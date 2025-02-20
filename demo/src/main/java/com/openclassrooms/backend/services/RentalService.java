package com.openclassrooms.backend.services;

import com.openclassrooms.backend.dto.RentalListDTO;
import com.openclassrooms.backend.dto.RentalRequestDTO;
import com.openclassrooms.backend.dto.RentalResponseDTO;
import com.openclassrooms.backend.dto.ResponseDTO;
import com.openclassrooms.backend.entities.Rental;
import com.openclassrooms.backend.entities.User;
import com.openclassrooms.backend.repositories.RentalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentalService {

  @Autowired
  private RentalRepository rentalRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private FileStorageService fileStorageService;

  public ResponseDTO createRental(RentalRequestDTO request) {
    // get user credentials
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    // despite name of method, return value is email, not username
    String email = auth.getName();
    System.out.println(" rental service " + email);
    User user = userService.getUserWithEmail(email);

    // handle image files
    String fileUrl = null;

    try {
      fileUrl = fileStorageService.saveFile(request.getPicture());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    Rental rental = convertToEntity(request, user);
    rental.setPicture(fileUrl);
    System.out.println(rental);
    rentalRepository.save(rental);
    ResponseDTO response = new ResponseDTO();
    response.setMessage("Rental created !");

    return response;
  }

  public RentalListDTO getAllRentals() {
    List<Rental> rentals = this.rentalRepository.findAll();
    List<RentalResponseDTO> rentalDTOs = new ArrayList<>();

    rentals.forEach(rental -> rentalDTOs.add(convertToDTO(rental)));
    RentalListDTO list = new RentalListDTO();
    list.setRentals(rentalDTOs);
    return list;
  }

  public RentalResponseDTO getRental(Long id) {
    Rental rental = this.rentalRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Rental not found"));
    return convertToDTO(rental);
  }

  public ResponseDTO updateRental(RentalRequestDTO requestDTO, Long id) {
    Rental rental = this.rentalRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Rental not found"));
    modelMapper.map(requestDTO, rental);
    rental.setUpdatedAt(LocalDateTime.now());
    this.rentalRepository.save(rental);
    ResponseDTO response = new ResponseDTO();
    response.setMessage("Rental updated !");
    return response;
  }
// mappers

  private Rental convertToEntity(RentalRequestDTO requestDTO, User user) {
    Rental rental = modelMapper.map(requestDTO, Rental.class);
    rental.setOwner(user);
    rental.setCreatedAt(LocalDateTime.now());
    rental.setUpdatedAt(LocalDateTime.now());
    return rental;
  }

  private RentalResponseDTO convertToDTO(Rental rental) {
    RentalResponseDTO dto = modelMapper.map(rental, RentalResponseDTO.class);
    User owner = rental.getOwner();
    // convert date formats
    if (rental.getCreatedAt() != null) {
      dto.setCreatedAt(rental.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
    }
    if (rental.getUpdatedAt() != null) {
      dto.setUpdatedAt(rental.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
    }

    dto.setOwner_id(owner.getId());
    return dto;
  }
}

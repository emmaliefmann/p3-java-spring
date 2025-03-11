package com.openclassrooms.backend.services;

import com.openclassrooms.backend.dto.RentalListDTO;
import com.openclassrooms.backend.dto.RentalRequestDTO;
import com.openclassrooms.backend.dto.RentalResponseDTO;
import com.openclassrooms.backend.dto.ResponseDTO;
import com.openclassrooms.backend.entities.Rental;
import com.openclassrooms.backend.entities.User;
import com.openclassrooms.backend.mappers.RentalMapper;
import com.openclassrooms.backend.repositories.RentalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentalService {

  private final RentalMapper rentalMapper;

  private final RentalRepository rentalRepository;

  private final UserService userService;

  private final ModelMapper modelMapper;


  private final FileStorageService fileStorageService;

  public RentalService(RentalMapper rentalMapper, RentalRepository rentalRepository, UserService userService, ModelMapper modelMapper, FileStorageService fileStorageService) {
    this.rentalMapper = rentalMapper;
    this.rentalRepository = rentalRepository;
    this.userService = userService;
    this.modelMapper = modelMapper;
    this.fileStorageService = fileStorageService;
  }

  public ResponseDTO createRental(RentalRequestDTO request) throws IOException {
    // get user credentials
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    // despite name of method, return value is email, not username
    String email = auth.getName();
    User user = userService.getUserWithEmail(email);

    String fileUrl = fileStorageService.saveFile(request.getPicture());
    Rental rental = rentalMapper.convertToEntity(request, user);
    rental.setPicture(fileUrl);
    rentalRepository.save(rental);
    ResponseDTO response = new ResponseDTO();
    response.setMessage("Rental created !");

    return response;
  }

  public RentalListDTO getAllRentals() {
    List<Rental> rentals = this.rentalRepository.findAll();
    List<RentalResponseDTO> rentalDTOs = new ArrayList<>();

    rentals.forEach(rental -> rentalDTOs.add(rentalMapper.convertToDTO(rental)));
    RentalListDTO list = new RentalListDTO();
    list.setRentals(rentalDTOs);
    return list;
  }

  public RentalResponseDTO getRental(Long id) {
    Rental rental = this.rentalRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Rental not found"));
    return rentalMapper.convertToDTO(rental);
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

}

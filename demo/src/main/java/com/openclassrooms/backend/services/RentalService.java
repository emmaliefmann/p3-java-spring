package com.openclassrooms.backend.services;

import com.openclassrooms.backend.dto.RentalRequestDTO;
import com.openclassrooms.backend.dto.RentalResponseDTO;
import com.openclassrooms.backend.entities.Rental;
import com.openclassrooms.backend.entities.User;
import com.openclassrooms.backend.repositories.RentalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService {

  @Autowired
  private RentalRepository rentalRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private ModelMapper modelMapper;

  public String createRental(RentalRequestDTO request) {
    // get user credentials from auth
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    // despite name of method, return value is email, not username
    String email = auth.getName();
    System.out.println(" rental service " + email);
    User user = userService.getUserWithEmail(email);
    Rental rental = convertToEntity(request, user);
    System.out.println(rental);
    rentalRepository.save(rental);
    // find in reponsitory

    return "Rental created !";
  }

  public List<Rental> getAllRentals() {
    // map to DTOS
    return this.rentalRepository.findAll();
  }

  public RentalResponseDTO getRental(Long id) {
    // map to DTOS
    Rental rental = this.rentalRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Rental not found"));
    return convertToDTO(rental);
  }

  private Rental convertToEntity(RentalRequestDTO requestDTO, User user) {
    Rental rental = modelMapper.map(requestDTO, Rental.class);
    rental.setOwner(user);
    rental.setCreatedAt(LocalDateTime.now());
    rental.setUpdatedAt(LocalDateTime.now());
    return rental;
  }

  private RentalResponseDTO convertToDTO(Rental rental) {
    RentalResponseDTO dto = modelMapper.map(rental, RentalResponseDTO.class);
    System.out.println(dto.getOwner_id());
    return dto;
  }
}

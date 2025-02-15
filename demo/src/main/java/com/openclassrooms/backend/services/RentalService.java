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

@Service
public class RentalService {

  @Autowired
  private RentalRepository rentalRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private ModelMapper modelMapper;

  public RentalResponseDTO createRental(RentalRequestDTO request) {
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
    // if found return the 200 code + mesj
    RentalResponseDTO response = new RentalResponseDTO();
    // store messages elsewhere
    response.setMessage("Rental created !");
    return response;
  }

  private Rental convertToEntity(RentalRequestDTO requestDTO, User user) {
    Rental rental = modelMapper.map(requestDTO, Rental.class);
    rental.setOwner(user);
    rental.setCreatedAt(LocalDateTime.now());
    rental.setUpdatedAt(LocalDateTime.now());
    return rental;
  }
}

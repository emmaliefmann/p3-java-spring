package com.openclassrooms.backend.controllers;

import com.openclassrooms.backend.dto.RentalRequestDTO;
import com.openclassrooms.backend.dto.RentalResponseDTO;
import com.openclassrooms.backend.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentalController {

  @Autowired
  RentalService rentalService;

  // double check address, in postman collection /1, likelt error
  @PostMapping("/rentals/1")
  public RentalResponseDTO createRental(@RequestBody RentalRequestDTO request) {
    System.out.println("Rental controller");
    return this.rentalService.createRental(request);
  }
}

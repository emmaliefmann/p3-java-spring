package com.openclassrooms.backend.controllers;

import com.openclassrooms.backend.dto.RentalRequestDTO;
import com.openclassrooms.backend.dto.RentalResponseDTO;
import com.openclassrooms.backend.entities.Rental;
import com.openclassrooms.backend.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RentalsController {

  @Autowired
  RentalService rentalService;

  // double check address, in postman collection /1, likelt error
  @PostMapping(value = "/rentals/1", consumes = {"application/json", "multipart/form-data"})
  public String createRental(@RequestBody RentalRequestDTO request) {
      return this.rentalService.createRental(request);
  }

  @GetMapping("/rentals")
  public List<Rental> getAllRentals() {
    return this.rentalService.getAllRentals();
  }

  @GetMapping("/rentals/{id}")
  public RentalResponseDTO getRental(@PathVariable Long id) {
    return this.rentalService.getRental(id);
  }
}

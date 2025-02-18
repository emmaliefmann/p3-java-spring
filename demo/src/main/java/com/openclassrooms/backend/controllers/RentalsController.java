package com.openclassrooms.backend.controllers;

import com.openclassrooms.backend.dto.RentalListDTO;
import com.openclassrooms.backend.dto.RentalRequestDTO;
import com.openclassrooms.backend.dto.RentalResponseDTO;
import com.openclassrooms.backend.dto.ResponseDTO;
import com.openclassrooms.backend.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RentalsController {

  @Autowired
  RentalService rentalService;

  // double check address, in postman collection /1, likelt error
  @PostMapping(value = "/rentals/1", consumes = {"application/json", "multipart/form-data"})
  public ResponseDTO createRental(@RequestBody RentalRequestDTO request) {
      return this.rentalService.createRental(request);
  }

  @GetMapping("/rentals")
  public RentalListDTO getAllRentals() {
    return this.rentalService.getAllRentals();
  }

  @GetMapping("/rentals/{id}")
  public RentalResponseDTO getRental(@PathVariable Long id) {
    return this.rentalService.getRental(id);
  }

  @PutMapping(value = "/rentals/{id}")
  public ResponseDTO updateRental(@PathVariable Long id, @RequestBody RentalRequestDTO rental) {
    return this.rentalService.updateRental(rental, id);
  }
}

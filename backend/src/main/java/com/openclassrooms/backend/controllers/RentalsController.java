package com.openclassrooms.backend.controllers;

import com.openclassrooms.backend.dto.RentalListDTO;
import com.openclassrooms.backend.dto.RentalRequestDTO;
import com.openclassrooms.backend.dto.RentalResponseDTO;
import com.openclassrooms.backend.dto.ResponseDTO;
import com.openclassrooms.backend.entities.Rental;
import com.openclassrooms.backend.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/rentals")
public class RentalsController {

  @Autowired
  RentalService rentalService;

  // double check address, in postman collection /1, likelt error
  @PostMapping(consumes = "multipart/form-data")
  public ResponseDTO createRental(@RequestParam("name") String name,
                                  @RequestParam("surface") float surface,
                                  @RequestParam("price") float price,
                                  @RequestParam("description") String description,
                                  @RequestParam("picture") MultipartFile picture) {
    RentalRequestDTO rental = new RentalRequestDTO();
    rental.setName(name);
    rental.setSurface(surface);
    rental.setPrice(price);
    rental.setDescription(description);
    rental.setPicture(picture);
      return this.rentalService.createRental(rental);
  }

  @GetMapping
  public RentalListDTO getAllRentals() {
    return this.rentalService.getAllRentals();
  }

  @GetMapping("/{id}")
  public RentalResponseDTO getRental(@PathVariable Long id) {
    return this.rentalService.getRental(id);
  }

  @PutMapping(value = "/{id}", consumes = "multipart/form-data")
  public ResponseDTO updateRental(@PathVariable Long id,
                                  @RequestParam(required = false) String name,
                                  @RequestParam(required = false) float surface,
                                  @RequestParam(required = false) float price,
                                  @RequestParam(required = false) String description,
                                  @RequestParam(required = false) MultipartFile picture) {
    System.out.println("Put controller");
    RentalRequestDTO rental = new RentalRequestDTO();
    rental.setName(name);
    rental.setSurface(surface);
    rental.setPrice(price);
    rental.setDescription(description);
    rental.setPicture(picture);
    return this.rentalService.updateRental(rental, id);
  }
}

package com.openclassrooms.backend.dto;

import java.util.List;

public class RentalListDTO {
  private List<RentalResponseDTO> rentals;


  public List<RentalResponseDTO> getRentals() {
    return rentals;
  }

  public void setRentals(List<RentalResponseDTO> rentals) {
    this.rentals = rentals;
  }
}

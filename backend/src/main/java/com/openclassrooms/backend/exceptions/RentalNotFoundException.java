package com.openclassrooms.backend.exceptions;

public class RentalNotFoundException extends RuntimeException {
  public RentalNotFoundException(String message) {
    super(message);
  }
}

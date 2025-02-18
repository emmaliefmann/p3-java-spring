package com.openclassrooms.backend.dto;

public class MessageRequestDTO {

  private String message;
  private Long user_id;
  private Long rental_id;


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Long getUser_id() {
    return user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

  public Long getRental_id() {
    return rental_id;
  }

  public void setRental_id(Long rental_id) {
    this.rental_id = rental_id;
  }
}

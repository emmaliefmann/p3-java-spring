package com.openclassrooms.backend.dto;

public class MessageRequestDTO {

  private String message;
  private int user_id;
  private int rental_id;


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public int getUser_id() {
    return user_id;
  }

  public void setUser_id(int user_id) {
    this.user_id = user_id;
  }

  public int getRental_id() {
    return rental_id;
  }

  public void setRental_id(int rental_id) {
    this.rental_id = rental_id;
  }
}

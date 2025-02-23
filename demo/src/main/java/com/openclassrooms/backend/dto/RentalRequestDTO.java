package com.openclassrooms.backend.dto;

import org.springframework.web.multipart.MultipartFile;

public class RentalRequestDTO {

  private String name;
  private float surface;
  private float price;
  private MultipartFile picture;
  private String description;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public float getSurface() {
    return surface;
  }

  public void setSurface(float surface) {
    this.surface = surface;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public MultipartFile getPicture() {
    return picture;
  }

  public void setPicture(MultipartFile picture) {
    this.picture = picture;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}

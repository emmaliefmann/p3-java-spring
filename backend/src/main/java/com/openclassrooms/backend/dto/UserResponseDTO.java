package com.openclassrooms.backend.dto;

import java.time.LocalDateTime;

public class UserResponseDTO {
  private Long id;
  private String name;
  private String email;
  private String createdAt;
  private String updateAt;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdateAt() {
    return updateAt;
  }

  public void setUpdateAt(String updateAt) {
    this.updateAt = updateAt;
  }
}

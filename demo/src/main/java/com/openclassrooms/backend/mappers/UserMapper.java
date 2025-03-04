package com.openclassrooms.backend.mappers;

import com.openclassrooms.backend.dto.UserResponseDTO;
import com.openclassrooms.backend.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class UserMapper {
  private final ModelMapper modelMappper;
  private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

  public UserMapper(ModelMapper modelMapper) {
    this.modelMappper = modelMapper;
  }

  public UserResponseDTO convertToDTO(User user) {
    UserResponseDTO dto = modelMappper.map(user, UserResponseDTO.class);
    if (user.getCreatedAt() != null) {
      dto.setCreatedAt(user.getCreatedAt().format(dateFormatter));
    }
    if (user.getUpdateAt() != null) {
      dto.setUpdateAt(user.getUpdateAt().format(dateFormatter));
    }
    return dto;
  }
}

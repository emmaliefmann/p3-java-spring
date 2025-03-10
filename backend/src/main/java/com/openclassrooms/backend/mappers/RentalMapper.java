package com.openclassrooms.backend.mappers;

import com.openclassrooms.backend.dto.RentalRequestDTO;
import com.openclassrooms.backend.dto.RentalResponseDTO;
import com.openclassrooms.backend.entities.Rental;
import com.openclassrooms.backend.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class RentalMapper {
  private final ModelMapper modelMapper;
  private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

  public RentalMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public RentalResponseDTO convertToDTO(Rental rental) {
    RentalResponseDTO dto = modelMapper.map(rental, RentalResponseDTO.class);
    User owner = rental.getOwner();
    // convert date formats
    if (rental.getCreatedAt() != null) {
      dto.setCreatedAt(rental.getCreatedAt().format(dateFormatter));
    }
    if (rental.getUpdatedAt() != null) {
      dto.setUpdatedAt(rental.getUpdatedAt().format(dateFormatter));
    }

    dto.setOwner_id(owner.getId());
    return dto;
  }

  public Rental convertToEntity(RentalRequestDTO requestDTO, User user) {
    Rental rental = modelMapper.map(requestDTO, Rental.class);
    rental.setOwner(user);
    rental.setCreatedAt(LocalDateTime.now());
    rental.setUpdatedAt(LocalDateTime.now());
    return rental;
  }
}

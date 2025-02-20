package com.openclassrooms.backend.config;

import com.openclassrooms.backend.dto.RentalResponseDTO;
import com.openclassrooms.backend.dto.UserResponseDTO;
import com.openclassrooms.backend.entities.Rental;
import com.openclassrooms.backend.entities.User;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration()
      .setSkipNullEnabled(true)
      .setFieldMatchingEnabled(true);

// Universal converter for LocalDateTime → String
    modelMapper.typeMap(Rental.class, RentalResponseDTO.class).addMappings(mapper ->
      mapper.map(src -> src.getCreatedAt() != null ?
          src.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) : null,
        RentalResponseDTO::setCreatedAt)
    );

    modelMapper.typeMap(User.class, UserResponseDTO.class).addMappings(mapper ->
      mapper.map(src -> src.getCreatedAt() != null ?
          src.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) : null,
        UserResponseDTO::setCreatedAt)
    );

    return modelMapper;
  }
}

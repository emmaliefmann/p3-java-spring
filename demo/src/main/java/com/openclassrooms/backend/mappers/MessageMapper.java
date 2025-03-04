package com.openclassrooms.backend.mappers;

import com.openclassrooms.backend.dto.MessageRequestDTO;
import com.openclassrooms.backend.entities.Message;
import com.openclassrooms.backend.entities.Rental;
import com.openclassrooms.backend.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class MessageMapper {
  private final ModelMapper modelMappper;
  private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

  public MessageMapper(ModelMapper modelMappper) {
    this.modelMappper = modelMappper;
  }

  public Message convertToEntity(MessageRequestDTO messageDTO, User user, Rental rental) {
    Message message = new Message();
    message.setMessage(messageDTO.getMessage());
    message.setCreatedAt(LocalDateTime.now());
    message.setUpdatedAt(LocalDateTime.now());
    message.setUser(user);
    message.setRental(rental);
    return message;
  }
}

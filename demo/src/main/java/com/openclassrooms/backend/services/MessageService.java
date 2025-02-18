package com.openclassrooms.backend.services;

import com.openclassrooms.backend.dto.MessageRequestDTO;
import com.openclassrooms.backend.dto.ResponseDTO;
import com.openclassrooms.backend.entities.Message;
import com.openclassrooms.backend.entities.Rental;
import com.openclassrooms.backend.entities.User;
import com.openclassrooms.backend.repositories.MessageRepository;
import com.openclassrooms.backend.repositories.RentalRepository;
import com.openclassrooms.backend.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {

  @Autowired
  MessageRepository messageRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RentalRepository rentalRepository;

  @Autowired
  ModelMapper modelMapper;

  public ResponseDTO createMessage(MessageRequestDTO request) {
    Message message = convertToEntity(request);
    this.messageRepository.save(message);
    ResponseDTO response = new ResponseDTO();
    response.setMessage("Message send with success");
    return response;
  }

  private Message convertToEntity(MessageRequestDTO messageDTO) {
    Message message = new Message();
    message.setMessage(messageDTO.getMessage());
    message.setCreatedAt(LocalDateTime.now());
    message.setUpdatedAt(LocalDateTime.now());
    User user = this.userRepository.findUserById(messageDTO.getUser_id())
      .orElseThrow(() -> new RuntimeException("User not found"));
    message.setUser(user);
    Rental rental = this.rentalRepository.findById(messageDTO.getRental_id())
      .orElseThrow(() -> new RuntimeException("Rental not found"));
    message.setRental(rental);
    return message;
  }

}

package com.openclassrooms.backend.services;

import com.openclassrooms.backend.dto.MessageRequestDTO;
import com.openclassrooms.backend.dto.ResponseDTO;
import com.openclassrooms.backend.entities.Message;
import com.openclassrooms.backend.entities.Rental;
import com.openclassrooms.backend.entities.User;
import com.openclassrooms.backend.exceptions.RentalNotFoundException;
import com.openclassrooms.backend.exceptions.UserNotFoundException;
import com.openclassrooms.backend.mappers.MessageMapper;
import com.openclassrooms.backend.repositories.MessageRepository;
import com.openclassrooms.backend.repositories.RentalRepository;
import com.openclassrooms.backend.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

  private final MessageMapper messageMapper;

  private final MessageRepository messageRepository;

  private final UserRepository userRepository;


  private final RentalRepository rentalRepository;

  public MessageService(MessageMapper messageMapper, MessageRepository messageRepository, UserRepository userRepository, RentalRepository rentalRepository, ModelMapper modelMapper) {
    this.messageMapper = messageMapper;
    this.messageRepository = messageRepository;
    this.userRepository = userRepository;
    this.rentalRepository = rentalRepository;
  }

  public ResponseDTO createMessage(MessageRequestDTO request) {
    User user = this.userRepository.findUserById(request.getUser_id())
      .orElseThrow(() -> new UserNotFoundException("User not found"));
    Rental rental = this.rentalRepository.findById(request.getRental_id())
      .orElseThrow(() -> new RentalNotFoundException("Rental not found"));
    Message message = messageMapper.convertToEntity(request, user, rental);
    this.messageRepository.save(message);
    ResponseDTO response = new ResponseDTO();
    response.setMessage("Message send with success");
    return response;
  }



}

package com.openclassrooms.backend.services;

import com.openclassrooms.backend.dto.MessageRequestDTO;
import com.openclassrooms.backend.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {


  public void createNewMessage(MessageRequestDTO request) {
    Message message = new Message();
    message.setMessage(request.getMessage());
    message.setCreatedAt(LocalDateTime.now());
  }
}

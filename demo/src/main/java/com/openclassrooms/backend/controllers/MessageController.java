package com.openclassrooms.backend.controllers;

import com.openclassrooms.backend.dto.MessageRequestDTO;
import com.openclassrooms.backend.dto.ResponseDTO;
import com.openclassrooms.backend.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

  @Autowired
  MessageService messageService;

  @PostMapping("/messages")
  public ResponseDTO createMessage(@RequestBody MessageRequestDTO request) {
    return this.messageService.createMessage(request);
  }
}

package com.openclassrooms.backend.controllers;

import com.openclassrooms.backend.dto.MessageRequestDTO;
import com.openclassrooms.backend.dto.ResponseDTO;
import com.openclassrooms.backend.services.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

  private final MessageService messageService;

  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  @PostMapping("/messages")
  public ResponseEntity<ResponseDTO> createMessage(@RequestBody MessageRequestDTO request) {
    ResponseDTO response = messageService.createMessage(request);
    return ResponseEntity.ok().body(response);
  }
}

package com.openclassrooms.backend.controllers;

import com.openclassrooms.backend.dto.MessageRequestDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

  @PostMapping("/api/messages")
  public void createMessage(@RequestBody MessageRequestDTO request) {

  }
}

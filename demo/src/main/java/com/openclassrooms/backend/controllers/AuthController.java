package com.openclassrooms.backend.controllers;

import com.openclassrooms.backend.dto.UserRequestDTO;
import com.openclassrooms.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  @Autowired
  UserService userService;

  @PostMapping("/auth/register")
  public void registerUser(@RequestBody UserRequestDTO request) {
    userService.registerNewUser(request);
  }
}

package com.openclassrooms.backend.controllers;

import com.openclassrooms.backend.dto.UserResponseDTO;
import com.openclassrooms.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserService userService;


  @GetMapping("/user/{id}")
  public UserResponseDTO getUserById(@PathVariable Long id) {
    System.out.println("user controller");
    return userService.getUserById(id);
  }
}

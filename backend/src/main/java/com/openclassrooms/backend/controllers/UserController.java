package com.openclassrooms.backend.controllers;

import com.openclassrooms.backend.dto.UserResponseDTO;
import com.openclassrooms.backend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }


  @GetMapping("/user/{id}")
  public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
    UserResponseDTO user = userService.getUserById(id);
    return ResponseEntity.ok().body(user);
  }
}

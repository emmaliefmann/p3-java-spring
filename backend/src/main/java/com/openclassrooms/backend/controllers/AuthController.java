package com.openclassrooms.backend.controllers;

import com.openclassrooms.backend.dto.*;
import com.openclassrooms.backend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  private final UserService userService;
  public AuthController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/auth/register")
  public ResponseEntity<TokenResponseDTO> registerUser(@RequestBody UserRequestDTO request) {
    TokenResponseDTO token = userService.registerNewUser(request);
    return ResponseEntity.ok().body(token);
  }

  @PostMapping("/auth/login")
  public ResponseEntity<TokenResponseDTO> login(@RequestBody LoginRequestDTO login) {
    TokenResponseDTO token =  userService.verifyUser(login);
    return ResponseEntity.ok().body(token);
  }

  @GetMapping("/auth/me")
  public ResponseEntity<UserResponseDTO> getConnectedUser() {
    UserResponseDTO user = userService.getConnectedUser();
    return ResponseEntity.ok().body(user);
  }

}

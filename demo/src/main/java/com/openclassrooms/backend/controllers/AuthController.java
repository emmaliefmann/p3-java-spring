package com.openclassrooms.backend.controllers;

import com.openclassrooms.backend.dto.LoginRequestDTO;
import com.openclassrooms.backend.dto.UserRequestDTO;
import com.openclassrooms.backend.dto.UserResponseDTO;
import com.openclassrooms.backend.entities.User;
import com.openclassrooms.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  @Autowired
  UserService userService;

  @PostMapping("/auth/register")
  public void registerUser(@RequestBody UserRequestDTO request) {
    userService.registerNewUser(request);
  }

  @PostMapping("/auth/email")
  public ResponseEntity<String> login(@RequestBody LoginRequestDTO login) {
    String token = userService.verifyUser(login);
    System.out.println("Token " + token);
    return ResponseEntity.ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(token);
  }

  //@GetMapping("/auth/me")
  // return type not found in mockoon ?

}

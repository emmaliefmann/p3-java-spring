package com.openclassrooms.backend.controllers;

import com.openclassrooms.backend.dto.LoginRequestDTO;
import com.openclassrooms.backend.dto.UserRequestDTO;
import com.openclassrooms.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  @Autowired
  UserService userService;

//  @PostMapping("/auth/register")
//  public void registerUser(@RequestBody UserRequestDTO request) {
//    userService.registerNewUser(request);
//  }

  @PostMapping("/auth/email")
  public void login(@RequestBody LoginRequestDTO login) {
    System.out.println(login);
    userService.verifyUser(login);
  }

  @GetMapping("/auth/email")
  public void testRoute() {
    System.out.println("test");
  }
}

package com.openclassrooms.backend.controllers;

import com.openclassrooms.backend.dto.UserResponseDTO;
import com.openclassrooms.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

  @GetMapping("/auth")
  public String greet() {
    return "Auth";
  }

  @GetMapping("/all")
  public String testAuth() {

    return "All permited";
  }

}

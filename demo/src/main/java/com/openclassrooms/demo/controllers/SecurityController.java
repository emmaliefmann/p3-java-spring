package com.openclassrooms.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
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

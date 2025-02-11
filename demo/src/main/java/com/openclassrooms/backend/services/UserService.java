package com.openclassrooms.backend.services;

import com.openclassrooms.backend.dto.LoginRequestDTO;
import com.openclassrooms.backend.dto.UserRequestDTO;
import com.openclassrooms.backend.dto.UserResponseDTO;
import com.openclassrooms.backend.entities.User;
import com.openclassrooms.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

  private final PasswordEncoder passwordEncoder;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  AuthenticationManager authManager;

  @Autowired
  private JWTService jwtService;

  public UserService(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  public void registerNewUser(UserRequestDTO userRequestDTO) {
    // returns completed profile, map object back
    // check email doesn't already exist make exception
    User user = new User();
    user.setEmail(userRequestDTO.getEmail());
    user.setName(userRequestDTO.getName());
    user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
    user.setCreatedAt(LocalDateTime.now());
    user.setUpdateAt(LocalDateTime.now());
    // correct return type?
    userRepository.save(user);
  }

  public UserResponseDTO getUserById(Long id) {
    User user = userRepository.findUserById(id)
      // make specific exception
      .orElseThrow(() -> new RuntimeException("User not found"));
    UserResponseDTO responseDTO = new UserResponseDTO();
    responseDTO.setId(user.getId());
    responseDTO.setName(user.getName());
    responseDTO.setEmail(user.getEmail());
    responseDTO.setCreatedAt(user.getCreatedAt());
    responseDTO.setUpdateAt(user.getUpdateAt());

    return responseDTO;
  }

  public User getUserWithEmail(String email) {
    return this.userRepository.findByEmail(email)
      .orElseThrow(() -> new RuntimeException("User not found"));
  }

  public String verifyUser(LoginRequestDTO user) {
    System.out.println("verify user method");
    System.out.println(user.getPassword());
    System.out.println(user.getEmail());
    Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

    if (auth.isAuthenticated()) {
      System.out.println("is authenticated");
      return jwtService.generateToken(user);
    }
    else if(!auth.isAuthenticated()) {
      System.out.println("not authenticated    ");
    throw new RuntimeException("Login failed for user: " + user.getEmail());
    }
    System.out.println("should throw error");
    throw new RuntimeException("Login failed for user: " + user.getEmail());
  }
}

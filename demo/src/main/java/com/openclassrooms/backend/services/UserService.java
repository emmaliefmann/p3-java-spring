package com.openclassrooms.backend.services;

import com.openclassrooms.backend.dto.LoginRequestDTO;
import com.openclassrooms.backend.dto.UserRequestDTO;
import com.openclassrooms.backend.dto.UserResponseDTO;
import com.openclassrooms.backend.entities.User;
import com.openclassrooms.backend.repositories.UserRepository;
import org.modelmapper.ModelMapper;
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
    User user = convertToEntity(userRequestDTO);
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

  public void verifyUser(LoginRequestDTO login) {

//    Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(login.getLogin(), login.getPassword()));
//    //implementer tokens
//    if (auth.isAuthenticated()) {
//      System.out.println("Login success");
//    }
//    else if(!auth.isAuthenticated()) {
//      // custom exception needed
//      throw new RuntimeException("Login failed for user: " + login.getLogin());
//    }
//    throw new RuntimeException("Login failed for user: " + login.getLogin());
//
    try {
      Authentication auth = authManager.authenticate(
        new UsernamePasswordAuthenticationToken(login.getLogin(), login.getPassword())
      );

      if (auth.isAuthenticated()) {
        System.out.println("Login success");
      }
    } catch (BadCredentialsException e) {
      throw new RuntimeException("Invalid credentials for user: " + login.getLogin(), e);
    } catch (Exception e) {
      throw new RuntimeException("Authentication failed for user: " + login.getLogin(), e);
    }
  }
}

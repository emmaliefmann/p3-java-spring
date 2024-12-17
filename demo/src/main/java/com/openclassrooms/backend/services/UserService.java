package com.openclassrooms.backend.services;

import com.openclassrooms.backend.dto.UserRequestDTO;
import com.openclassrooms.backend.dto.UserResponseDTO;
import com.openclassrooms.backend.entities.User;
import com.openclassrooms.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

@Autowired
private UserRepository userRepository;

  public void registerNewUser(UserRequestDTO userRequestDTO) {
    // returns completed profile ?
    // check email doesn't already exist make exception
    // encrypt password
    User user = new User();
    user.setEmail(userRequestDTO.getEmail());
    user.setName(userRequestDTO.getName());
    user.setPassword(userRequestDTO.getPassword());
    user.setCreatedAt(LocalDateTime.now());
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
}

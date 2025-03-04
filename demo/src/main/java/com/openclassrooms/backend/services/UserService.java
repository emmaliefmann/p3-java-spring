package com.openclassrooms.backend.services;

import com.openclassrooms.backend.dto.LoginRequestDTO;
import com.openclassrooms.backend.dto.TokenResponseDTO;
import com.openclassrooms.backend.dto.UserRequestDTO;
import com.openclassrooms.backend.dto.UserResponseDTO;
import com.openclassrooms.backend.entities.User;
import com.openclassrooms.backend.exceptions.AuthenticationException;
import com.openclassrooms.backend.exceptions.UserAlreadyExistsException;
import com.openclassrooms.backend.exceptions.UserNotFoundException;
import com.openclassrooms.backend.mappers.UserMapper;
import com.openclassrooms.backend.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class UserService {
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  AuthenticationManager authManager;

  @Autowired
  private JWTService jwtService;

  @Autowired
  private ModelMapper modelMapper;

  public UserService(PasswordEncoder passwordEncoder, UserMapper userMapper) {

    this.passwordEncoder = passwordEncoder;
    this.userMapper = userMapper;
  }

  public void registerNewUser(UserRequestDTO userDTO) {
    Optional<User> existingUser = this.userRepository.findByEmail(userDTO.getEmail());
    if (existingUser.isPresent()) {
      throw new UserAlreadyExistsException("User with this email already exists");
    }

    User user = new User();
    user.setEmail(userDTO.getEmail());
    user.setName(userDTO.getName());
    user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    user.setCreatedAt(LocalDateTime.now());
    user.setUpdateAt(LocalDateTime.now());
    userRepository.save(user);
  }

  public UserResponseDTO getUserById(Long id) {
    User user = userRepository.findUserById(id)
      .orElseThrow(() -> new UserNotFoundException("User not found"));

    return userMapper.convertToDTO(user);
  }

  public User getUserWithEmail(String email) {
    return this.userRepository.findByEmail(email)
      .orElseThrow(() -> new RuntimeException("User not found"));
  }

  public TokenResponseDTO verifyUser(LoginRequestDTO user) {
    Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));

    if (auth.isAuthenticated()) {
     String token = jwtService.generateToken(user);
      TokenResponseDTO response = new TokenResponseDTO();
      response.setToken(token);
      return response;
    }
    else if(!auth.isAuthenticated()) {
      throw new AuthenticationException("Login failed for user: " + user.getLogin());
    }
    throw new AuthenticationException("Login failed for user: " + user.getLogin());
  }
}

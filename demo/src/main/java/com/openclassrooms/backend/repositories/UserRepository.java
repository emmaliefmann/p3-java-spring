package com.openclassrooms.backend.repositories;

import com.openclassrooms.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findUserById(Long id);
}

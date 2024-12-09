package com.openclassrooms.demo.repositories;

import com.openclassrooms.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

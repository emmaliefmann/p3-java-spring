package com.openclassrooms.backend.repositories;

import com.openclassrooms.backend.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}

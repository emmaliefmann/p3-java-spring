package com.openclassrooms.demo.repositories;

import com.openclassrooms.demo.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}

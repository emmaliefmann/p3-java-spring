package com.openclassrooms.backend.repositories;

import com.openclassrooms.backend.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}

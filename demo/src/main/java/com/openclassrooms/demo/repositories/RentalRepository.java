package com.openclassrooms.demo.repositories;

import com.openclassrooms.demo.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}

package com.CarRental.Benutzerverwaltung.repository;

import com.CarRental.Benutzerverwaltung.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

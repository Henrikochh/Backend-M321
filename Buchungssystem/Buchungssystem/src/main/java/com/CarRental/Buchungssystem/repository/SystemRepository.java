package com.CarRental.Buchungssystem.repository;

import com.CarRental.Buchungssystem.model.System;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemRepository extends JpaRepository<System, Long> {
}

package com.CarRental.Buchungssystem.controller;

import com.CarRental.Buchungssystem.repository.SystemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Buchung")
@RequiredArgsConstructor
public class SystemController {
    private final SystemRepository repository;

}

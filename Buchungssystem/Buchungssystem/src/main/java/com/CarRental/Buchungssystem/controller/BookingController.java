package com.CarRental.Buchungssystem.controller;

import com.CarRental.Buchungssystem.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Buchung")
public class BookingController {
    private final BookingRepository repository;

    public BookingController(BookingRepository repository) {
        this.repository = repository;
    }
}

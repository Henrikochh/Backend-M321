package com.CarRental.Buchungssystem.controller;

import com.CarRental.Buchungssystem.dto.BookingResponseDTO;
import com.CarRental.Buchungssystem.model.Booking;
import com.CarRental.Buchungssystem.repository.BookingRepository;
import com.CarRental.Buchungssystem.service.BookingOrchestrationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buchung")
public class BookingController {
    private final BookingRepository repository;
    private final BookingOrchestrationService orchestrationService;

    public BookingController(BookingRepository repository, BookingOrchestrationService orchestrationService) {
        this.repository = repository;
        this.orchestrationService = orchestrationService;
    }

    @GetMapping
    public List<BookingResponseDTO> getAllBookings() {
        return orchestrationService.getAllBookings();
    }

    @GetMapping("/{id}")
    public BookingResponseDTO getBookingById(@PathVariable Long id) {
        return orchestrationService.getBookingById(id);
    }

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return repository.save(booking);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

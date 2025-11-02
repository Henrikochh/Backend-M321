package com.CarRental.Buchungssystem.controller;

import com.CarRental.Buchungssystem.dto.BookingRequestDTO;
import com.CarRental.Buchungssystem.dto.BookingResponseDTO;
import com.CarRental.Buchungssystem.model.Booking;
import com.CarRental.Buchungssystem.repository.BookingRepository;
import com.CarRental.Buchungssystem.service.BookingOrchestrationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.CarRental.Buchungssystem.exception.VehicleNotAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
    public Booking createBooking(@RequestBody BookingRequestDTO bookingRequestDTO) {
        return orchestrationService.createBooking(bookingRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        orchestrationService.deleteBooking(id);
    }

    @ExceptionHandler(VehicleNotAvailableException.class)
    public ResponseEntity<String> handleVehicleNotAvailableException(VehicleNotAvailableException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
}

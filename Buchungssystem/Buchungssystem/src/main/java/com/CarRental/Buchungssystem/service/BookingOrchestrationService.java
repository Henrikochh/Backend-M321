package com.CarRental.Buchungssystem.service;

import com.CarRental.Buchungssystem.dto.BookingResponseDTO;
import com.CarRental.Buchungssystem.dto.UserDTO;
import com.CarRental.Buchungssystem.dto.VehicleDTO;
import com.CarRental.Buchungssystem.model.Booking;
import com.CarRental.Buchungssystem.repository.BookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingOrchestrationService {

    private final BookingRepository bookingRepository;
    private final RestTemplate restTemplate;

    public BookingOrchestrationService(BookingRepository bookingRepository, RestTemplate restTemplate) {
        this.bookingRepository = bookingRepository;
        this.restTemplate = restTemplate;
    }

    public List<BookingResponseDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(this::toBookingResponseDTO)
                .collect(Collectors.toList());
    }

    public BookingResponseDTO getBookingById(Long id) {
        return bookingRepository.findById(id)
                .map(this::toBookingResponseDTO)
                .orElse(null);
    }

    private BookingResponseDTO toBookingResponseDTO(Booking booking) {
        VehicleDTO vehicleDTO = restTemplate.getForObject("http://fahrzeug-service:8080/api/fahrzeuge/" + booking.getVehicleId(), VehicleDTO.class);
        UserDTO userDTO = restTemplate.getForObject("http://benutzer-service:8082/api/benutzer/" + booking.getUserId(), UserDTO.class);

        BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();
        bookingResponseDTO.setBooking(booking);
        bookingResponseDTO.setVehicle(vehicleDTO);
        bookingResponseDTO.setUser(userDTO);
        return bookingResponseDTO;
    }
}

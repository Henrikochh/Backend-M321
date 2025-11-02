package com.CarRental.Buchungssystem.service;

import com.CarRental.Buchungssystem.dto.BookingRequestDTO;
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

    public Booking createBooking(BookingRequestDTO bookingRequestDTO) {
        System.out.println("Creating booking for vehicle: " + bookingRequestDTO.getVehicleId());
        VehicleDTO vehicleDTO = restTemplate.getForObject("http://fahrzeug-service:8080/api/fahrzeuge/" + bookingRequestDTO.getVehicleId(), VehicleDTO.class);
        System.out.println("Vehicle DTO: " + vehicleDTO);
        if (vehicleDTO == null || !vehicleDTO.isAvailable()) {
            System.out.println("Vehicle not available");
            throw new com.CarRental.Buchungssystem.exception.VehicleNotAvailableException("Vehicle is not available for booking");
        }

        Booking booking = new Booking();
        booking.setVehicleId(bookingRequestDTO.getVehicleId());
        booking.setUserId(bookingRequestDTO.getUserId());
        booking.setStartDate(bookingRequestDTO.getStartDate());
        booking.setEndDate(bookingRequestDTO.getEndDate());

        System.out.println("Saving booking");
        Booking savedBooking = bookingRepository.save(booking);
        System.out.println("Booking saved: " + savedBooking);
        updateVehicleAvailability(bookingRequestDTO.getVehicleId(), false);
        return savedBooking;
    }

    private void updateVehicleAvailability(Long vehicleId, boolean availability) {
        System.out.println("Updating vehicle availability for vehicle: " + vehicleId + " to " + availability);
        VehicleDTO vehicleDTO = restTemplate.getForObject("http://fahrzeug-service:8080/api/fahrzeuge/" + vehicleId, VehicleDTO.class);
        if (vehicleDTO != null) {
            vehicleDTO.setAvailable(availability);
            restTemplate.put("http://fahrzeug-service:8080/api/fahrzeuge/" + vehicleId, vehicleDTO);
            System.out.println("Vehicle availability updated");
        } else {
            System.out.println("Vehicle not found, could not update availability");
        }
    }

    public void deleteBooking(Long id) {
        bookingRepository.findById(id).ifPresent(booking -> {
            updateVehicleAvailability(booking.getVehicleId(), true);
            bookingRepository.deleteById(id);
        });
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

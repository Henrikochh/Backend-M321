package com.CarRental.Buchungssystem.service;

import com.CarRental.Buchungssystem.dto.VehicleDTO;
import com.CarRental.Buchungssystem.model.Booking;
import com.CarRental.Buchungssystem.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class BookingOrchestrationServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BookingOrchestrationService bookingOrchestrationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBooking_should_create_booking_when_vehicle_is_available() {
        // Given
        Booking booking = new Booking(1L, 1L, 1L, LocalDate.now(), LocalDate.now().plusDays(5));
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setAvailable(true);

        when(restTemplate.getForObject(anyString(), any(Class.class))).thenReturn(vehicleDTO);
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        // When
        bookingOrchestrationService.createBooking(booking);

        // Then
        verify(bookingRepository, times(1)).save(booking);
        verify(restTemplate, times(1)).put(anyString(), any());
    }

    @Test
    void createBooking_should_throw_exception_when_vehicle_is_unavailable() {
        // Given
        Booking booking = new Booking(1L, 1L, 1L, LocalDate.now(), LocalDate.now().plusDays(5));
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setAvailable(false);

        when(restTemplate.getForObject(anyString(), any(Class.class))).thenReturn(vehicleDTO);

        // When & Then
        assertThrows(RuntimeException.class, () -> bookingOrchestrationService.createBooking(booking));
        verify(bookingRepository, never()).save(any(Booking.class));
        verify(restTemplate, never()).put(anyString(), any());
    }

    @Test
    void deleteBooking_should_make_vehicle_available_again() {
        // Given
        Booking booking = new Booking(1L, 1L, 1L, LocalDate.now(), LocalDate.now().plusDays(5));
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setAvailable(false);

        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));
        when(restTemplate.getForObject(anyString(), any(Class.class))).thenReturn(vehicleDTO);

        // When
        bookingOrchestrationService.deleteBooking(1L);

        // Then
        verify(bookingRepository, times(1)).deleteById(1L);
        verify(restTemplate, times(1)).put(anyString(), any());
    }
}

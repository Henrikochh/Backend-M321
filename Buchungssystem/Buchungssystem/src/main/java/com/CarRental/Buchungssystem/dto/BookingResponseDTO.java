package com.CarRental.Buchungssystem.dto;

import com.CarRental.Buchungssystem.model.Booking;
import lombok.Data;

@Data
public class BookingResponseDTO {
    private Booking booking;
    private VehicleDTO vehicle;
    private UserDTO user;
}

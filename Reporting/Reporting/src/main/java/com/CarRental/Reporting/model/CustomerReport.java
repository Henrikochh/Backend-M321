package com.CarRental.Reporting.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerReport {
    
    private Long customerId;
    private String customerName;
    private String customerEmail;
    private Integer totalBookings;
    private Double totalSpent;
    private LocalDate lastBookingDate;
    private LocalDate registrationDate;
    private String customerStatus; // ACTIVE, INACTIVE, PREMIUM
}
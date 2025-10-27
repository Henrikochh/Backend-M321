package com.CarRental.Reporting.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleUsageReport {
    
    private Long vehicleId;
    private String vehicleModel;
    private String vehicleBrand;
    private Integer totalRentals;
    private Integer totalRentalDays;
    private Double utilizationRate;
    private LocalDate reportDate;
    private String currentStatus;
}
package com.CarRental.Reporting.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevenueReport {
    
    private LocalDate reportDate;
    private BigDecimal dailyRevenue;
    private BigDecimal monthlyRevenue;
    private BigDecimal yearlyRevenue;
    private Integer totalBookings;
    private Integer activeRentals;
    private Integer completedRentals;
}
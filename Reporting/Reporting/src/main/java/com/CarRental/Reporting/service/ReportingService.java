package com.CarRental.Reporting.service;

import com.CarRental.Reporting.dto.BookingDto;
import com.CarRental.Reporting.dto.UserDto;
import com.CarRental.Reporting.dto.VehicleDto;
import com.CarRental.Reporting.model.CustomerReport;
import com.CarRental.Reporting.model.RevenueReport;
import com.CarRental.Reporting.model.VehicleUsageReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportingService {

    private static final String STATUS_ACTIVE = "ACTIVE";
    private static final String STATUS_COMPLETED = "COMPLETED";
    private static final String STATUS_VERFUGBAR = "VERFÜGBAR";
    private static final String STATUS_VERMIETET = "VERMIETET";
    
    // Customer Status Thresholds
    private static final double PREMIUM_SPENDING_THRESHOLD = 5000.0;
    private static final int PREMIUM_BOOKING_THRESHOLD = 20;
    private static final double ACTIVE_SPENDING_THRESHOLD = 1000.0;
    private static final int ACTIVE_BOOKING_THRESHOLD = 5;

    @Autowired
    private ExternalDataService externalDataService;

    // Revenue Reports
    public RevenueReport generateDailyRevenueReport(LocalDate date) {
        List<BookingDto> bookings = externalDataService.getAllBookings();
        
        BigDecimal dailyRevenue = bookings.stream()
            .filter(booking -> booking.getStartDate().equals(date) || 
                             (booking.getStartDate().isBefore(date) && booking.getEndDate().isAfter(date)))
            .filter(booking -> STATUS_COMPLETED.equals(booking.getStatus()) || STATUS_ACTIVE.equals(booking.getStatus()))
            .map(BookingDto::getTotalPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        int totalBookings = (int) bookings.stream()
            .filter(booking -> booking.getStartDate().equals(date))
            .count();

        int activeRentals = (int) bookings.stream()
            .filter(booking -> STATUS_ACTIVE.equals(booking.getStatus()))
            .filter(booking -> booking.getStartDate().isBefore(date.plusDays(1)) && 
                             booking.getEndDate().isAfter(date.minusDays(1)))
            .count();

        int completedRentals = (int) bookings.stream()
            .filter(booking -> STATUS_COMPLETED.equals(booking.getStatus()))
            .filter(booking -> booking.getEndDate().equals(date))
            .count();

        RevenueReport report = new RevenueReport();
        report.setReportDate(date);
        report.setDailyRevenue(dailyRevenue);
        report.setTotalBookings(totalBookings);
        report.setActiveRentals(activeRentals);
        report.setCompletedRentals(completedRentals);

        return report;
    }

    public List<RevenueReport> getRevenueReportsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<RevenueReport> reports = new ArrayList<>();
        LocalDate currentDate = startDate;
        
        while (!currentDate.isAfter(endDate)) {
            RevenueReport dailyReport = generateDailyRevenueReport(currentDate);
            reports.add(dailyReport);
            currentDate = currentDate.plusDays(1);
        }
        
        return reports;
    }

    // Vehicle Usage Reports
    public VehicleUsageReport generateVehicleUsageReport(Long vehicleId, LocalDate reportDate) {
        VehicleDto vehicle = externalDataService.getVehicleById(vehicleId);
        if (vehicle == null) return null;

        List<BookingDto> vehicleBookings = externalDataService.getAllBookings().stream()
            .filter(booking -> booking.getVehicleId().equals(vehicleId))
            .collect(Collectors.toList());

        int totalRentals = vehicleBookings.size();
        
        int totalRentalDays = vehicleBookings.stream()
            .mapToInt(booking -> (int) ChronoUnit.DAYS.between(booking.getStartDate(), booking.getEndDate()) + 1)
            .sum();

        // Berechne Auslastungsrate (vereinfacht als Prozentsatz der gemieteten Tage)
        long totalDaysSinceFirstBooking = vehicleBookings.isEmpty() ? 1 : 
            ChronoUnit.DAYS.between(
                vehicleBookings.stream().map(BookingDto::getStartDate).min(LocalDate::compareTo).orElse(reportDate),
                reportDate
            ) + 1;
        
        double utilizationRate = totalDaysSinceFirstBooking > 0 ? 
            (totalRentalDays * 100.0) / totalDaysSinceFirstBooking : 0.0;

        VehicleUsageReport report = new VehicleUsageReport();
        report.setVehicleId(vehicleId);
        report.setVehicleModel(vehicle.getModell());
        report.setVehicleBrand(vehicle.getMarke());
        report.setTotalRentals(totalRentals);
        report.setTotalRentalDays(totalRentalDays);
        report.setUtilizationRate(Math.min(utilizationRate, 100.0)); // Cap at 100%
        report.setReportDate(reportDate);
        report.setAvailable(vehicle.isAvailable());

        return report;
    }

    public List<VehicleUsageReport> getTopPerformingVehicles() {
        List<VehicleDto> allVehicles = externalDataService.getAllVehicles();
        List<VehicleUsageReport> reports = new ArrayList<>();
        
        for (VehicleDto vehicle : allVehicles) {
            VehicleUsageReport report = generateVehicleUsageReport(vehicle.getId(), LocalDate.now());
            if (report != null) {
                reports.add(report);
            }
        }
        
        return reports.stream()
            .sorted((r1, r2) -> Integer.compare(r2.getTotalRentals(), r1.getTotalRentals()))
            .collect(Collectors.toList());
    }

    // Customer Reports
    public CustomerReport generateCustomerReport(Long customerId) {
        UserDto user = externalDataService.getUserById(customerId);
        if (user == null) return null;

        List<BookingDto> customerBookings = externalDataService.getAllBookings().stream()
            .filter(booking -> booking.getCustomerId().equals(customerId))
            .collect(Collectors.toList());

        int totalBookings = customerBookings.size();
        
        double totalSpent = customerBookings.stream()
            .filter(booking -> STATUS_COMPLETED.equals(booking.getStatus()))
            .mapToDouble(booking -> booking.getTotalPrice().doubleValue())
            .sum();

        LocalDate lastBookingDate = customerBookings.stream()
            .map(BookingDto::getStartDate)
            .max(LocalDate::compareTo)
            .orElse(null);

        String customerStatus = determineCustomerStatus(totalSpent, totalBookings);

        CustomerReport report = new CustomerReport();
        report.setCustomerId(customerId);
        report.setCustomerName(user.getName());
        report.setCustomerEmail(user.getEmail());
        report.setTotalBookings(totalBookings);
        report.setTotalSpent(totalSpent);
        report.setLastBookingDate(lastBookingDate);
        report.setRegistrationDate(LocalDate.now()); // Vereinfacht eigentlich würde das aus der DB kommen
        report.setCustomerStatus(customerStatus);

        return report;
    }

    private String determineCustomerStatus(double totalSpent, int totalBookings) {
        if (totalSpent > PREMIUM_SPENDING_THRESHOLD || totalBookings > PREMIUM_BOOKING_THRESHOLD) {
            return "PREMIUM";
        } else if (totalSpent > ACTIVE_SPENDING_THRESHOLD || totalBookings > ACTIVE_BOOKING_THRESHOLD) {
            return STATUS_ACTIVE;
        } else {
            return "INACTIVE";
        }
    }

    public List<CustomerReport> getTopCustomers() {
        List<UserDto> allUsers = externalDataService.getAllUsers();
        List<CustomerReport> reports = new ArrayList<>();
        
        for (UserDto user : allUsers) {
            CustomerReport report = generateCustomerReport(user.getId());
            if (report != null && report.getTotalSpent() >= ACTIVE_SPENDING_THRESHOLD) {
                reports.add(report);
            }
        }
        
        return reports.stream()
            .sorted((r1, r2) -> Double.compare(r2.getTotalSpent(), r1.getTotalSpent()))
            .collect(Collectors.toList());
    }

    // Comprehensive Statistics
    public Map<String, Object> getComprehensiveStatistics() {
        Map<String, Object> stats = new HashMap<>();
        
        LocalDate today = LocalDate.now();
        List<BookingDto> allBookings = externalDataService.getAllBookings();
        List<VehicleDto> allVehicles = externalDataService.getAllVehicles();
        List<UserDto> allUsers = externalDataService.getAllUsers();

        // Revenue statistics
        BigDecimal totalRevenue = allBookings.stream()
            .filter(booking -> STATUS_COMPLETED.equals(booking.getStatus()))
            .map(BookingDto::getTotalPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Booking statistics
        long activeBookings = allBookings.stream()
            .filter(booking -> STATUS_ACTIVE.equals(booking.getStatus()))
            .count();

        long completedBookings = allBookings.stream()
            .filter(booking -> STATUS_COMPLETED.equals(booking.getStatus()))
            .count();

        // Vehicle statistics
        long availableVehicles = allVehicles.stream()
            .filter(VehicleDto::isAvailable)
            .count();

        long rentedVehicles = allVehicles.stream()
            .filter(vehicle -> !vehicle.isAvailable())
            .count();

        stats.put("totalRevenue", totalRevenue);
        stats.put("totalBookings", allBookings.size());
        stats.put("activeBookings", activeBookings);
        stats.put("completedBookings", completedBookings);
        stats.put("totalVehicles", allVehicles.size());
        stats.put("availableVehicles", availableVehicles);
        stats.put("rentedVehicles", rentedVehicles);
        stats.put("totalCustomers", allUsers.size());
        stats.put("reportGeneratedAt", today);

        return stats;
    }
}
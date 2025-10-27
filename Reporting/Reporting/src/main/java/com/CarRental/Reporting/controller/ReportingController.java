package com.CarRental.Reporting.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CarRental.Reporting.model.CustomerReport;
import com.CarRental.Reporting.model.RevenueReport;
import com.CarRental.Reporting.model.VehicleUsageReport;
import com.CarRental.Reporting.service.ReportingService;

@RestController
@RequestMapping("/reporting")
@CrossOrigin(origins = "*")
public class ReportingController {

    @Autowired
    private ReportingService reportingService;

    // Revenue Report Endpoints
    @PostMapping("/revenue/daily")
    public ResponseEntity<RevenueReport> generateDailyRevenueReport(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        RevenueReport report = reportingService.generateDailyRevenueReport(date);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/revenue/range")
    public ResponseEntity<List<RevenueReport>> getRevenueReportsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<RevenueReport> reports = reportingService.getRevenueReportsByDateRange(startDate, endDate);
        return ResponseEntity.ok(reports);
    }

    // Vehicle Usage Report Endpoints
    @PostMapping("/vehicles/{vehicleId}/usage")
    public ResponseEntity<VehicleUsageReport> generateVehicleUsageReport(
            @PathVariable Long vehicleId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reportDate) {
        VehicleUsageReport report = reportingService.generateVehicleUsageReport(vehicleId, reportDate);
        if (report == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(report);
    }

    @GetMapping("/vehicles/top-performing")
    public ResponseEntity<List<VehicleUsageReport>> getTopPerformingVehicles() {
        List<VehicleUsageReport> reports = reportingService.getTopPerformingVehicles();
        return ResponseEntity.ok(reports);
    }

    // Customer Report Endpoints
    @PostMapping("/customers/{customerId}")
    public ResponseEntity<CustomerReport> generateCustomerReport(@PathVariable Long customerId) {
        CustomerReport report = reportingService.generateCustomerReport(customerId);
        if (report == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(report);
    }

    @GetMapping("/customers/top")
    public ResponseEntity<List<CustomerReport>> getTopCustomers() {
        List<CustomerReport> reports = reportingService.getTopCustomers();
        return ResponseEntity.ok(reports);
    }

    // Comprehensive Statistics
    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> getComprehensiveStatistics() {
        Map<String, Object> statistics = reportingService.getComprehensiveStatistics();
        return ResponseEntity.ok(statistics);
    }

    // Health Check
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Reporting Service is running");
    }
}
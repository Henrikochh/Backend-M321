package com.CarRental.Reporting.service;

import com.CarRental.Reporting.dto.BookingDto;
import com.CarRental.Reporting.dto.UserDto;
import com.CarRental.Reporting.dto.VehicleDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.Collections;

@Service
public class ExternalDataService {

    private static final Logger logger = LoggerFactory.getLogger(ExternalDataService.class);
    
    private final RestTemplate restTemplate;

    @Value("${services.benutzerverwaltung.url}")
    private String userServiceUrl;

    @Value("${services.fahrzeugverwaltung.url}")
    private String vehicleServiceUrl;

    @Value("${services.buchungssystem.url}")
    private String bookingServiceUrl;

    public ExternalDataService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<UserDto> getAllUsers() {
        try {
            ResponseEntity<List<UserDto>> response = restTemplate.exchange(
                userServiceUrl + "/users",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserDto>>() {}
            );
            return response.getBody() != null ? response.getBody() : Collections.emptyList();
        } catch (RestClientException e) {
            logger.error("Fehler beim Abrufen der Benutzerdaten: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<VehicleDto> getAllVehicles() {
        try {
            ResponseEntity<List<VehicleDto>> response = restTemplate.exchange(
                vehicleServiceUrl + "/api/fahrzeuge",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<VehicleDto>>() {}
            );
            return response.getBody() != null ? response.getBody() : Collections.emptyList();
        } catch (RestClientException e) {
            logger.error("Fehler beim Abrufen der Fahrzeugdaten: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<BookingDto> getAllBookings() {
        try {
            ResponseEntity<List<BookingDto>> response = restTemplate.exchange(
                bookingServiceUrl + "/bookings",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BookingDto>>() {}
            );
            return response.getBody() != null ? response.getBody() : Collections.emptyList();
        } catch (RestClientException e) {
            logger.error("Fehler beim Abrufen der Buchungsdaten: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public UserDto getUserById(Long userId) {
        try {
            return restTemplate.getForObject(userServiceUrl + "/users/" + userId, UserDto.class);
        } catch (RestClientException e) {
            logger.error("Fehler beim Abrufen der Benutzerdaten für ID " + userId + ": " + e.getMessage());
            return null;
        }
    }

    public VehicleDto getVehicleById(Long vehicleId) {
        try {
            return restTemplate.getForObject(vehicleServiceUrl + "/api/fahrzeuge/" + vehicleId, VehicleDto.class);
        } catch (RestClientException e) {
            logger.error("Fehler beim Abrufen der Fahrzeugdaten für ID " + vehicleId + ": " + e.getMessage());
            return null;
        }
    }
}
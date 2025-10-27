package com.CarRental.Reporting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {
    private Long id;
    private String marke;
    private String modell;
    private Integer baujahr;
    private String kennzeichen;
    private Double preisProTag;
    private String status; // VERFÜGBAR, VERMIETET, WARTUNG
}
package com.example.autovermietung.vehicles.model.entity;

import com.example.autovermietung.vehicles.model.FahrzeugStatus;
import jakarta.persistence.*;

@Entity
public class Fahrzeug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marke;
    private String modell;
    private int baujahr;
    private String kennzeichen;
    private double preisProTag;

    @Enumerated(EnumType.STRING)
    private FahrzeugStatus status;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public int getBaujahr() {
        return baujahr;
    }

    public void setBaujahr(int baujahr) {
        this.baujahr = baujahr;
    }

    public String getKennzeichen() {
        return kennzeichen;
    }

    public void setKennzeichen(String kennzeichen) {
        this.kennzeichen = kennzeichen;
    }

    public double getPreisProTag() {
        return preisProTag;
    }

    public void setPreisProTag(double preisProTag) {
        this.preisProTag = preisProTag;
    }

    public FahrzeugStatus getStatus() {
        return status;
    }

    public void setStatus(FahrzeugStatus status) {
        this.status = status;
    }
}

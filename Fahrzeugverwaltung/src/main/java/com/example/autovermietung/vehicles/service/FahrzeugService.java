package com.example.autovermietung.vehicles.service;

import com.example.autovermietung.vehicles.model.entity.Fahrzeug;
import com.example.autovermietung.vehicles.repository.FahrzeugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FahrzeugService {

    @Autowired
    private FahrzeugRepository fahrzeugRepository;

    public List<Fahrzeug> getAllFahrzeuge() {
        return fahrzeugRepository.findAll();
    }

    public Optional<Fahrzeug> getFahrzeugById(Long id) {
        return fahrzeugRepository.findById(id);
    }

    public Fahrzeug createFahrzeug(Fahrzeug fahrzeug) {
        return fahrzeugRepository.save(fahrzeug);
    }

    public Fahrzeug updateFahrzeug(Long id, Fahrzeug fahrzeugDetails) {
        Fahrzeug fahrzeug = fahrzeugRepository.findById(id).orElseThrow(() -> new RuntimeException("Fahrzeug not found"));

        fahrzeug.setMarke(fahrzeugDetails.getMarke());
        fahrzeug.setModell(fahrzeugDetails.getModell());
        fahrzeug.setBaujahr(fahrzeugDetails.getBaujahr());
        fahrzeug.setKennzeichen(fahrzeugDetails.getKennzeichen());
        fahrzeug.setPreisProTag(fahrzeugDetails.getPreisProTag());
        fahrzeug.setAvailable(fahrzeugDetails.isAvailable());

        return fahrzeugRepository.save(fahrzeug);
    }

    public void deleteFahrzeug(Long id) {
        fahrzeugRepository.deleteById(id);
    }
}

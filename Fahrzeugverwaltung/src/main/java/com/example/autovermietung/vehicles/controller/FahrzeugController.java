package com.example.autovermietung.vehicles.controller;

import com.example.autovermietung.vehicles.model.entity.Fahrzeug;
import com.example.autovermietung.vehicles.service.FahrzeugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fahrzeuge")
public class FahrzeugController {

    @Autowired
    private FahrzeugService fahrzeugService;

    @GetMapping
    public List<Fahrzeug> getAllFahrzeuge() {
        return fahrzeugService.getAllFahrzeuge();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fahrzeug> getFahrzeugById(@PathVariable Long id) {
        return fahrzeugService.getFahrzeugById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Fahrzeug createFahrzeug(@RequestBody Fahrzeug fahrzeug) {
        return fahrzeugService.createFahrzeug(fahrzeug);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fahrzeug> updateFahrzeug(@PathVariable Long id, @RequestBody Fahrzeug fahrzeug) {
        try {
            return ResponseEntity.ok(fahrzeugService.updateFahrzeug(id, fahrzeug));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFahrzeug(@PathVariable Long id) {
        fahrzeugService.deleteFahrzeug(id);
        return ResponseEntity.noContent().build();
    }
}

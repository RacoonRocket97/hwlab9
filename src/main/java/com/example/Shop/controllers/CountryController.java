package com.example.Shop.controllers;

import com.example.Shop.dto.CountryDto;
import com.example.Shop.services.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/countries")
public class CountryController {
    private final CountryService service;

    @GetMapping
    public ResponseEntity<?> getCountries() {
        List<CountryDto> countries = service.getCountries();
        if (countries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCountry(@PathVariable Long id) {
        CountryDto country = service.getCountry(id);
        if (country == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(country);
    }

    @PostMapping
    public ResponseEntity<?> addCountry(@RequestBody CountryDto dto) {
        CountryDto country = service.addCountry(dto);
        return new ResponseEntity<>(country, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCountry(@PathVariable Long id, @RequestBody CountryDto dto) {
        CountryDto country = service.updateCountry(id, dto);
        if (country == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(country);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCountry(@PathVariable Long id) {
        boolean deleted = service.deleteCountry(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
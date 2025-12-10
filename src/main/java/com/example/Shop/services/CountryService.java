package com.example.Shop.services;

import com.example.Shop.dto.CountryDto;
import com.example.Shop.mappers.CountryMap;
import com.example.Shop.models.Country;
import com.example.Shop.repositories.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryMap mapper;
    private final CountryRepository countryRepository;

    public List<CountryDto> getCountries() {
        List<Country> countries = countryRepository.findAll();
        return mapper.toDtoList(countries);
    }

    public CountryDto getCountry(Long id) {
        Country country = countryRepository.findById(id).orElse(null);
        if (country == null) {
            return null;
        }
        return mapper.toDto(country);
    }

    public CountryDto updateCountry(Long id, CountryDto dto) {
        Country country = countryRepository.findById(id).orElse(null);
        if (country == null) {
            return null;
        }
        country.setName(dto.getCountryName());
        country.setCode(dto.getCountryCode());

        Country updated = countryRepository.save(country);
        return mapper.toDto(updated);
    }

    public CountryDto addCountry(CountryDto dto) {
        Country ent = mapper.toEntity(dto);
        Country saved = countryRepository.save(ent);
        return mapper.toDto(saved);
    }

    public boolean deleteCountry(Long id) {
        if (!countryRepository.existsById(id)) {
            return false;
        }
        countryRepository.deleteById(id);
        return true;
    }}

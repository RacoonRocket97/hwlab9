package com.example.Shop.services;

import com.example.Shop.dto.CountryDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    @Test
    void getAllCountriesTest() {
        List<CountryDto> countries = countryService.getCountries();

        Assertions.assertNotNull(countries);
        Assertions.assertNotEquals(0, countries.size());

        for (CountryDto dto : countries) {
            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getCountryId());
            Assertions.assertNotNull(dto.getCountryName());
            Assertions.assertNotNull(dto.getCountryCode());
        }
    }

    @Test
    void getCountryByIdTest() {
        Random random = new Random();
        int randomIndex = random.nextInt(countryService.getCountries().size());
        Long someCountryId = countryService.getCountries().get(randomIndex).getCountryId();

        CountryDto dto = countryService.getCountry(someCountryId);

        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getCountryId());
        Assertions.assertNotNull(dto.getCountryName());
        Assertions.assertNotNull(dto.getCountryCode());

        CountryDto mockCountry = countryService.getCountry(-1L);
        Assertions.assertNull(mockCountry);
    }

    @Test
    void createCountryTest() {
        CountryDto dto = new CountryDto();
        dto.setCountryName("France");
        dto.setCountryCode("FR");

        CountryDto createdCountry = countryService.addCountry(dto);

        Assertions.assertNotNull(createdCountry);
        Assertions.assertNotNull(createdCountry.getCountryId());
        Assertions.assertNotEquals(0L, createdCountry.getCountryId());
        Assertions.assertNotNull(createdCountry.getCountryName());
        Assertions.assertNotNull(createdCountry.getCountryCode());

        Assertions.assertEquals(dto.getCountryName(), createdCountry.getCountryName());
        Assertions.assertEquals(dto.getCountryCode(), createdCountry.getCountryCode());

        CountryDto checkCountry = countryService.getCountry(createdCountry.getCountryId());

        Assertions.assertNotNull(checkCountry);
        Assertions.assertNotNull(checkCountry.getCountryId());
        Assertions.assertNotNull(checkCountry.getCountryName());
        Assertions.assertNotNull(checkCountry.getCountryCode());

        Assertions.assertEquals(checkCountry.getCountryId(), createdCountry.getCountryId());
        Assertions.assertEquals(checkCountry.getCountryName(), createdCountry.getCountryName());
        Assertions.assertEquals(checkCountry.getCountryCode(), createdCountry.getCountryCode());
    }

    @Test
    void updateCountryTest() {
        Random random = new Random();
        int randomIndex = random.nextInt(countryService.getCountries().size());
        Long someCountryId = countryService.getCountries().get(randomIndex).getCountryId();

        CountryDto dto = new CountryDto();
        dto.setCountryId(someCountryId);
        dto.setCountryName("Updated Country");
        dto.setCountryCode("UC");

        CountryDto updatedCountry = countryService.updateCountry(dto.getCountryId(), dto);

        Assertions.assertNotNull(updatedCountry);
        Assertions.assertNotNull(updatedCountry.getCountryId());
        Assertions.assertNotNull(updatedCountry.getCountryName());
        Assertions.assertNotNull(updatedCountry.getCountryCode());

        Assertions.assertEquals(updatedCountry.getCountryId(), dto.getCountryId());
        Assertions.assertEquals(updatedCountry.getCountryName(), dto.getCountryName());
        Assertions.assertEquals(updatedCountry.getCountryCode(), dto.getCountryCode());

        CountryDto checkCountry = countryService.getCountry(updatedCountry.getCountryId());

        Assertions.assertNotNull(checkCountry);
        Assertions.assertNotNull(checkCountry.getCountryId());
        Assertions.assertNotNull(checkCountry.getCountryName());
        Assertions.assertNotNull(checkCountry.getCountryCode());

        Assertions.assertEquals(checkCountry.getCountryId(), updatedCountry.getCountryId());
        Assertions.assertEquals(checkCountry.getCountryName(), updatedCountry.getCountryName());
        Assertions.assertEquals(checkCountry.getCountryCode(), updatedCountry.getCountryCode());

        CountryDto mockCountry = countryService.updateCountry(-1L, dto);
        Assertions.assertNull(mockCountry);
    }

    @Test
    void deleteCountryTest() {
        CountryDto dto = new CountryDto();
        dto.setCountryName("ToDelete");
        dto.setCountryCode("TD");

        CountryDto createdCountry = countryService.addCountry(dto);
        Long countryId = createdCountry.getCountryId();

        boolean deleted = countryService.deleteCountry(countryId);
        Assertions.assertTrue(deleted);

        CountryDto checkCountry = countryService.getCountry(countryId);
        Assertions.assertNull(checkCountry);

        boolean mockDeleted = countryService.deleteCountry(countryId);
        Assertions.assertFalse(mockDeleted);
    }
}
package com.example.Shop.mappers;

import com.example.Shop.dto.CountryDto;
import com.example.Shop.models.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CountryMapperTest {

    @Autowired
    private CountryMap countryMapper;

    @Test
    void convertEntityToDtoTest() {
        Country country = new Country();
        country.setId(1L);
        country.setName("United States");
        country.setCode("US");

        CountryDto dto = countryMapper.toDto(country);

        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getCountryId());
        Assertions.assertNotNull(dto.getCountryName());
        Assertions.assertNotNull(dto.getCountryCode());

        Assertions.assertEquals(country.getId(), dto.getCountryId());
        Assertions.assertEquals(country.getName(), dto.getCountryName());
        Assertions.assertEquals(country.getCode(), dto.getCountryCode());
    }

    @Test
    void convertDtoToEntityTest() {
        CountryDto dto = new CountryDto();
        dto.setCountryId(1L);
        dto.setCountryName("Germany");
        dto.setCountryCode("DE");

        Country country = countryMapper.toEntity(dto);

        Assertions.assertNotNull(country);
        Assertions.assertNotNull(country.getId());
        Assertions.assertNotNull(country.getName());
        Assertions.assertNotNull(country.getCode());

        Assertions.assertEquals(dto.getCountryId(), country.getId());
        Assertions.assertEquals(dto.getCountryName(), country.getName());
        Assertions.assertEquals(dto.getCountryCode(), country.getCode());
    }

    @Test
    void convertEntityListToDtoListTest() {
        List<Country> countries = new ArrayList<>();

        Country c1 = new Country();
        c1.setId(1L);
        c1.setName("United States");
        c1.setCode("US");
        countries.add(c1);

        Country c2 = new Country();
        c2.setId(2L);
        c2.setName("Germany");
        c2.setCode("DE");
        countries.add(c2);

        Country c3 = new Country();
        c3.setId(3L);
        c3.setName("Japan");
        c3.setCode("JP");
        countries.add(c3);

        List<CountryDto> dtoList = countryMapper.toDtoList(countries);

        Assertions.assertNotNull(dtoList);
        Assertions.assertNotEquals(0, dtoList.size());
        Assertions.assertEquals(countries.size(), dtoList.size());

        for (int i = 0; i < countries.size(); i++) {
            Country country = countries.get(i);
            CountryDto dto = dtoList.get(i);

            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getCountryId());
            Assertions.assertNotNull(dto.getCountryName());
            Assertions.assertNotNull(dto.getCountryCode());

            Assertions.assertEquals(country.getId(), dto.getCountryId());
            Assertions.assertEquals(country.getName(), dto.getCountryName());
            Assertions.assertEquals(country.getCode(), dto.getCountryCode());
        }
    }
}
package com.example.Shop.mappers;

import com.example.Shop.dto.CountryDto;
import com.example.Shop.models.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMap {
    @Mapping(source = "countryId", target = "id")
    @Mapping(source = "countryName", target = "name")
    @Mapping(source = "countryCode", target = "code")
    Country toEntity(CountryDto dto);

    @Mapping(source = "id", target = "countryId")
    @Mapping(source = "name", target = "countryName")
    @Mapping(source = "code", target = "countryCode")
    CountryDto toDto(Country country);

    List<CountryDto> toDtoList(List<Country> countries);
}
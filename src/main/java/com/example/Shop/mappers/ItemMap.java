package com.example.Shop.mappers;

import com.example.Shop.dto.ItemDto;
import com.example.Shop.models.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ItemMap {
    @Mapping(source = "id", target = "itemId")
    @Mapping(source = "name", target = "itemName")
    @Mapping(source = "price", target = "itemPrice")
    @Mapping(source = "quantity", target = "itemQuantity")
    @Mapping(source = "manufacturer", target = "itemManufacturer")
    ItemDto toDto(Item item);

    @Mapping(source = "itemId", target = "id")
    @Mapping(source = "itemName", target = "name")
    @Mapping(source = "itemPrice", target = "price")
    @Mapping(source = "itemQuantity", target = "quantity")
    @Mapping(source = "itemManufacturer", target = "manufacturer")
    Item toEntity(ItemDto itemDto);

    List<ItemDto> toDtoList(List<Item> items);
}
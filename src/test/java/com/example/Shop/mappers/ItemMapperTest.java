package com.example.Shop.mappers;

import com.example.Shop.dto.ItemDto;
import com.example.Shop.models.Country;
import com.example.Shop.models.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ItemMapperTest {

    @Autowired
    private ItemMap itemMapper;

    @Test
    void convertEntityToDtoTest() {
        Country country = new Country();
        country.setId(1L);
        country.setName("United States");
        country.setCode("US");

        Item item = new Item();
        item.setId(1L);
        item.setName("Oreo");
        item.setPrice(12);
        item.setQuantity(123);
        item.setManufacturer(country);

        ItemDto dto = itemMapper.toDto(item);

        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getItemId());
        Assertions.assertNotNull(dto.getItemName());
        Assertions.assertNotNull(dto.getItemPrice());
        Assertions.assertNotNull(dto.getItemQuantity());
        Assertions.assertNotNull(dto.getItemManufacturer());

        Assertions.assertEquals(item.getId(), dto.getItemId());
        Assertions.assertEquals(item.getName(), dto.getItemName());
        Assertions.assertEquals(item.getPrice(), dto.getItemPrice());
        Assertions.assertEquals(item.getQuantity(), dto.getItemQuantity());
        Assertions.assertEquals(item.getManufacturer().getId(), dto.getItemManufacturer().getId());
    }

    @Test
    void convertDtoToEntityTest() {
        Country country = new Country();
        country.setId(2L);
        country.setName("Germany");
        country.setCode("DE");

        ItemDto dto = new ItemDto();
        dto.setItemId(2L);
        dto.setItemName("KitKat");
        dto.setItemPrice(6);
        dto.setItemQuantity(200);
        dto.setItemManufacturer(country);

        Item item = itemMapper.toEntity(dto);

        Assertions.assertNotNull(item);
        Assertions.assertNotNull(item.getId());
        Assertions.assertNotNull(item.getName());
        Assertions.assertNotNull(item.getPrice());
        Assertions.assertNotNull(item.getQuantity());
        Assertions.assertNotNull(item.getManufacturer());

        Assertions.assertEquals(dto.getItemId(), item.getId());
        Assertions.assertEquals(dto.getItemName(), item.getName());
        Assertions.assertEquals(dto.getItemPrice(), item.getPrice());
        Assertions.assertEquals(dto.getItemQuantity(), item.getQuantity());
        Assertions.assertEquals(dto.getItemManufacturer().getId(), item.getManufacturer().getId());
    }

    @Test
    void convertEntityListToDtoListTest() {
        Country country1 = new Country();
        country1.setId(1L);
        country1.setName("United States");
        country1.setCode("US");

        Country country2 = new Country();
        country2.setId(2L);
        country2.setName("Germany");
        country2.setCode("DE");

        List<Item> items = new ArrayList<>();

        Item item1 = new Item();
        item1.setId(1L);
        item1.setName("Oreo");
        item1.setPrice(12);
        item1.setQuantity(123);
        item1.setManufacturer(country1);
        items.add(item1);

        Item item2 = new Item();
        item2.setId(2L);
        item2.setName("Skittles");
        item2.setPrice(5);
        item2.setQuantity(230);
        item2.setManufacturer(country1);
        items.add(item2);

        Item item3 = new Item();
        item3.setId(3L);
        item3.setName("KitKat");
        item3.setPrice(6);
        item3.setQuantity(200);
        item3.setManufacturer(country2);
        items.add(item3);

        List<ItemDto> dtoList = itemMapper.toDtoList(items);

        Assertions.assertNotNull(dtoList);
        Assertions.assertNotEquals(0, dtoList.size());
        Assertions.assertEquals(items.size(), dtoList.size());

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            ItemDto dto = dtoList.get(i);

            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getItemId());
            Assertions.assertNotNull(dto.getItemName());
            Assertions.assertNotNull(dto.getItemPrice());
            Assertions.assertNotNull(dto.getItemQuantity());

            Assertions.assertEquals(item.getId(), dto.getItemId());
            Assertions.assertEquals(item.getName(), dto.getItemName());
            Assertions.assertEquals(item.getPrice(), dto.getItemPrice());
            Assertions.assertEquals(item.getQuantity(), dto.getItemQuantity());
        }
    }
}
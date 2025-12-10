package com.example.Shop.services;

import com.example.Shop.dto.ItemDto;
import com.example.Shop.models.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CountryService countryService;

    @Test
    void getAllItemsTest() {
        List<ItemDto> items = itemService.getItems();

        Assertions.assertNotNull(items);
        Assertions.assertNotEquals(0, items.size());

        for (ItemDto dto : items) {
            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getItemId());
            Assertions.assertNotNull(dto.getItemName());
            Assertions.assertNotNull(dto.getItemPrice());
            Assertions.assertNotNull(dto.getItemQuantity());
        }
    }

    @Test
    void getItemByIdTest() {
        Random random = new Random();
        int randomIndex = random.nextInt(itemService.getItems().size());
        Long someItemId = itemService.getItems().get(randomIndex).getItemId();

        ItemDto dto = itemService.getItem(someItemId);

        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getItemId());
        Assertions.assertNotNull(dto.getItemName());
        Assertions.assertNotNull(dto.getItemPrice());
        Assertions.assertNotNull(dto.getItemQuantity());

        ItemDto mockItem = itemService.getItem(-1L);
        Assertions.assertNull(mockItem);
    }

    @Test
    void createItemTest() {
        Country manufacturer = new Country();
        manufacturer.setId(1L);
        manufacturer.setName("United States");
        manufacturer.setCode("US");

        ItemDto dto = new ItemDto();
        dto.setItemName("M&Ms");
        dto.setItemPrice(8);
        dto.setItemQuantity(150);
        dto.setItemManufacturer(manufacturer);

        ItemDto createdItem = itemService.addItem(dto);

        Assertions.assertNotNull(createdItem);
        Assertions.assertNotNull(createdItem.getItemId());
        Assertions.assertNotEquals(0L, createdItem.getItemId());
        Assertions.assertNotNull(createdItem.getItemName());
        Assertions.assertNotNull(createdItem.getItemPrice());
        Assertions.assertNotNull(createdItem.getItemQuantity());

        Assertions.assertEquals(dto.getItemName(), createdItem.getItemName());
        Assertions.assertEquals(dto.getItemPrice(), createdItem.getItemPrice());
        Assertions.assertEquals(dto.getItemQuantity(), createdItem.getItemQuantity());

        ItemDto checkItem = itemService.getItem(createdItem.getItemId());

        Assertions.assertNotNull(checkItem);
        Assertions.assertNotNull(checkItem.getItemId());
        Assertions.assertNotNull(checkItem.getItemName());
        Assertions.assertNotNull(checkItem.getItemPrice());
        Assertions.assertNotNull(checkItem.getItemQuantity());

        Assertions.assertEquals(checkItem.getItemId(), createdItem.getItemId());
        Assertions.assertEquals(checkItem.getItemName(), createdItem.getItemName());
        Assertions.assertEquals(checkItem.getItemPrice(), createdItem.getItemPrice());
        Assertions.assertEquals(checkItem.getItemQuantity(), createdItem.getItemQuantity());
    }

    @Test
    void updateItemTest() {
        Random random = new Random();
        int randomIndex = random.nextInt(itemService.getItems().size());
        Long someItemId = itemService.getItems().get(randomIndex).getItemId();

        Country manufacturer = new Country();
        manufacturer.setId(2L);
        manufacturer.setName("Germany");
        manufacturer.setCode("DE");

        ItemDto dto = new ItemDto();
        dto.setItemId(someItemId);
        dto.setItemName("Updated Item");
        dto.setItemPrice(999);
        dto.setItemQuantity(50);
        dto.setItemManufacturer(manufacturer);

        ItemDto updatedItem = itemService.updateItem(dto.getItemId(), dto);

        Assertions.assertNotNull(updatedItem);
        Assertions.assertNotNull(updatedItem.getItemId());
        Assertions.assertNotNull(updatedItem.getItemName());
        Assertions.assertNotNull(updatedItem.getItemPrice());
        Assertions.assertNotNull(updatedItem.getItemQuantity());

        Assertions.assertEquals(updatedItem.getItemId(), dto.getItemId());
        Assertions.assertEquals(updatedItem.getItemName(), dto.getItemName());
        Assertions.assertEquals(updatedItem.getItemPrice(), dto.getItemPrice());
        Assertions.assertEquals(updatedItem.getItemQuantity(), dto.getItemQuantity());

        ItemDto checkItem = itemService.getItem(updatedItem.getItemId());

        Assertions.assertNotNull(checkItem);
        Assertions.assertNotNull(checkItem.getItemId());
        Assertions.assertNotNull(checkItem.getItemName());
        Assertions.assertNotNull(checkItem.getItemPrice());
        Assertions.assertNotNull(checkItem.getItemQuantity());

        Assertions.assertEquals(checkItem.getItemId(), updatedItem.getItemId());
        Assertions.assertEquals(checkItem.getItemName(), updatedItem.getItemName());
        Assertions.assertEquals(checkItem.getItemPrice(), updatedItem.getItemPrice());
        Assertions.assertEquals(checkItem.getItemQuantity(), updatedItem.getItemQuantity());

        ItemDto mockItem = itemService.updateItem(-1L, dto);
        Assertions.assertNull(mockItem);
    }

    @Test
    void deleteItemTest() {
        Country manufacturer = new Country();
        manufacturer.setId(1L);

        ItemDto dto = new ItemDto();
        dto.setItemName("ToDelete");
        dto.setItemPrice(1);
        dto.setItemQuantity(1);
        dto.setItemManufacturer(manufacturer);

        ItemDto createdItem = itemService.addItem(dto);
        Long itemId = createdItem.getItemId();

        boolean deleted = itemService.deleteItem(itemId);
        Assertions.assertTrue(deleted);

        ItemDto checkItem = itemService.getItem(itemId);
        Assertions.assertNull(checkItem);

        boolean mockDeleted = itemService.deleteItem(itemId);
        Assertions.assertFalse(mockDeleted);
    }

    @Test
    void verifyManyToOneRelationshipTest() {
        List<ItemDto> items = itemService.getItems();

        for (ItemDto item : items) {
            if (item.getItemManufacturer() != null) {
                Country manufacturer = item.getItemManufacturer();
                Assertions.assertNotNull(manufacturer);
                Assertions.assertNotNull(manufacturer.getId());
                Assertions.assertNotNull(manufacturer.getName());
                Assertions.assertNotNull(manufacturer.getCode());
            }
        }
    }
}
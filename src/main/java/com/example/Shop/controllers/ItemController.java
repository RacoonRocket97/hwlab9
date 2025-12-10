package com.example.Shop.controllers;

import com.example.Shop.dto.ItemDto;
import com.example.Shop.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<List<ItemDto>> getAllItems() {
        List<ItemDto> items = itemService.getItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable Long id) {
        ItemDto item = itemService.getItem(id);
        if (item != null) {
            return ResponseEntity.ok(item);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemDto dto) {
        ItemDto createdItem = itemService.addItem(dto);
        return ResponseEntity.ok(createdItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDto> updateItem(@PathVariable Long id, @RequestBody ItemDto itemDTO) {
        ItemDto updatedItem = itemService.updateItem(id, itemDTO);
        if (updatedItem != null) {
            return ResponseEntity.ok(updatedItem);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
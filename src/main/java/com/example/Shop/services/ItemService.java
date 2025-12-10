package com.example.Shop.services;

import com.example.Shop.dto.ItemDto;
import com.example.Shop.mappers.ItemMap;
import com.example.Shop.models.Item;
import com.example.Shop.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMap itemMapper;

    public List<ItemDto> getItems() {
        List<Item> items = itemRepository.findAll();
        return itemMapper.toDtoList(items);
    }

    public ItemDto getItem(Long id) {
        Item item = itemRepository.findById(id).orElse(null);
        if (item == null) {
            return null;
        }
        return itemMapper.toDto(item);
    }

    public ItemDto addItem(ItemDto dto) {
        Item item = itemMapper.toEntity(dto);
        Item saved = itemRepository.save(item);
        return itemMapper.toDto(saved);
    }

    public ItemDto updateItem(Long id, ItemDto dto) {
        Item item = itemRepository.findById(id).orElse(null);
        if (item == null) {
            return null;
        }
        item.setName(dto.getItemName());
        item.setPrice(dto.getItemPrice());
        item.setQuantity(dto.getItemQuantity());
        item.setManufacturer(dto.getItemManufacturer());
        Item updated = itemRepository.save(item);
        return itemMapper.toDto(updated);
    }

    public boolean deleteItem(Long id) {
        if (!itemRepository.existsById(id)) {
            return false;
        }
        itemRepository.deleteById(id);
        return true;
    }
}
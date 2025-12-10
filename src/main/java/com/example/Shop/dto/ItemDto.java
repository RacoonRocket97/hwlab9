package com.example.Shop.dto;

import com.example.Shop.models.Country;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    private Long itemId;
    private String itemName;
    private int itemPrice;
    private int itemQuantity;


    private Country itemManufacturer;
}

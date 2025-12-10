package com.example.Shop.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="items")
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    private int quantity;

    @ManyToOne
    @JoinColumn(name="manufacturer_id")
    private Country manufacturer;
}
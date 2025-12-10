package com.example.Shop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;


@Getter
@Setter
@Entity
@Table(name="countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;

    @OneToMany(mappedBy = "manufacturer")
    @JsonIgnore
    private List<Item> items = new ArrayList<>();
}
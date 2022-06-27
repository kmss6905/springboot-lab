package com.example.springbootthymeleaf.domain;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Item {
    private Long id;
    private String itemName;
    private Money price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Money price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}

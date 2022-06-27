package com.example.springbootthymeleaf.domain;

import lombok.Getter;

@Getter
public class ItemDto {
    private String itemName;
    private Money price;
    private Integer quantity;

    public ItemDto(String itemName, Money price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}

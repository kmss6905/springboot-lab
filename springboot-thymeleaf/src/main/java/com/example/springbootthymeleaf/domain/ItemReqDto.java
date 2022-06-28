package com.example.springbootthymeleaf.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter @ToString
public class ItemReqDto {
    private String itemName;
    private Integer price;
    private Integer quantity;

    public ItemReqDto(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}

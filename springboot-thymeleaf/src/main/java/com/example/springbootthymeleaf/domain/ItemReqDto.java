package com.example.springbootthymeleaf.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter @ToString
public class ItemReqDto {
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public ItemReqDto(Long id, String itemName, Integer price, Integer quantity) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}

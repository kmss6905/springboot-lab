package com.example.springbootthymeleaf.domain;

import lombok.Getter;

@Getter
public class ItemDto {
    private String itemName;
    private Money price;
    private Integer quantity;
}

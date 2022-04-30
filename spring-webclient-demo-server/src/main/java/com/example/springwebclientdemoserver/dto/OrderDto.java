package com.example.springwebclientdemoserver.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import java.util.Objects;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
public class OrderDto {
    private int orderId;
    private String productName;
    private String ordererName;
    private String price;

    public OrderDto(int orderId, String productName, String ordererName, String price) {
        this.orderId = orderId;
        this.productName = productName;
        this.ordererName = ordererName;
        this.price = price;
    }
}

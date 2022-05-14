package com.example.springtestcode.order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @GetMapping("/v1/orders/{orderId}")
    BookOrder getOrder(@PathVariable Long orderId){


        return new BookOrder("minshik", orderId);
    }
}

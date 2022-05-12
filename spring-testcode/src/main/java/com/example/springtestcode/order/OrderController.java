package com.example.springtestcode.order;

import org.aspectj.weaver.ast.Or;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @GetMapping("/v1/orders/{orderId}")
    Order getOrder(@PathVariable Integer orderId){


        return new Order("minshik", orderId);
    }
}

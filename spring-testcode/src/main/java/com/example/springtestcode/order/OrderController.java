package com.example.springtestcode.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/v1/orders/{orderId}")
    BookOrder getOrder(@PathVariable Long orderId){
        return orderService.getOrderById(orderId);
    }

    @PostMapping("/v1/order")
    ResponseEntity<?> createOrder(
        @RequestBody BookOrderDto bookOrderDto
    ){
        orderService.createBookOrder(bookOrderDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/v1/order")
    ResponseEntity<?> deleteOrder(
        @RequestBody BookOrderNumber orderNumber
    ){
        orderService.deleteOrder(orderNumber);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/v1/order/{orderId}")
    ResponseEntity<?> updateOrder(
            @PathVariable Long orderId,
            @RequestBody BookOrderDto bookOrderDto
    ){
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}


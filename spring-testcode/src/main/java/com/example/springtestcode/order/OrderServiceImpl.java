package com.example.springtestcode.order;

public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        return orderRepository.findById(orderId);
    }
}

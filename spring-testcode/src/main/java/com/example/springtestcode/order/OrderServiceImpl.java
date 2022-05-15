package com.example.springtestcode.order;

public class OrderServiceImpl implements OrderService{

    private final BookOrderRepository bookOrderRepository;

    public OrderServiceImpl(BookOrderRepository bookOrderRepository) {
        this.bookOrderRepository = bookOrderRepository;
    }

    @Override
    public BookOrder getOrderById(Long orderId) {
        return findOrder(orderId);
    }

    public BookOrder findOrder(Long orderId){
        return bookOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("not found order"));
    }
}

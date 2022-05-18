package com.example.springtestcode.order;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    private final BookOrderRepository bookOrderRepository;

    public OrderServiceImpl(BookOrderRepository bookOrderRepository) {
        this.bookOrderRepository = bookOrderRepository;
    }

    @Override
    public BookOrder getOrderById(Long orderId) {
        return findOrder(orderId);
    }

    @Override
    public BookOrderDto createBookOrder(BookOrderDto bookOrderDto) {
        return bookOrderRepository.save(bookOrderDto.to()).toDto();
    }

    @Override
    public BookOrderDto updateOrder(Long orderId, BookOrderDto bookOrderDto) {
        BookOrder findBookOrder = findOrder(orderId);
        System.out.println(findBookOrder);
        findBookOrder.updateBookOrder(bookOrderDto);
        return findBookOrder.toDto();
    }

    @Override
    public Long deleteOrder(BookOrderNumber bookOrderNumber) {
        BookOrder findBookOrder = findOrder(bookOrderNumber.getOrderId());
        bookOrderRepository.delete(findBookOrder);
        return bookOrderNumber.getOrderId();
    }

    public BookOrder findOrder(Long orderId){
        return bookOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("not found order"));
    }
}

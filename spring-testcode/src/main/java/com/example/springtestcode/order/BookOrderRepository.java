package com.example.springtestcode.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookOrderRepository extends JpaRepository<BookOrder, Long> {
}

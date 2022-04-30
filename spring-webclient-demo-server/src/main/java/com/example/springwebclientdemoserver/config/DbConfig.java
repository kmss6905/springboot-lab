package com.example.springwebclientdemoserver.config;

import com.example.springwebclientdemoserver.dto.OrderDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DbConfig {

    @Bean
    List<OrderDto> preOrders(){
        return List.of(
            new OrderDto(1, "제품1","홍길동","1000"),
            new OrderDto(2, "제품2","고길동","1500"),
            new OrderDto(3, "제품3","마이콜","2000"),
            new OrderDto(4, "제품4","흰둥이","3000")
        );
    }
}

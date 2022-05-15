package com.example.springtestcode.order;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookOrderServiceTest {

    @Mock
    BookOrderRepository bookOrderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    @DisplayName("책 주문 번호 id(Long) 로 책 주문을 찾습니다. - 성공")
    void getBookOrderGivenBookOrderId(){
        BookOrder bookOrder = new BookOrder("hello", 2L);

        when(bookOrderRepository.findById(2L)).thenReturn(Optional.of(bookOrder));

        // given
        Long orderId = 2L;

        // when
        orderService = new OrderServiceImpl(bookOrderRepository);
        BookOrder findBookOrder = orderService.getOrderById(orderId);

        // then
        assertThat(findBookOrder).satisfies(
                it -> {
                    // assertj
//                    assertThat(it.getId()).isEqualTo("hello");
//                    assertThat(it.getUserName()).isEqualTo(2L);

                    // junit5
                    assertEquals(it.getId(), 2L);
                    assertEquals(it.getUserName(), "hello");
                }
        );
    }

    @Test
    @DisplayName("책 주문 번호 id(Long) 로 책 주문 찾기 - 실패, 존재하지 않는 주문")
    void getBookOrderGivenNonExisted(){
    }
}

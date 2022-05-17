package com.example.springtestcode.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookOrderDtoTest {

    @Test
    @DisplayName("책 주문 요청 dto 에서 entity 전환 - 성공")
    void toDto(){
        // given
        BookOrderDto bookOrderDto = new BookOrderDto("홍길동전", "김민식", 10000L);

        // when
        BookOrder bookOrder = bookOrderDto.to();

        // then
        assertThat(bookOrder).satisfies(
                it -> {
                    assertThat(it.getUserName()).isEqualTo("김민식");
                    assertThat(it.getBookName()).isEqualTo("홍길동전");
                }
        );
    }

    @Test
    @DisplayName("책 주문 entity 에서 dto 전환 - ")
    void bookOrderEntityToDto(){
        // given
        BookOrder bookOrder = new BookOrder(1L, "김민식", "홍길동전");

        // when
        BookOrderDto orderDto = bookOrder.toDto();

        // then
        assertThat(orderDto).satisfies(
                it -> {
                    assertThat(it.getBookName()).isEqualTo("홍길동전");
                    assertThat(it.getConsumerName()).isEqualTo("김민식");
                }
        );
    }
}

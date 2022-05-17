package com.example.springtestcode.order;


import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
@DisplayName("책 주문 서비스 테스트")
@MockitoSettings(strictness = Strictness.LENIENT)
class BookOrderServiceTest {

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

    @ParameterizedTest(name = "[{index}/4], {displayName}, {arguments}")
    @DisplayName("책 주문 번호 id(Long) 로 책 주문 찾기 - 실패, 존재하지 않는 주문")
//    @MethodSource("orderedArguments")
    @ValueSource(longs = {0L, 9999L, 2183912839128939218L, 4L})
    void getBookOrderGivenNonExisted(long orderId){

        // then
        assertThatThrownBy(() -> orderService.getOrderById(orderId))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("not found order");
    }

    @Test
    @DisplayName("책 주문 생성하기 - 성공")
    void createBookOrder(){
        // given
        BookOrderDto bookOrderDto = new BookOrderDto("홍길동전", "김민식", 10000L);
        System.out.println(bookOrderDto);
        System.out.println(bookOrderDto.to());

        when(bookOrderRepository.save(any())).thenReturn(bookOrderDto.to());

        // when
        orderService = new OrderServiceImpl(bookOrderRepository);
        BookOrderDto bookOrder = orderService.createBookOrder(bookOrderDto);

        // then
        assertThat(bookOrder).satisfies(
                it -> {
                    assertThat(it.bookName).isEqualTo("홍길동전");
                    assertThat(it.consumerName).isEqualTo("김민식");
                }
        );
    }

    @Test
    @DisplayName("책 주문 취소하기 - 성공")
    void deleteBookOrder(){
        // given
        BookOrderNumber bookOrderNumber = new BookOrderNumber(9999L);
        BookOrder order = new BookOrder(9999L, "minshik", "hello world");
        when(bookOrderRepository.findById(9999L)).thenReturn(Optional.of(order));

        // when
        Long bookOrderId = orderService.deleteOrder(bookOrderNumber);

        // then
        assertThat(bookOrderId).isEqualTo(bookOrderNumber.getOrderId());
    }

    @NotNull
    private Stream<Arguments> orderedArguments() {
        return Stream.of(
                arguments(Named.of("중요한 값 반드시 통과해야함.", new BookOrder("book", 0L))),
                arguments(Named.of("default", new BookOrder("book", 0L))),
                arguments(Named.of("default", new BookOrder("book", 0L))),
                arguments(Named.of("default", new BookOrder("book", 0L)))
        );
    }

//    // 별도로 따로 메서드로부터 가져올 수도 있다.
//    @DisplayName("A parameterized test with named arguments")
//    @ParameterizedTest(name = "{index}: {0}") // ?
//    @MethodSource("namedArguments")
//    void testWithNamedArguments(File file) {
//        System.out.println(file.getName());
//    }
//
//    // argument 타입으로 만든다. -> 장점. 테스트 건마다의 부연 설명을 작성할 수 있다.
//    static Stream<Arguments> namedArguments() {
//        return Stream.of(arguments(Named.of("An important file", new File("path1"))),
//                arguments(Named.of("Another file", new File("path2"))));
//    }
}

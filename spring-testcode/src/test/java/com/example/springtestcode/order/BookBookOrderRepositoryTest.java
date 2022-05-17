package com.example.springtestcode.order;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@Testcontainers
@Slf4j
@ContextConfiguration(initializers = BookBookOrderRepositoryTest.ContainerPropertyInitializer.class)
@DisplayName("책 주문 리포지토리 테스트")
class BookBookOrderRepositoryTest {

    @Autowired
    private BookOrderRepository bookOrderRepository;

    @Autowired
    Environment environment;

    // 바인딩과 동시에 타입캐스팅
    @Value("${container.port}") int port;

    @Container
    private static final MariaDBContainer mariaDBContainer = new MariaDBContainer("mariadb:10.3")
            .withDatabaseName("test");

    @BeforeEach
    void beforeEach(){
        System.out.println("=============================================");
        System.out.println("port = " + port);
        System.out.println("from enviroment = " + environment.getProperty("container.port"));

        // stream log to host console
        System.out.println(mariaDBContainer.getLogs());
    }

    @AfterEach
    void afterEach(){

        // 삭제 후 처리
        bookOrderRepository.deleteAll();
    }

    @Test
    @DisplayName("주문 번호 id(Long) 로 주문 찾기 - 성공")
    void findByOrderId() {
        BookOrder save = bookOrderRepository.save(new BookOrder("hello", 123L));
        BookOrder bookOrder = findOrder(save.getId());
        assertThat(bookOrder.getId()).isEqualTo(save.getId());
    }

    @Test
    @DisplayName("주문 번호 id(Long) 로 주문 찾기 - 실패, 존재하지 않는 주문번호")
    void findNonExistentBookOrderByOrderId(){
        assertThatThrownBy(() -> findOrder(9999L), "존재하지 않는 주문 번호 검색 시 예외")
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("not found order");
    }

    @Test
    @DisplayName("주문 저장하기")
    void saveBookOrder(){
        BookOrder bookOrder = new BookOrder("minshik", "hello");
        BookOrder save = bookOrderRepository.save(bookOrder);

        assertThat(save).satisfies(
                it -> {
                    assertThat(it.getBookName()).isEqualTo("hello");
                    assertThat(it.getUserName()).isEqualTo("minshik");
                }
        );
    }

    @Test
    @DisplayName("책 주문 삭제하기")
    @Transactional
    void deleteOrder(){
        // 저장
        BookOrder bookOrder = new BookOrder("minshik", "hello");
        BookOrder saveBookOrder = bookOrderRepository.save(bookOrder);
        BookOrder findBookOrder = bookOrderRepository.findById(saveBookOrder.getId())
                .orElseThrow(() -> new RuntimeException("not found order"));
        bookOrderRepository.delete(findBookOrder);


        // then
        Optional<BookOrder> findOrderAgain = bookOrderRepository.findById(1L);
        assertThat(findOrderAgain).isEmpty();

    }

    public BookOrder findOrder(Long orderId) {
        return bookOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("not found order"));
    }

    // 프로퍼티 추가 ( 동적으로 추가하기) -> 스프링이 테스트 컨테이어 존재를 알 수 있게끔 한다.
    static class ContainerPropertyInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>{

        @Override
        public void initialize(ConfigurableApplicationContext context) {
            TestPropertyValues.of("container.port=" + mariaDBContainer.getMappedPort(3306))
                    .applyTo(context.getEnvironment());
        }
    }
}

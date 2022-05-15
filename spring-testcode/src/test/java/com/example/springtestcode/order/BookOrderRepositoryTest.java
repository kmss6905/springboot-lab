package com.example.springtestcode.order;

import lombok.extern.slf4j.Slf4j;
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
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@Testcontainers
@Slf4j
@ContextConfiguration(initializers = BookOrderRepositoryTest.ContainerPropertyInitializer.class)
public class BookOrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

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

    @Test
    @DisplayName("주문 번호 id(Long) 로 주문 찾기 - 성공")
    void findByOrderId() {
        BookOrder save = orderRepository.save(new BookOrder("hello", 123L));
        BookOrder bookOrder = findOrder(save.getId());
        assertThat(bookOrder.getId()).isEqualTo(save.getId());
    }

    @Test
    @DisplayName("주문 번호 id(Long) 로 주문 찾기 - 실패, 존재하지 않는 주문번호")
    void finNonExistentBookOrderByOrderId(){

        assertThatThrownBy(() -> findOrder(9999L), "존재하지 않는 주문 번호 검색 시 예외")
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("not found order");
    }

    public BookOrder findOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("not found order"));
    }

    // 프로퍼티 추가 ( 동적으로 추가하기)
    static class ContainerPropertyInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>{

        @Override
        public void initialize(ConfigurableApplicationContext context) {
            TestPropertyValues.of("container.port=" + mariaDBContainer.getMappedPort(3306))
                    .applyTo(context.getEnvironment());
        }
    }
}

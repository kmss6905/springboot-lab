package com.example.springtestcode;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.MethodOrderer.*;
import static org.junit.jupiter.api.TestInstance.*;

// 테스트 생명주기
@TestInstance(Lifecycle.PER_CLASS) // 생명주기는 클래스
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(LifeCycleExtension.class)
public class JunitStudyTest {

    private AtomicLong count = new AtomicLong(0L);

    @BeforeEach
    void setUp(){
        count.getAndAdd(1); // 테스트 실생 전마다 +1
    }

    @Test
    @Order(1)
    void first(){
        assertThat(count).hasValue(1L);
    }

    @Test
    @Order(2)
    void second(){
        assertThat(count).hasValue(2L);
    }

}

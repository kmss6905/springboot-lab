package com.example.springcorebasic.order;

import com.example.springcorebasic.AppConfig;
import com.example.springcorebasic.member.Grade;
import com.example.springcorebasic.member.Member;
import com.example.springcorebasic.member.MemberRepository;
import com.example.springcorebasic.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("주문서비스 테스트")
public class OrderServiceTest {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Test
    @DisplayName("VIP 유저 주문 생성, 고정 할인 가격 적용")
    void createOrderVIP(){
        // given
        Member member = new Member(1L, "민식", Grade.VIP);
        memberRepository.save(member);

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        // 추가 (어떤 구현체를 사용할 지는 config 가 담당함)
//        AppConfig appConfig = new AppConfig();

        // when
//        OrderService orderService = appConfig.orderService();
        Order order = orderService.createOrder(1L, "닌텐도", 10000);

        // then
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
        assertThat(order.getItemName()).isEqualTo("닌텐도");
        assertThat(order.getMemberId()).isEqualTo(1L);
        assertThat(order.getItemPrice()).isEqualTo(10000);
        assertThat(order.calculatePrice()).isEqualTo(9000);
    }

    @Test
    @DisplayName("BASIC 유저 주문 생성, 고정 할인 가격 적용")
    void createOrderBasic(){
        // given
        Member member = new Member(1L, "민식", Grade.BASIC);
        memberRepository.save(member);

        // 추가 (어떤 구현체를 사용할 지는 config 가 담당함)
        AppConfig appConfig = new AppConfig();

        // when
        OrderService orderService = appConfig.orderService();
        Order order = orderService.createOrder(1L, "닌텐도", 4000);

        // then
        assertThat(order.getDiscountPrice()).isEqualTo(0);
        assertThat(order.getItemName()).isEqualTo("닌텐도");
        assertThat(order.getMemberId()).isEqualTo(1L);
        assertThat(order.calculatePrice()).isEqualTo(4000);
        assertThat(order.getItemPrice()).isEqualTo(4000);
    }
}

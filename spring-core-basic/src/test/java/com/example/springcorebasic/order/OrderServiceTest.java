package com.example.springcorebasic.order;

import com.example.springcorebasic.member.Grade;
import com.example.springcorebasic.member.Member;
import com.example.springcorebasic.member.MemberRepository;
import com.example.springcorebasic.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderServiceTest {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Test
    @DisplayName("VIP 유저 주문 생성, 고정 할인 가격 적용")
    void createOrderVIP(){
        // given
        Member member = new Member(1L, "민식", Grade.VIP);
        memberRepository.save(member);

        // when
        OrderService orderService = new OrderServiceImpl();
        Order order = orderService.createOrder(1L, "닌텐도", 4000);

        // then
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
        assertThat(order.getItemName()).isEqualTo("닌텐도");
        assertThat(order.getMemberId()).isEqualTo(1L);
        assertThat(order.getItemPrice()).isEqualTo(4000);
        assertThat(order.calculatePrice()).isEqualTo(3000);
    }

    @Test
    @DisplayName("BASIC 유저 주문 생성, 고정 할인 가격 적용")
    void createOrderBasic(){
        // given
        Member member = new Member(1L, "민식", Grade.BASIC);
        memberRepository.save(member);

        // when
        OrderService orderService = new OrderServiceImpl();
        Order order = orderService.createOrder(1L, "닌텐도", 4000);

        // then
        assertThat(order.getDiscountPrice()).isEqualTo(0);
        assertThat(order.getItemName()).isEqualTo("닌텐도");
        assertThat(order.getMemberId()).isEqualTo(1L);
        assertThat(order.calculatePrice()).isEqualTo(4000);
        assertThat(order.getItemPrice()).isEqualTo(4000);
    }
}

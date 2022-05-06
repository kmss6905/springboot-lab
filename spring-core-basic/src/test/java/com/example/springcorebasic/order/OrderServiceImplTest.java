package com.example.springcorebasic.order;

import com.example.springcorebasic.discount.FixDiscountPolicy;
import com.example.springcorebasic.member.Grade;
import com.example.springcorebasic.member.Member;
import com.example.springcorebasic.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderServiceImplTest {

    // 테스트를 짜는 입장에서는 의존관계가 무엇이 들어가는 지 알 수 없다.
    // 생성자 주입으로 바꾸어 본다면 ?
    @Test
    void createOrder(){
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));
        OrderServiceImpl orderService = new OrderServiceImpl(
                memberRepository,
                new FixDiscountPolicy()
        );
        Order order = orderService.createOrder(1L, "ItemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
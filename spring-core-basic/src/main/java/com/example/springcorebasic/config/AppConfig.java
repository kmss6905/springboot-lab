package com.example.springcorebasic.config;

import com.example.springcorebasic.discount.DiscountPolicy;
import com.example.springcorebasic.discount.RateDiscountPolicy;
import com.example.springcorebasic.member.MemberRepository;
import com.example.springcorebasic.member.MemberService;
import com.example.springcorebasic.member.MemberServiceImpl;
import com.example.springcorebasic.member.MemoryMemberRepository;
import com.example.springcorebasic.order.OrderService;
import com.example.springcorebasic.order.OrderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    private final static Logger log = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    public MemberService memberService(){
        log.debug("memberService call ");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService(){
        log.debug("orderService call ");
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy()
        );
    }

    @Bean
    public MemberRepository memberRepository(){
        log.debug("memberRepository call ");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        log.debug("disCountPolicy call ");
        return new RateDiscountPolicy();
    }
}

package com.example.springcorebasic.order;

import com.example.springcorebasic.discount.DiscountPolicy;
import com.example.springcorebasic.discount.FixDiscountPolicy;
import com.example.springcorebasic.member.Member;
import com.example.springcorebasic.member.MemberRepository;
import com.example.springcorebasic.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member findMember = memberRepository.findById(memberId);
        int price = discountPolicy.discount(findMember, itemPrice);
        return new Order(memberId, itemName, itemPrice, price);
    }
}
package com.example.springcorebasic.discount;

import com.example.springcorebasic.member.Member;

public interface DiscountPolicy {

    /**
     * @return 할인 금액 반환
     */
    int discount(Member member, int itemPrice);
}

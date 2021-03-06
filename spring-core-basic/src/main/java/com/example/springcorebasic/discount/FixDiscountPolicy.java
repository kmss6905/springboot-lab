package com.example.springcorebasic.discount;

import com.example.springcorebasic.member.Grade;
import com.example.springcorebasic.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy{
    private static final int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int itemPrice) {
        if(member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        }else{
            return 0;
        }
    }
}

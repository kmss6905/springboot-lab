package com.example.springcorebasic.discount;

import com.example.springcorebasic.annotation.MainDiscountPolicy;
import com.example.springcorebasic.member.Grade;
import com.example.springcorebasic.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
//@Qualifier("mainDiscountPolicy")
//@Primary
public class RateDiscountPolicy implements DiscountPolicy {
    public static final int discountRatePercent = 10;

    @Override
    public int discount(Member member, int itemPrice) {
        if(member.getGrade() == Grade.VIP){
            return itemPrice * discountRatePercent / 100;
        }else{
            return 0;
        }
    }
}

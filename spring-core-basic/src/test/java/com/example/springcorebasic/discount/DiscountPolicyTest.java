package com.example.springcorebasic.discount;

import com.example.springcorebasic.member.Grade;
import com.example.springcorebasic.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("할인정책 테스트")
public class DiscountPolicyTest {

    @Test
    @DisplayName("고정 할인 정책 적용 - VIP")
    void fixPolicyVip(){
        // given
        Member member = new Member(1L, "민식", Grade.VIP);

        // when
        DiscountPolicy discountPolicy = new FixDiscountPolicy();
        int discountPrice = discountPolicy.discount(member, 1000);

        // then
        assertThat(discountPrice).isEqualTo(1000);
    }

    @Test
    @DisplayName("고정 할인 정책 적용 - BASIC")
    void fixPolicyBasic(){
        // given
        Member member = new Member(1L, "민식", Grade.BASIC);

        // when
        DiscountPolicy discountPolicy = new FixDiscountPolicy();
        int discountPrice = discountPolicy.discount(member, 1000);

        // then
        assertThat(discountPrice).isEqualTo(0);
    }
}

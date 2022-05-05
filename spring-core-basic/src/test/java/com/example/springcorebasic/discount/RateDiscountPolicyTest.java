package com.example.springcorebasic.discount;

import com.example.springcorebasic.member.Grade;
import com.example.springcorebasic.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.PropertyBatchUpdateException;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("할인정책 - 정률계산 테스트")
public class RateDiscountPolicyTest {

    @Test
    @DisplayName("VIP 회원은 10퍼센트 할인을 적용해야한다.")
    void vip_o(){
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);
        DiscountPolicy discountPolicy = new RateDiscountPolicy();

        // when
        int price = discountPolicy.discount(member, 10000);

        // then
        assertThat(price).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP 회원이 아니면 10퍼센트 할인을 적용하지 않는다.")
    void  vip_x(){
        // given
        Member member = new Member(1L, "memberA", Grade.BASIC);
        DiscountPolicy discountPolicy = new RateDiscountPolicy();

        // when
        int price = discountPolicy.discount(member, 10000);

        // then
        assertThat(price).isEqualTo(0);
    }

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


}

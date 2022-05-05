package com.example.springcorebasic.config;

import com.example.springcorebasic.discount.DiscountPolicy;
import com.example.springcorebasic.discount.RateDiscountPolicy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

// 실제로 로딩하는 지 테스트 함
class AppConfigTest {

    @Test
    @DisplayName("실제 읜존관계가 주입되어야 한다.")
    void getDiscountPolicyTest(){
        // given
        AppConfig appConfig = new AppConfig();

        // when
        DiscountPolicy discountPolicy = appConfig.discountPolicy();

        // then
        boolean result = discountPolicy instanceof RateDiscountPolicy;
        assertThat(result).isTrue();
    }
}
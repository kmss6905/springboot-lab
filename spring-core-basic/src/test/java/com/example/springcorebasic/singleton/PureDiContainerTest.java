package com.example.springcorebasic.singleton;

import com.example.springcorebasic.AppConfig;
import com.example.springcorebasic.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PureDiContainerTest {

    @Test
    @DisplayName("스프링을 사용하지 않는 순수 DI 컨테이너")
    void pureContainer(){
        // given
        AppConfig appConfig = new AppConfig();

        // when
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        // then
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴를 사용한 객체 적용")
    void singletonObject() {
        // given
        SingletonService singletonService = SingletonService.getInstance();
        SingletonService singletonService1 = SingletonService.getInstance();


        // then
        assertThat(singletonService).isSameAs(singletonService1);
    }
}

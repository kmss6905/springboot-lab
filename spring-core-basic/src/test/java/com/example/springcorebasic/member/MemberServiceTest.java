package com.example.springcorebasic.member;

import com.example.springcorebasic.config.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MemberServiceTest {

    @Test
    @DisplayName("회원가입")
    void join(){
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);

        // 관리의 위임
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(member.getId());

        // then
        assertThat(findMember).isEqualTo(member);

    }
}

package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberSaveControllerV3Test {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Test
    @DisplayName("유저 저장 컨트롤러는 요청값을 받아 유저를 저장하고 모델뷰를 반환한다.")
    void process() {
        // given
        ControllerV3 controllerV3 = new MemberSaveControllerV3();

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("username", "minshik");
        paramMap.put("age", "10");

        // when
        ModelView mv = controllerV3.process(paramMap);
        Member member = (Member) mv.getModel().get("member");

        // then
        assertThat(member.getUsername()).isEqualTo("minshik");
        assertThat(member.getAge()).isEqualTo(10);
        assertThat(getMembers().contains(member)).isTrue();
    }

    private List<Member> getMembers() {
        return memberRepository.findAll();
    }
}
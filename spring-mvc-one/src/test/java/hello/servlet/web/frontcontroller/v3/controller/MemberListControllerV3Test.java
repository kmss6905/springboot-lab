package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class MemberListControllerV3Test {

    private MemberRepository memberRepository = MemberRepository.getInstance();
    private List<Member> members = new ArrayList<>();

    @BeforeEach
    void beforeEach() {
        Member member1 = new Member("minshik", 10);
        Member member2 = new Member("minshik2", 20);

        memberRepository.save(member1);
        memberRepository.save(member2);

        members = memberRepository.findAll();
    }

    @Test
    @DisplayName("맴버 리스트 컨트롤러는 모든 유저리스트를 포함하는 모델뷰를 반환한다.")
    void process() {
        // given
        Map<String, String> map = new HashMap<>();
        ControllerV3 controllerV3 = new MemberListControllerV3();

        // when
        ModelView mv = controllerV3.process(map);
        List<Member> findMembers = (List<Member>) mv.getModel().get("members");

        // then
        assertThat(mv.getModel().get("members")).isNotNull();
        assertThat(members).isEqualTo(findMembers);
    }
}
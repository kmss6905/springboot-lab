package com.example.springcorebasic.member;

import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    // MemberRepository 타입에 맞는 빈(MemoryMemberRepository.class)을 찾아 자동으로 주입해줌.(의존관계를 설정해줌)
    // ac.getBean(MemberRepository.class), 생성자가 하나일때는 생략 가능 -> 무엇을 의존관계 적용할 지 명확하기 때문에
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

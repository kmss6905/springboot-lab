package hello.servlet.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Test
    void save() {
        // given
        Member member = new Member("minshik", 20);

        // when
        memberRepository.save(member);

        List<Member> members = memberRepository.findAll();

        // then
        assertThat(members).hasSize(1);
    }

    @Test
    void findById() {
        // given
        Member member = new Member("minshik", 20);
        memberRepository.save(member);

        // when
        Member findMember = memberRepository.findById(member.getId());

        // then
        assertThat(findMember.getAge()).isEqualTo(20);
        assertThat(findMember.getUsername()).isEqualTo("minshik");
    }

    @Test
    void findAll() {
        // given
        Member member = new Member("kim", 20);
        Member member1 = new Member("jin", 21);
        memberRepository.save(member);
        memberRepository.save(member1);

        // when
        List<Member> members = memberRepository.findAll();

        // then
        assertThat(members).hasSize(2);
        assertThat(members).contains(member, member1);
    }

    @Test
    void clear() {
        // given
        Member member = new Member("kim", 20);
        Member member1 = new Member("jin", 21);
        memberRepository.save(member);
        memberRepository.save(member1);

        // when
        memberRepository.clear();

        // then
        List<Member> members = memberRepository.findAll();
        assertThat(members).hasSize(0);
    }

    @AfterEach
    void afterEach(){
        MemberRepository memberRepository = MemberRepository.getInstance();
        memberRepository.clear();
    }
}
package com.example.springtestcode;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoNumberGeneratorTest {
    /**
     * 구현이 아닌 비지니스 설계를 테스트 해야한다.
     * 구현테스트? 를 하지말자!
     * 구현물에 대한 테스트 X
     * 설계물에 대한 테스트 O
     *
     * 1. 무엇을 테스트 할 것인가? : 구현이 아닌 설계를 테스트
     * 2. 테스트 가능한 것과 불가능한 것을 구분하라( 항상 성공할 수 있는 것, 항상 결과가 나올 수 있는 것을 테스트 )
     *public
     * Non-Testable
     * -> 외부세계 : HTTP(외부 API), 외부 저장소
     *
     * 6개의 숫자를 반환
     * 중복되지 않은 숫자
     * 랜덤하게 반환 -> 테스트가 불가능한 것 => ?? 의도한 전략대로 반환
     *
     * 어떻게 테스트할 것인가?
     * => 테스트 불가능한 영역을 BoundaryLayer 로 올려서 테스트 가능하도록 변경
     * => Context, Framework 에 의존적이지 않은 테스트 작성
     *
     * TestDouble => 외부요인을 넣어주는 모듈(대표적으로 Mockito)
     * 무엇을 테스트더블로 처리해야할까?
     * 테스트더블의 남용은 구현 테스트로 유도할 수도 있다.
     *
     * 순수 자바 어플리케이션으로는 테스트 할 수 없는 것 => 4. Embedded 시스텝
     * * 저장소 입출력 검증
     * * Spec 검증
     * ** 내부 Controller
     * ** 외부 API
     * embedded 활용가능 ( ppt 참고 )
     *
     * 5. EndPoint test
     * MockMvc
     * REST Assured
     * WebTestClient(WebFlux)
     * 테스트의 목적은 요청과 응답 스팩점증만으로 제한하는 게 정신건강에 좋을 것이라고 생각
     * Service 과 Repository -> @MockBean
     */

    @Test
    @DisplayName("6개의 숫자를 반환")
    void generateTicket() {
        LottoNumberGenerator generator = new LottoNumberGenerator(new LottoNumberCollection());
        List<Integer> ticket = generator.generateTicket();

        assertThat(ticket.size()).isEqualTo(6);
    }
}
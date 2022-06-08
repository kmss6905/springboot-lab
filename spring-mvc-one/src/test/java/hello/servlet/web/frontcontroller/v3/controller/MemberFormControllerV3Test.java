package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberFormControllerV3Test {

    @Test
    @DisplayName("유저 폼 컨트롤러는 뷰 정보만 포함된 모델뷰를 반환한다.")
    void process() {
        ControllerV3 controllerV3 = new MemberFormControllerV3();
        Map<String, String> map = new HashMap<>();

        // when
        ModelView mv = controllerV3.process(map);

        // then
        assertThat(mv.getViewName()).isEqualTo("new-form");
        assertThat(mv.getModel()).isEmpty();
    }
}
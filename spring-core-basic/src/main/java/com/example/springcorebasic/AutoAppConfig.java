package com.example.springcorebasic;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 기본 패키지 밑으로 전부 스캔함(지정하지않으면, @ComponentScan 이 붙은 설정 정보 클래스의 패키지가 시작위치가 된다.)
        basePackages = "com.example.springcorebasic",
        // @Configuration 이 붙은 클래스는 컴포넌트 스캔 대상에서 제외시킴
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
) // @Component 가 붙은 클래스를 찾아 전부 빈으로 등록시킴
public class AutoAppConfig {

}

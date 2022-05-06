package com.example.springcorebasic.beanfind;

import com.example.springcorebasic.AppConfig;
import com.example.springcorebasic.order.OrderService;
import com.example.springcorebasic.order.OrderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationFindBeanTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("이름으로 빈 찾기")
    void findBeanByName(){
        Object bean = ac.getBean("orderService");
        assertThat(bean).isInstanceOf(OrderServiceImpl.class);
    }

    @Test
    @DisplayName("이름과 타입으로 빈 찾기")
    void findBeanByNameAndType(){
        OrderService bean = ac.getBean("orderService", OrderService.class);
        assertThat(bean).isInstanceOf(OrderServiceImpl.class);
    }

    @Test
    @DisplayName("빈을 찾지 못함")
    void findBeanX(){
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxx"));
    }

}

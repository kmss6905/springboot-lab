package com.example.springcorebasic.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonScopeTest {

    @Test
    void test(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(Singleton.class);
        Singleton bean = ac.getBean(Singleton.class);
        Singleton bean2 = ac.getBean(Singleton.class);

        assertThat(bean).isSameAs(bean2);

        ac.close();
    }


    @Scope("singleton")
    static class Singleton{

        public Singleton() {
            System.out.println("Singleton.Singleton");
        }

        @PostConstruct
        void init(){
            System.out.println("Singleton.init");
        }

        @PreDestroy
        void destroy(){
            System.out.println("Singleton.destroy");
        }
    }
}

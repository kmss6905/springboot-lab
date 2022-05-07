package com.example.springcorebasic.provider;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;


public class SingletonWithPrototypeTest {

    @Test
    @DisplayName("싱글톤, 프로토타입 스코프를 동시에 쓸 때 문제가 되는 부분")
    void getSingletonBean(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        ClientBean clientBean2 = ac.getBean(ClientBean.class);

        int count = clientBean1.logic();
        int count1 = clientBean2.logic();
        Assertions.assertThat(count).isEqualTo(1);
        Assertions.assertThat(count1).isEqualTo(1);

        ac.close();
    }

    /**
     *  현재는 자바 표준 기술을 사용하여 싱글톤 빈 & 프로토타입 빈 단점 극복 중
     */
    @Scope("singleton")
    static class ClientBean{

        private final PrototypeBean prototypeBean;
        private final Provider<PrototypeBean> provider;
//        private final ObjectProvider<PrototypeBean> prototypeBeanProvider;
//
//        public ClientBean(PrototypeBean prototypeBean, ObjectProvider<PrototypeBean> prototypeBeanProvider) {
//            this.prototypeBean = prototypeBean;
//            this.prototypeBeanProvider = prototypeBeanProvider;
//        }

        public ClientBean(PrototypeBean prototypeBean, Provider<PrototypeBean> provider) {
            this.prototypeBean = prototypeBean;
            this.provider = provider;
        }

        public PrototypeBean getPrototypeBean() {
            return prototypeBean;
        }

//        public int logic(){
//            prototypeBean.setCount();
//            return prototypeBean.getCount();
//        }

        /*
            1. 의존관계를 외부에서 주입(DI) 받지 않고 직접 필요한 의존관계를 찾음

             싱글톤 빈이 프로토타입을 사용할 때 마다 스프링 컨테이너에 새롭게 요청함.
             프로토 타입 스코프 빈이기 때문에 요청할 때마다 새로운 빈을 요청하게 됨

            public int logic() {
                PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
                prototypeBean.addCount();
                int count = prototypeBean.getCount();
            return count;
        }
         */


        /*
            2. 위의 코드 개선 -> 단순히 DL 기능만 제공하는 것(이미 스프링에서 제공)

            위의 코드는 매우 스프링에 의존적임..
            따라서 자바 표준을 사용하는 방법도 있다.(javax.inject:javax.inject:1 dependency 추가)

            public int logic() {
                PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
                prototypeBean.addCount();
                int count = prototypeBean.getCount();
            return count;
        }
         */

        /*
            3. DL 자바표준을 사용함

            > javax.inject:javax.inject:1 dependency 추가
             스프링에 의존적이지는 않지만 기능이 매우 단순함 해당 빈을 가져오는 get() 메서드 하나밖에 없음

         */
        public int logic() {
            PrototypeBean prototypeBean = provider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }

    }


    @Scope("prototype")
    static class PrototypeBean{

        private int count = 0;

        public PrototypeBean() {
            System.out.println("PrototypeBean.PrototypeBean : " + this);
        }

        public void addCount(){
            this.count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        void init(){
            System.out.println("PrototypeBean.init : " + this);
        }


        @PreDestroy
        void destory(){
            System.out.println("PrototypeBean.destory : " + this);
        }
    }
}

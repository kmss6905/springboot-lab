package com.example.springcorebasic.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService(){}

    public void logic(){
        System.out.println("싱글톤 인스턴스 로직 호출");
    }
}

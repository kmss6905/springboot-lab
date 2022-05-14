package com.example.springtestcode.order;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    public String getUserName() {
        if(this.userName == null && this.userName.isBlank()){
            throw new RuntimeException("order user name can't be null or blank");
        }
        return userName;
    }

    public Long getId() {
        if(this.id == null){
            throw new RuntimeException("order id can't be null or blank");
        }
        return id;
    }

    public BookOrder(String userName, Long id) {
        this.userName = userName;
        this.id = id;
    }
}

package com.example.springtestcode.order;

public class Order {

    private String userName;
    private Integer id;

    public String getUserName() {
        if(this.userName == null && this.userName.isBlank()){
            throw new RuntimeException("order user name can't be null or blank");
        }
        return userName;
    }

    public Integer getId() {
        if(this.id == null){
            throw new RuntimeException("order id can't be null or blank");
        }
        return id;
    }

    public Order(String userName, Integer id) {
        this.userName = userName;
        this.id = id;
    }
}

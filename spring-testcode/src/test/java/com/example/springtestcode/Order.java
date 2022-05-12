package com.example.springtestcode;

public class Order {

    private String getOrderUserName;
    private Integer getOrderId;

    public String getGetOrderUserName() {
        if(this.getOrderUserName == null && this.getOrderUserName.isBlank()){
            throw new RuntimeException("order user name can't be null or blank");
        }
        return getOrderUserName;
    }

    public Integer getGetOrderId() {
        if(this.getOrderId == null){
            throw new RuntimeException("order id can't be null or blank");
        }
        return getOrderId;
    }

    public Order(String getOrderUserName, Integer getOrderId) {
        this.getOrderUserName = getOrderUserName;
        this.getOrderId = getOrderId;
    }
}

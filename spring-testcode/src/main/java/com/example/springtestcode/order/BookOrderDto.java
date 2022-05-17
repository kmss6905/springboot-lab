package com.example.springtestcode.order;


import lombok.*;

// TODO validation 추가
@Getter @Setter
@NoArgsConstructor
@ToString
public class BookOrderDto{
    String bookName;
    String consumerName;
    Long bookPrice;
    Long id;

    public BookOrder to(){
        return new BookOrder(consumerName, bookName);
    }

    public BookOrderDto(String bookName, String consumerName, Long bookPrice) {
        this.bookName = bookName;
        this.consumerName = consumerName;
        this.bookPrice = bookPrice;
    }
}

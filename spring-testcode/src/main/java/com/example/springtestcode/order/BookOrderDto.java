package com.example.springtestcode.order;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// TODO validation 추가
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookOrderDto{
    String bookName;
    String consumerName;
    Long bookPrice;

    public BookOrder to(){
        BookOrder bookOrder = new BookOrder();
        bookOrder.setUserName(this.consumerName);
        bookOrder.setBookName(this.bookName);
        return bookOrder;
    }
}

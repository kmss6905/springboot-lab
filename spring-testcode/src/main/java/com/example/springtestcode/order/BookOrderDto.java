package com.example.springtestcode.order;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    public BookOrderDto(String bookName) {
        this.bookName = bookName;
    }

    public String getBookName() {
        return bookName;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public Long getBookPrice() {
        return bookPrice;
    }

    public Long getId() {
        return id;
    }
}

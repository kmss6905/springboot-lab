package com.example.springtestcode.order;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class BookOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "book_name")
    private String bookName;

    public String getUserName() {
        if(this.userName == null || this.userName.isBlank()){
            throw new RuntimeException("order user name can't be null or blank");
        }
        return userName;
    }

    public Long getId() {
        return id;
    }

    public BookOrder(String userName, Long id) {
        this.id = id;
        this.userName = userName;
    }

    public BookOrder(String bookName) {
        this.bookName = bookName;
    }

    public BookOrder(String userName, String bookName) {
        this.userName = userName;
        this.bookName = bookName;
    }

    public BookOrderDto toDto(){
        return new BookOrderDto(this.bookName, this.userName, this.id);
    }

    public String getBookName() {
        return bookName;
    }

}

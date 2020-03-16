package com.example.bookmanager.model;

public class Book {
    String bookID;
    String bookType;
    String bookName;
    String bookAut;
    String bookNXB;
    double bookPrice;
    int bookCount;

    public Book() {
    }

    public Book(String bookID, String bookType, String bookName, String bookAut, String bookNXB, double bookPrice, int bookCount) {
        this.bookID = bookID;
        this.bookType = bookType;
        this.bookName = bookName;
        this.bookAut = bookAut;
        this.bookNXB = bookNXB;
        this.bookPrice = bookPrice;
        this.bookCount = bookCount;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAut() {
        return bookAut;
    }

    public void setBookAut(String bookAut) {
        this.bookAut = bookAut;
    }

    public String getBookNXB() {
        return bookNXB;
    }

    public void setBookNXB(String bookNXB) {
        this.bookNXB = bookNXB;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }
}

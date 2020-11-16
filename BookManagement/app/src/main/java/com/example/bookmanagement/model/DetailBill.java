package com.example.bookmanagement.model;

public class DetailBill {
    String detailBillID;
    private Bill bill;
    private Book book;
    int account;

    public DetailBill() {
    }

    public DetailBill(String detailBillID, Bill bill, Book book, int account) {
        this.detailBillID = detailBillID;
        this.bill = bill;
        this.book = book;
        this.account = account;
    }

    public String getDetailBillID() {
        return detailBillID;
    }

    public void setDetailBillID(String detailBillID) {
        this.detailBillID = detailBillID;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "DetailBill{" +
                "detailBillID='" + detailBillID + '\'' +
                ", bill=" + bill +
                ", book=" + book +
                ", account=" + account +
                '}';
    }

}

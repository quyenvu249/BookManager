package com.example.bookmanager.model;

public class DetailBill {
    String detailBillID;
    String billID;
    String bookID;
    int account;

    public DetailBill() {
    }

    public DetailBill(String detailBillID, String billID, String bookID, int account) {
        this.detailBillID = detailBillID;
        this.billID = billID;
        this.bookID = bookID;
        this.account = account;
    }

    public String getDetailBillID() {
        return detailBillID;
    }

    public void setDetailBillID(String detailBillID) {
        this.detailBillID = detailBillID;
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }
}

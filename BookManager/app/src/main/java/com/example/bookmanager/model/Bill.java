package com.example.bookmanager.model;

public class Bill {
    String billID;
    String date;

    public Bill(String billID, String date) {
        this.billID = billID;
        this.date = date;
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return billID + '\''+ date ;
    }
}

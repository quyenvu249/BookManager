package com.example.bookmanager.model;

public class Category {
    String cateID;
    String cateName;
    String catePosition;
    String cateDes;

    public Category() {
    }

    public Category(String cateID, String cateName, String catePosition, String cateDes) {
        this.cateID = cateID;
        this.cateName = cateName;
        this.catePosition = catePosition;
        this.cateDes = cateDes;
    }

    public String getCateID() {
        return cateID;
    }

    public void setCateID(String cateID) {
        this.cateID = cateID;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getCatePosition() {
        return catePosition;
    }

    public void setCatePosition(String catePosition) {
        this.catePosition = catePosition;
    }

    public String getCateDes() {
        return cateDes;
    }

    public void setCateDes(String cateDes) {
        this.cateDes = cateDes;
    }

    @Override
    public String toString() {
        return cateName;
    }
}

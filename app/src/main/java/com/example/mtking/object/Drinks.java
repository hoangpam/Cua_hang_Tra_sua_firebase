package com.example.mtking.object;

import android.content.Intent;

public class Drinks {
    private String IdDrink,nameDrink,idSizeDrink,mNameCategories,MaDrink,hinh,idCate;
    private int price;

    public Drinks() {
    }

    public String getIdDrink() {
        return IdDrink;
    }

    public void setIdDrink(String idDrink) {
        IdDrink = idDrink;
    }

    public String getNameDrink() {
        return nameDrink;
    }

    public void setNameDrink(String nameDrink) {
        this.nameDrink = nameDrink;
    }

    public String getIdSizeDrink() {
        return idSizeDrink;
    }

    public void setIdSizeDrink(String idSizeDrink) {
        this.idSizeDrink = idSizeDrink;
    }

    public String getmNameCategories() {
        return mNameCategories;
    }

    public void setmNameCategories(String mNameCategories) {
        this.mNameCategories = mNameCategories;
    }

    public String getMaDrink() {
        return MaDrink;
    }

    public void setMaDrink(String maDrink) {
        MaDrink = maDrink;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getIdCate() {
        return idCate;
    }

    public void setIdCate(String idCate) {
        this.idCate = idCate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Drinks(String idDrink, String nameDrink, String idSizeDrink, String mNameCategories, String maDrink, String hinh, String idCate, int price) {
        IdDrink = idDrink;
        this.nameDrink = nameDrink;
        this.idSizeDrink = idSizeDrink;
        this.mNameCategories = mNameCategories;
        MaDrink = maDrink;
        this.hinh = hinh;
        this.idCate = idCate;
        this.price = price;
    }
}






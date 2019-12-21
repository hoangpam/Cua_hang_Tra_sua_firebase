package com.example.mtking.object;

public class ChiTietGioHang {
    String IdDrink;
    String idGioHang;
    int sl;
    String idChiTietGH;

    public String getIdChiTietGH() {
        return idChiTietGH;
    }

    public void setIdChiTietGH(String idChiTietGH) {
        this.idChiTietGH = idChiTietGH;
    }

    public ChiTietGioHang(String idDrink, String idGioHang, int sl, int giaTien, String size) {
        IdDrink = idDrink;
        this.idGioHang = idGioHang;
        this.sl = sl;
        GiaTien = giaTien;
        this.size = size;
    }

    public int getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(int giaTien) {
        GiaTien = giaTien;
    }

    int GiaTien;
    public ChiTietGioHang(String idDrink, String idGioHang, int sl, String size) {
        IdDrink = idDrink;
        this.idGioHang = idGioHang;
        this.sl = sl;
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    String size;


    public String getIdDrink() {
        return IdDrink;
    }

    public void setIdDrink(String idDrink) {
        IdDrink = idDrink;
    }

    public String getIdGioHang() {
        return idGioHang;
    }

    public void setIdGioHang(String idGioHang) {
        this.idGioHang = idGioHang;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }


    public ChiTietGioHang() {
    }




}

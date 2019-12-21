package com.example.mtking.object;

public class GioHang {

    public String getIdGioHang() {
        return idGioHang;
    }

    public void setIdGioHang(String idGioHang) {
        this.idGioHang = idGioHang;
    }

    public String getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(String idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int tongTien) {
        TongTien = tongTien;
    }

    public GioHang() {
    }



    String idGioHang;
    String idKhachHang;
    String tinhtrang;

    public GioHang(String idKhachHang, String tinhtrang, int tongTien) {
        this.idKhachHang = idKhachHang;
        this.tinhtrang = tinhtrang;
        TongTien = tongTien;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    int TongTien;

    int GiaTien;

    public GioHang(String idGioHang, String idKhachHang, String tinhtrang, int tongTien, int giaTien) {
        this.idGioHang = idGioHang;
        this.idKhachHang = idKhachHang;
        this.tinhtrang = tinhtrang;
        TongTien = tongTien;
        GiaTien = giaTien;
    }

    public int getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(int giaTien) {
        GiaTien = giaTien;
    }
}

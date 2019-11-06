package com.example.mtking.Model;

public class KhachHang {
    public String tenKh;
    public String email;
    public String mk;
    public String SDT;
    public String Quan;
    public String Phuong;
    public String TenDuong;

    public KhachHang() {
    }

    public KhachHang(String tenKh, String email, String mk, String SDT, String quan, String phuong, String tenDuong) {
        this.tenKh = tenKh;
        this.email = email;
        this.mk = mk;
        this.SDT = SDT;
        Quan = quan;
        Phuong = phuong;
        TenDuong = tenDuong;
    }
}

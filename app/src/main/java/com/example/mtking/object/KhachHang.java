package com.example.mtking.object;

public class KhachHang {

//    public static Object currentKhachHang;
    String tenKh;
    String email;
    String mk;
    String SDT;
    String Quan;
    String Phuong;
    String TenDuong;

    public KhachHang(String tenKh, String email, String mk, String SDT, String quan, String phuong, String tenDuong) {
        this.tenKh = tenKh;
        this.email = email;
        this.mk = mk;
        this.SDT = SDT;
        Quan = quan;
        Phuong = phuong;
        TenDuong = tenDuong;
    }

    public void setTenKh(String tenKh) {
        this.tenKh = tenKh;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public void setQuan(String quan) {
        Quan = quan;
    }

    public void setPhuong(String phuong) {
        Phuong = phuong;
    }

    public void setTenDuong(String tenDuong) {
        TenDuong = tenDuong;
    }

    public String getTenKh() {
        return tenKh;
    }

    public String getEmail() {
        return email;
    }

    public String getMk() {
        return mk;
    }

    public String getSDT() {
        return SDT;
    }

    public String getQuan() {
        return Quan;
    }

    public String getPhuong() {
        return Phuong;
    }

    public String getTenDuong() {
        return TenDuong;
    }
}


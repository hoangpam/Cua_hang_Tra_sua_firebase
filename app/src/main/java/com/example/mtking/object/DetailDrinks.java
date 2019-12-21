package com.example.mtking.object;

public class DetailDrinks {
    String idDrinks;
    String TenCT;
    int Giatien;

    public DetailDrinks() {
    }

    public String getIdDrinks() {
        return idDrinks;
    }

    public void setIdDrinks(String idDrinks) {
        this.idDrinks = idDrinks;
    }

    public String getTenCT() {
        return TenCT;
    }

    public void setTenCT(String tenCT) {
        TenCT = tenCT;
    }

    public int getGiatien() {
        return Giatien;
    }

    public void setGiatien(int giatien) {
        Giatien = giatien;
    }

    public DetailDrinks(String idDrinks, String tenCT, int giatien) {
        this.idDrinks = idDrinks;
        TenCT = tenCT;
        Giatien = giatien;
    }
}

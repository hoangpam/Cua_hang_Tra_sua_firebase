package com.example.mtking.object;

public class Discount_code {
    private String id,imaged;

    public Discount_code() {
    }

    public Discount_code(String id, String imaged) {
        this.id = id;
        this.imaged = imaged;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImaged() {
        return imaged;
    }

    public void setImaged(String imaged) {
        this.imaged = imaged;
    }
}

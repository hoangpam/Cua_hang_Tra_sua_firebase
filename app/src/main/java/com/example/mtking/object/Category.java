package com.example.mtking.object;

public class Category {
    String id;
    private String  mName;

    public Category(String mName, String mImage) {
        this.mName = mName;
        this.mImage = mImage;
    }

    public Category() {
    }

    private String mImage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }
}

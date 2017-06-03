package com.example.haeunkim.mealtime.model;


public class Item {

    int imgae;
    String nickName;
    String major;

    public Item(int image, String nickName, String major) {
        this.imgae = image;
        this.nickName = nickName;
        this.major = major;
    }

    public int getImgae() {
        return imgae;
    }

    public void setImgae(int imgae) {
        this.imgae = imgae;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}

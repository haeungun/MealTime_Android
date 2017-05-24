package com.example.haeunkim.mealtime.model;


public class Waiting {

    private String category;
    private String nickname;
    private String major;

    public Waiting(String category, String nickname, String major) {
        this.category = category;
        this.nickname = nickname;
        this.major = major;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}

package com.example.haeunkim.mealtime.model;

// User model stored in user node of firebase
public class User {

    private String nickname;
    private String major;

    public User(String name, String major) {
        this.nickname = name;
        this.major = major;
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

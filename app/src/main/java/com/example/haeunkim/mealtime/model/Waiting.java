package com.example.haeunkim.mealtime.model;

// Waiting model stored in the waiting node of firebase
public class Waiting {

    private String uid;
    private String category;
    private String nickname;
    private String major;

    public Waiting() {}

    public Waiting(String uid, String category, String nickname, String major) {
        this.uid = uid;
        this.category = category;
        this.nickname = nickname;
        this.major = major;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

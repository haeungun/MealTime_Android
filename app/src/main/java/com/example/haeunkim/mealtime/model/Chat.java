package com.example.haeunkim.mealtime.model;


public class Chat {

    private String uid;
    private String name;
    private String message;

    public Chat() {}

    public Chat(String uid, String name, String msg) {
        this.uid = uid;
        this.name = name;
        this.message = msg;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

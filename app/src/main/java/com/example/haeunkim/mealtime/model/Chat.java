package com.example.haeunkim.mealtime.model;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Chat {

    private String uid;
    private String name;
    private String message;
    private String date;

    public Chat() {}

    public Chat(String uid, String name, String msg) {
        this.uid = uid;
        this.name = name;
        this.message = msg;

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm");
        date = sdf.format(new Date());
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

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

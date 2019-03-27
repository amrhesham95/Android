package com.example.firebaseapp;

public class Message {
    private String body;
    private String title;
    private String uid;

    public String getEmail() {
        return uid;
    }

    public void setEmail(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return this.getTitle();
    }

    public Message() {
    }

    public Message(String body, String title,String uid) {
        this.body = body;
        this.title = title;
        this.uid=uid;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}

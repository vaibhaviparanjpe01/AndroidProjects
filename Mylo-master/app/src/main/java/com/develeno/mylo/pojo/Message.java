package com.develeno.mylo.pojo;

import com.google.firebase.firestore.Exclude;
import com.stfalcon.chatkit.commons.models.IMessage;

import java.util.Calendar;
import java.util.Date;

public class Message implements IMessage {
    private String id;
    private String text;
    private ChatUser author;
    private Date createdAt;

    public Message() {
    }

    public Message(String id, String text, ChatUser user) {
        this.createdAt = Calendar.getInstance().getTime();
        this.id = id;
        this.text = text;
        this.author = user;
    }

    public ChatUser getAuthor() {
        return author;
    }

    public void setAuthor(ChatUser author) {
        this.author = author;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    @Exclude
    public ChatUser getUser() {
        return author;
    }

    public void setUser(ChatUser author) {
        this.author = author;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
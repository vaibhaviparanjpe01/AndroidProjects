package com.develeno.mylo.pojo;

import com.stfalcon.chatkit.commons.models.IUser;

/**
 * Created by devel_000 on 10-Oct-17.
 */
public class ChatUser implements IUser {

    private String id;
    private String name;
    private String avatar;
    private String token;

    public ChatUser() {
    }

    public ChatUser(UserObject user) {
        this.id = user.getNumber();
        this.name = user.getName();
//        this.avatar = user.getImglink();
//        this.token = user.getToken();
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

package com.develeno.mylo.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by devel_000 on 08-Jan-18.
 */
public class UserObject {
    private String name;
    private String email;
    private String number;
    private String password;
    private String fcm;
    private Date createdOn;
    private int credits;
    private boolean userStatus;
    private List<String> fav;
    private String referralCode;

    public UserObject() {
        fav = new ArrayList<>();
        fcm = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public boolean isUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    public List<String> getFav() {
        return fav;
    }

    public void setFav(List<String> fav) {
        this.fav = fav;
    }

    public void addToFav(String first) {
        fav.add(first);
    }

    public void removeFromFav(String first) {
        fav.remove(first);
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public String getFcm() {
        return fcm;
    }

    public void setFcm(String fcm) {
        this.fcm = fcm;
    }
}

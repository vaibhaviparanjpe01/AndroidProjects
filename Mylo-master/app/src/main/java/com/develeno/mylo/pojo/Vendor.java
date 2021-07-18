package com.develeno.mylo.pojo;

import com.google.firebase.firestore.GeoPoint;

import java.util.List;

public class Vendor {

    private String name;
    private String desc;
    private String address;
    private String category;
    private String video_link;
    private GeoPoint coordinates;
    private List<String> images;
    private List<String> menu;
    private List<String> services;
    private String sublabel;
    private String sublabel2;
    private int ratingCount;
    private float ratingTotal;
    private String text;
    private String timming;
    private boolean dealsEnabled;
    private float distanceInMetres;
    private int points;
    private int vegStatus;
    private int costForTwo;
    private String cuisines;
    private int credits;

    public Vendor() {
        ratingCount = 0;
        ratingTotal = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVideo_link() {
        return video_link;
    }

    public void setVideo_link(String video_link) {
        this.video_link = video_link;
    }

    public GeoPoint getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(GeoPoint coordinates) {
        this.coordinates = coordinates;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getSublabel() {
        return sublabel;
    }

    public void setSublabel(String sublabel) {
        this.sublabel = sublabel;
    }

    public float getRatingTotal() {
        return ratingTotal;
    }

    public void setRatingTotal(float ratingTotal) {
        this.ratingTotal = ratingTotal;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTimming() {
        return timming;
    }

    public void setTimming(String timming) {
        this.timming = timming;
    }

    public boolean getDealsEnabled() {
        return dealsEnabled;
    }

    public void setDealsEnabled(boolean dealsEnabled) {
        this.dealsEnabled = dealsEnabled;
    }

    public float getDistanceInMetres() {
        return distanceInMetres;
    }

    public void setDistanceInMetres(float distanceInMetres) {
        this.distanceInMetres = distanceInMetres;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getVegStatus() {
        return vegStatus;
    }

    public void setVegStatus(int vegStatus) {
        this.vegStatus = vegStatus;
    }

    public int getCostForTwo() {
        return costForTwo;
    }

    public void setCostForTwo(int costForTwo) {
        this.costForTwo = costForTwo;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public List<String> getMenu() {
        return menu;
    }

    public void setMenu(List<String> menu) {
        this.menu = menu;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }

    public String getSublabel2() {
        return sublabel2;
    }

    public void setSublabel2(String sublabel2) {
        this.sublabel2 = sublabel2;
    }
}

package com.develeno.mylo.pojo;

import java.util.Calendar;
import java.util.Date;

public class Reservation {

    public static final int PENDING = 0;
    public static final int CONFIRMED = 1;
    private String dealId;
    private String tnc;
    private String userName;
    private String vendorId;
    private String vendorName;
    private String userId;
    private Date createdAt;
    private Date reservationDate;
    private int guestCount;
    private String timeSlot;
    private int status;
    private int cost;

    public Reservation() {
    }


    public Reservation(String dealId, Deal deal, String tnc, Vendor vendor, String userId, Date reservationDate, int guestCount, String timeSlot) {
        this.dealId = dealId;
        this.vendorId = deal.getVendorId();
        this.vendorName = vendor.getName();
        this.userId = userId;
        this.reservationDate = reservationDate;
        this.guestCount = guestCount;
        this.timeSlot = timeSlot;
        this.createdAt = Calendar.getInstance().getTime();
        this.status = PENDING;
        this.tnc = tnc;
        this.cost = deal.getPrice();
    }

    public String getDeal() {
        return dealId;
    }

    public void setDeal(String dealId) {
        this.dealId = dealId;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public int getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(int guestCount) {
        this.guestCount = guestCount;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getTnc() {
        return tnc;
    }

    public void setTnc(String tnc) {
        this.tnc = tnc;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}

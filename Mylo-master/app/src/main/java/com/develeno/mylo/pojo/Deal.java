package com.develeno.mylo.pojo;

import java.util.Date;
import java.util.List;

public class Deal {

    private String deal;
    private int price;
    private String tnc;
    private String vendorId;
    private Date createdOn;
    private List<String> timeslotIds;

    public Deal() {
    }

    public String getDeal() {
        return deal;
    }

    public void setDeal(String deal) {
        this.deal = deal;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTnc() {
        return tnc;
    }

    public void setTnc(String tnc) {
        this.tnc = tnc;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public List<String> getTimeslotIds() {
        return timeslotIds;
    }

    public void setTimeslotIds(List<String> timeslotIds) {
        this.timeslotIds = timeslotIds;
    }
}

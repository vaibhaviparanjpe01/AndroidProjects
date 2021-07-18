package com.develeno.mylo.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeSlot {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
    private String name;
    private Date startTime;
    private Date endTime;
    private boolean mon, tue, wed, thurs, fri, sat, sun;

    public TimeSlot() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public boolean isMon() {
        return mon;
    }

    public void setMon(boolean mon) {
        this.mon = mon;
    }

    public boolean isTue() {
        return tue;
    }

    public void setTue(boolean tue) {
        this.tue = tue;
    }

    public boolean isWed() {
        return wed;
    }

    public void setWed(boolean wed) {
        this.wed = wed;
    }

    public boolean isThurs() {
        return thurs;
    }

    public void setThurs(boolean thurs) {
        this.thurs = thurs;
    }

    public boolean isFri() {
        return fri;
    }

    public void setFri(boolean fri) {
        this.fri = fri;
    }

    public boolean isSat() {
        return sat;
    }

    public void setSat(boolean sat) {
        this.sat = sat;
    }

    public boolean isSun() {
        return sun;
    }

    public void setSun(boolean sun) {
        this.sun = sun;
    }

    public String getStartTimeText() {
        return simpleDateFormat.format(startTime);
    }

    public String getEndTimeText() {
        return simpleDateFormat.format(endTime);
    }

    public boolean isAvailableOnDay(int day) {
        switch (day) {
            case 1:
                return isSun();
            case 2:
                return isMon();
            case 3:
                return isTue();
            case 4:
                return isWed();
            case 5:
                return isThurs();
            case 6:
                return isFri();
            case 7:
                return isSat();
        }
        return true;
    }
}

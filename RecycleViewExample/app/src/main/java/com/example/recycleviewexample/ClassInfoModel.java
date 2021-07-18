package com.example.recycleviewexample;


public class ClassInfoModel {

    String batchImage,batchName,batchDays,batchTime1,batchTime2,batchTime3;

    public ClassInfoModel() {
    }

    public ClassInfoModel(String batchImage, String batchName, String batchDays, String batchTime1, String batchTime2, String batchTime3) {
        this.batchImage = batchImage;
        this.batchName = batchName;
        this.batchDays = batchDays;
        this.batchTime1 = batchTime1;
        this.batchTime2 = batchTime2;
        this.batchTime3 = batchTime3;
    }

    public String getBatchName() {        return batchName; }

    public void setBatchName(String batchName) {        this.batchName = batchName; }

    public String getBatchDays() { return batchDays; }

    public void setBatchDays(String batchDays) { this.batchDays = batchDays; }

    public String getBatchTime1() { return batchTime1; }

    public void setBatchTime1(String batchTime1) { this.batchTime1 = batchTime1; }

    public String getBatchTime2() { return batchTime2; }

    public void setBatchTime2(String batchTime2) { this.batchTime2 = batchTime2; }

    public String getBatchTime3() { return batchTime3; }

    public void setBatchTime3(String batchTime3) { this.batchTime3 = batchTime3; }

    public String getBatchImage() {        return batchImage; }

    public void setBatchImage(String batchImage) { this.batchImage = batchImage; }
}

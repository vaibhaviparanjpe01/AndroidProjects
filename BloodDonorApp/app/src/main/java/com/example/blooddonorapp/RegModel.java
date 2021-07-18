package com.example.blooddonorapp;

class RegModel {
    String Name;
    String Mobile;
    String bg;
    String city;

    public RegModel()
    {

    }
public RegModel(String n,String m,String b, String c)
{
this.Name=n;
this.Mobile=m;
this.bg=b;
this.city=c;

}
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

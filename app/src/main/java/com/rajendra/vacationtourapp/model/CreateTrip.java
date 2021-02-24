package com.rajendra.vacationtourapp.model;

import com.google.gson.annotations.SerializedName;

public class CreateTrip {
    @SerializedName("id")
    private long id;

    @SerializedName("location")
    private String location;

    @SerializedName("day")
    private String day;


    @SerializedName("cost")
    private String cost;

    @SerializedName("recivingaddress")
    private String recivingaddress;

    @SerializedName("date")
    private String date;



    @SerializedName("image")
    private String image;

    @SerializedName("details")
    private String details;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getRecivingaddress() {
        return recivingaddress;
    }

    public void setRecivingaddress(String recivingaddress) {
        this.recivingaddress = recivingaddress;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "CreateTrip{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", day='" + day + '\'' +
                ", cost='" + cost + '\'' +
                ", recivingaddress='" + recivingaddress + '\'' +
                ", date='" + date + '\'' +
                ", image='" + image + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}

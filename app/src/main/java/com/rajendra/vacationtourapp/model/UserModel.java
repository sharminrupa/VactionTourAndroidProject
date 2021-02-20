package com.rajendra.vacationtourapp.model;

import com.google.gson.annotations.SerializedName;

public class UserModel {

    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("email")
    private String email;

    @SerializedName("pass")
    private String pass;

    @SerializedName("conpass")
    private String conpass;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getConpass() {
        return conpass;
    }

    public void setConpass(String conpass) {
        this.conpass = conpass;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", conpass='" + conpass + '\'' +
                '}';
    }
}

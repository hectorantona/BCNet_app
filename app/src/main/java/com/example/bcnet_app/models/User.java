package com.example.bcnet_app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class User {

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("result")
    @Expose
    private String message;

    @SerializedName("picture")
    @Expose
    private String profileimg;

    @SerializedName("ErrorMessage")
    @Expose
    private String errormsg;

    @SerializedName("value")
    @Expose
    private ArrayList<String> infoUser;

    public String getMessage() {
        return message;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getProfileimg() {
        return profileimg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.example.sameapp.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class apiUser {
    @SerializedName("userName")
    private String UserName;
    @SerializedName("password")
    private String Password;

    public apiUser(String userName, String password) {
        UserName = userName;
        Password = password;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

}

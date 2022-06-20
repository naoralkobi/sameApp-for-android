package com.example.sameapp.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class User {
    @PrimaryKey(autoGenerate=false)
    @NonNull
    private String userNameId;

    @NonNull
    private String password;

    private String pictureId;

    public User(){}

    public User(@NonNull String userName, @NonNull String password, String pictureId) {
        this.userNameId = userName;
        this.password = password;
        this.pictureId = pictureId;
    }

    @NonNull
    public String getUserNameId() {
        return userNameId;
    }

    public void setUserNameId(@NonNull String userName) {
        this.userNameId = userName;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }
}
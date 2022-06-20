package com.example.sameapp.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Contact {
    @PrimaryKey(autoGenerate=false)
    @NonNull
    // his User Name.
    private String contactID;
    private String userNameOwner;
    private String lastMassage;
    private String lastMassageSendingTime;

//    public Contact(){}

    public Contact(@NonNull String contactID, String lastMassage, String lastMassageSendingTime, String userNameOwner) {
        this.contactID = contactID;
        this.lastMassage = lastMassage;
        this.lastMassageSendingTime = lastMassageSendingTime;
        this.userNameOwner = userNameOwner;
    }

    public String getLastMassage() {
        return lastMassage;
    }

    public String getLastMassageSendingTime() {
        return lastMassageSendingTime;
    }

    @NonNull
    public String getContactID() {
        return contactID;
    }

    public void setContactID(@NonNull String contactID) {
        this.contactID = contactID;
    }

    public void setLastMassage(String lastMassage) {
        this.lastMassage = lastMassage;
    }

    public void setLastMassageSendingTime(String lastMassageSendingTime) {
        this.lastMassageSendingTime = lastMassageSendingTime;
    }

    public String getUserNameOwner() {
        return userNameOwner;
    }

    public void setUserNameOwner(String userNameOwner) {
        this.userNameOwner = userNameOwner;
    }
}
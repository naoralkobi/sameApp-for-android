package com.example.sameapp.api;

import com.google.gson.annotations.SerializedName;

public class apiContact {
    @SerializedName("id")
    private String Id;
    @SerializedName("userNameOwner")
    private String UserNameOwner;
    @SerializedName("name")
    private String Name;
    @SerializedName("server")
    private String Server;
    @SerializedName("last")
    private String Last;
    @SerializedName("lastDate")
    private String LastDate;

    public apiContact(String id, String userNameOwner, String name, String server, String last, String lastDate) {
        Id = id;
        UserNameOwner = userNameOwner;
        Name = name;
        Server = server;
        Last = last;
        LastDate = lastDate;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUserNameOwner() {
        return UserNameOwner;
    }

    public void setUserNameOwner(String userNameOwner) {
        UserNameOwner = userNameOwner;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getServer() {
        return Server;
    }

    public void setServer(String server) {
        Server = server;
    }

    public String getLast() {
        return Last;
    }

    public void setLast(String last) {
        Last = last;
    }

    public String getLastDate() {
        return LastDate;
    }

    public void setLastDate(String lastDate) {
        LastDate = lastDate;
    }
}

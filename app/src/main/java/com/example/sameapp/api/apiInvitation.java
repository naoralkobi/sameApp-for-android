package com.example.sameapp.api;

import com.google.gson.annotations.SerializedName;

public class apiInvitation {
    @SerializedName("from")
    private String From;
    @SerializedName("to")
    private String To;
    @SerializedName("server")
    private String Server;

    public apiInvitation(String from, String to, String server) {
        From = from;
        To = to;
        Server = server;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public String getServer() {
        return Server;
    }

    public void setServer(String server) {
        Server = server;
    }
}

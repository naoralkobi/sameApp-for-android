package com.example.sameapp.api;

import com.google.gson.annotations.SerializedName;

public class apiTransfer {

    @SerializedName("from")
    private String From;
    @SerializedName("to")
    private String To;
    @SerializedName("content")
    private String Content;

    public apiTransfer(String from, String to, String content) {
        From = from;
        To = to;
        Content = content;
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

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}

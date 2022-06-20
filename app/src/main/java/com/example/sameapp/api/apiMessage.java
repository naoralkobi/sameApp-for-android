package com.example.sameapp.api;

import com.google.gson.annotations.SerializedName;

public class apiMessage {
    @SerializedName("id")
    private int Id;
    @SerializedName("content")
    private String Content;
    @SerializedName("created")
    private String Created;
    @SerializedName("sent")
    private Boolean Sent;
    @SerializedName("userId")
    private String UserId;
    @SerializedName("contactId")
    private String ContactId;

    public apiMessage(int id, String content, String created, Boolean sent, String userId, String contactId) {
        Id = id;
        Content = content;
        Created = created;
        Sent = sent;
        UserId = userId;
        ContactId = contactId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getCreated() {
        return Created;
    }

    public void setCreated(String created) {
        Created = created;
    }

    public Boolean getSent() {
        return Sent;
    }

    public void setSent(Boolean sent) {
        Sent = sent;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getContactId() {
        return ContactId;
    }

    public void setContactId(String contactId) {
        ContactId = contactId;
    }
}

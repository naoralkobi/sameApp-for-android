package com.example.sameapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Message {

    @PrimaryKey(autoGenerate = true)
    private int messageId;

    private String created;
    private boolean sent;
    private String content;

    // this is a reference to the primary key of the parent entity.
    private String userCreatorId;
    private String contactId;
    // CONTACT


    public Message(){

    }
    public Message(String created, boolean sent, String userId, String content, String contactId) {
        this.created = created;
        this.sent = sent;
        this.userCreatorId = userId;
        this.content = content;
        this.contactId = contactId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int id) {
        this.messageId = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public String getUserCreatorId() {
        return userCreatorId;
    }

    public void setUserCreatorId(String userId) {
        userCreatorId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }
}

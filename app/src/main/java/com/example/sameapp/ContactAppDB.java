package com.example.sameapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.sameapp.dao.ContactDao;
import com.example.sameapp.dao.MessageDao;
import com.example.sameapp.dao.UserDao;
import com.example.sameapp.models.Contact;
import com.example.sameapp.models.Message;
import com.example.sameapp.models.User;

@Database(entities = {Contact.class, Message.class, User.class},version = 5, exportSchema = false)
public abstract class ContactAppDB extends RoomDatabase {
    public abstract ContactDao contactDao();
    public abstract MessageDao messageDao();
    public abstract UserDao userDao();

}

package com.example.sameapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sameapp.models.Contact;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM contact WHERE userNameOwner =:owner")
    List<Contact> index(String owner);

    @Query("SELECT * FROM contact WHERE contactID = :contactId")
    Contact get(String contactId);

    @Query("UPDATE contact SET lastMassage = :LastMessage, lastMassageSendingTime = :LastTime WHERE contactID = :contactId")
    void update(String contactId, String LastMessage, String LastTime);

    @Query("SELECT * FROM contact WHERE contactID = :userName AND userNameOwner =:owner")
    Contact isDataExist(String owner, String userName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Contact... contacts);

    @Update
    void update(Contact... contacts);

    @Delete
    void delete(Contact... contacts);

    @Query("DELETE FROM contact WHERE userNameOwner = :owner")
    void clear(String owner);

}

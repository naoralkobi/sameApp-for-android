package com.example.sameapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.example.sameapp.models.Message;

import java.util.List;

@Dao
public interface MessageDao {

    @Query("SELECT * FROM message")
    List<Message> index();

    @Query("SELECT * FROM message WHERE contactID = :contactId AND userCreatorId = :owner")
    List<Message> get(String contactId, String owner);

    @Query("SELECT * FROM message WHERE messageId = :id")
    Message isDataExist(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Message... messages);

    @Update
    void update(Message... messages);

    @Delete
    void delete(Message... messages);

    @Query("DELETE FROM message WHERE userCreatorId = :sender AND contactId = :receiver")
    void clear(String sender, String receiver);
}

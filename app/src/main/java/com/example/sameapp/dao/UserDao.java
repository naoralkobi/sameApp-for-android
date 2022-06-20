package com.example.sameapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sameapp.models.User;
import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> index();

    @Query("SELECT * FROM user WHERE userNameId = :userName")
    User get(String userName);

    @Query("SELECT * FROM user WHERE userNameId = :userName")
    User isDataExist(String userName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User... users);

    @Update
    void update(User... users);

    @Delete
    void delete(User... users);

}
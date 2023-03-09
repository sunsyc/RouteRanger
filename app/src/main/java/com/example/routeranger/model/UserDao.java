package com.example.routeranger.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE userId IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Query("SELECT * FROM user WHERE location LIKE :location")
    User findByLocation(String location);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

    @Transaction
    @Query("SELECT * FROM user")
    public List<UserWithRoutes> getUsersWithRoutes();
}

package com.example.routeranger.model.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.routeranger.model.User;
import com.example.routeranger.model.UserWithRoutes;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE userId IN (:userId) LIMIT 1")
    Single<User> findById(int userId);

    @Query("SELECT * FROM user WHERE username LIKE :first AND " +
            "password LIKE :last LIMIT 1")
    Single<User> findByCredentials(String first, String last);

    @Query("SELECT * FROM user WHERE location LIKE :location")
    User findByLocation(String location);

    @Update
    Completable updateUser(User user);

    @Insert()
    Completable insertAll(User... users);

    @Delete
    void delete(User user);

    @Transaction
    @Query("SELECT * FROM user")
    public List<UserWithRoutes> getUsersWithRoutes();
}

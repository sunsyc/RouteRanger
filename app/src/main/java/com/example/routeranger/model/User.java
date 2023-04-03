package com.example.routeranger.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    public User(String username, String password) {
        mUsername = username;
        mPassword = password;
    }
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    public int uid;

    @ColumnInfo(name = "username")
    public String mUsername;

    @ColumnInfo(name = "password")
    public String mPassword;

    // may change to a geolocation or separated into distinct parts of an address
    @ColumnInfo(name = "location")
    public String location;
}

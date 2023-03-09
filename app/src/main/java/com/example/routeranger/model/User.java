package com.example.routeranger.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    @ColumnInfo(name = "userId")
    public int uid;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    // may change to a geolocation or separated into distinct parts of an address
    @ColumnInfo(name = "location")
    public String location;
}

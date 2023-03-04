package com.example.routeranger.model;

import androidx.room.Entity;

@Entity(primaryKeys = {"userId", "routeId"})
public class UserRouteCrossRef {
    public int userId;
    public int songId;
}

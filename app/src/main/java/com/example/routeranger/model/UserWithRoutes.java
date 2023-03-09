package com.example.routeranger.model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Junction;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.List;
public class UserWithRoutes {
    @Embedded public User user;
    @Relation(
            parentColumn = "userId",
            entityColumn = "routeId",
            associateBy = @Junction(UserRouteCrossRef.class)
    )
    public List<Route> routes;
}

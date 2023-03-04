package com.example.routeranger.model;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class UsersWithRoutes {
    @Embedded public User user;
    @Relation(
            parentColumn = "userId",
            entityColumn = "routeId",
            associateBy = @Junction(UserRouteCrossRef.class)
    )
    public List<Route> routes;
}

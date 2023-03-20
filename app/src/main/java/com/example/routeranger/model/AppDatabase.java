package com.example.routeranger.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.routeranger.model.Dao.RouteDao;
import com.example.routeranger.model.Dao.UserDao;

@Database(entities = {User.class, UserRouteCrossRef.class, Route.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract RouteDao routeDao();
}

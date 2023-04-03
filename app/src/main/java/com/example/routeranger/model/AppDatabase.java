package com.example.routeranger.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.routeranger.model.Dao.RouteDao;
import com.example.routeranger.model.Dao.UserDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, UserRouteCrossRef.class, Route.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract RouteDao routeDao();

    private static final int sNumberOfThreads = 2;
    public final ExecutorService dbWriteExecutor = Executors.newFixedThreadPool(sNumberOfThreads);
}

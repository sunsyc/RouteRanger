package com.example.routeranger.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.routeranger.model.Dao.RouteDao;
import com.example.routeranger.model.Dao.UserDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, UserRouteCrossRef.class, Route.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract RouteDao routeDao();

    private static volatile AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    protected AppDatabase() {};

        private static AppDatabase create(final Context context) {
            return Room.databaseBuilder(context, AppDatabase.class, "db")
                    .fallbackToDestructiveMigration()
                    .enableMultiInstanceInvalidation()
                    .build();
        }

    public int loggedInUserId;
    private static final int sNumberOfThreads = 2;
    public final ExecutorService dbWriteExecutor = Executors.newFixedThreadPool(sNumberOfThreads);
}

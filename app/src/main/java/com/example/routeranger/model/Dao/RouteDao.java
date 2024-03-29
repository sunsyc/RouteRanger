package com.example.routeranger.model.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.routeranger.model.Route;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

@Dao
public interface RouteDao {
    @Query("SELECT * FROM route")
    List<Route> getAll();

    @Query("SELECT * FROM route WHERE routeId IN (:routeIds)")
    List<Route> loadAllByIds(int[] routeIds);

    @Query("SELECT * FROM route WHERE name LIKE :name")
    Route findByName(String name);

    @Insert
    ListenableFuture<Long> insert(Route route);

    @Delete
    void delete(Route route);
}

package com.example.routeranger.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Route {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "routeId")
    public int uid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "start")
    public String start;

    @ColumnInfo(name = "end")
    public String end;

    @ColumnInfo(name = "length")
    public int length;

    @ColumnInfo(name = "duration")
    public String duration;

    @ColumnInfo(name = "userId")
    public int userId;
}

package com.example.project_popular_libraries.model.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.project_popular_libraries.model.photoupload.entity.Hit;

@Database(entities = {Hit.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract HitDao hitDao();
}
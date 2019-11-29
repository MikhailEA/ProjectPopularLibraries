package com.example.projectpopularlibraries.app;

import android.app.Application;

import androidx.room.Room;

import com.example.projectpopularlibraries.model.database.AppDataBase;

public class App extends Application {

    private static AppDataBase appDataBase;

    @Override
    public void onCreate() {
        super.onCreate();

        appDataBase = Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class, "photo_database").build();
    }

    public static AppDataBase getAppDataBase() {
        return appDataBase;
    }


}

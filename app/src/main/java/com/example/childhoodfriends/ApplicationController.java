package com.example.childhoodfriends;

import android.app.Application;

import androidx.room.Room;

import com.example.childhoodfriends.data.PersonDatabase;

public class ApplicationController extends Application {
    private static ApplicationController instance;
    private static PersonDatabase personDatabase;

    private final String personDatabaseName = "PersonDB";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        setupDatabase();
    }

    private void setupDatabase() {
        personDatabase = Room.databaseBuilder(
                getApplicationContext(),
                PersonDatabase.class,
                personDatabaseName)
                .addMigrations(PersonDatabase.MIGRATION_2_3).build();
    }

    public static PersonDatabase getPersonDatabase() {
        return personDatabase;
    }

    public static ApplicationController getInstance() { return instance; }
}

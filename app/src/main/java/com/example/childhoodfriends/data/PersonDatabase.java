package com.example.childhoodfriends.data;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.childhoodfriends.models.dbEntities.PersonItem;

@Database(entities = {PersonItem.class}, version = 3)
public abstract class PersonDatabase extends RoomDatabase {

    public abstract PersonDAO personDAO();

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }

    public static final Migration MIGRATION_2_3 = new Migration(2, 3 ) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE PersonItem ADD COLUMN description TEXT");
        }
    };
}

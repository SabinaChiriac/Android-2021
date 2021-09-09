package com.example.childhoodfriends.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.childhoodfriends.models.dbEntities.PersonItem;

import java.util.List;

@Dao
public interface PersonDAO {
    @Query("SELECT * FROM personItem")
    List<PersonItem> getAll();

    @Insert
    void insertPerson(PersonItem personItem);

}

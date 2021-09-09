package com.example.childhoodfriends.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.childhoodfriends.models.dbEntities.PersonItem;

import java.util.List;

@Dao
public interface PersonDAO {
    @Query("SELECT * FROM personItem")
    List<PersonItem> getAll();

    @Insert
    void insertPerson(PersonItem personItem);

    @Delete
    void Delete(PersonItem personItem);

    @Query("DELETE FROM personitem WHERE name=:personName")
    void deleteByName(String personName);

    @Update
    void Update(PersonItem personItem);


}

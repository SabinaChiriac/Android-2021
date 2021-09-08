package com.example.childhoodfriends.models.dbEntities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PersonItem {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "city")
    public String city;
    @ColumnInfo(name = "neighborhood")
    public String neighborhood;
    @ColumnInfo(name = "email")
    public String email;
    @ColumnInfo(name = "school")
    public String school;
    @ColumnInfo(name = "description")
    public String description;
    @ColumnInfo(name = "freq_places")
    public String freq_places;
    @ColumnInfo(name = "birthday")
    public String birthday;

    public PersonItem(String name, String city, String neighborhood, String email, String school, String description, String freq_places, String birthday) {
        this.name = name;
        this.city = city;
        this.neighborhood = neighborhood;
        this.email = email;
        this.school = school;
        this.description = description;
        this.freq_places = freq_places;
        this.birthday = birthday;
    }

    public PersonItemElement convert() { return new PersonItemElement(name, city, neighborhood, email,
                              school, description, freq_places, birthday); }
}

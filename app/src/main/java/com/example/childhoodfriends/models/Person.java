package com.example.childhoodfriends.models;

public class Person {
    private String name;
    private String city;
    private String neighborhood;
    private String email;
    private String school;
    private String description;
    private String frequented_places;
    private String birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFrequented_places() {
        return frequented_places;
    }

    public void setFrequented_places(String frequented_places) {
        this.frequented_places = frequented_places;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Person(String name, String city, String neighborhood, String email, String school,
                  String description, String frequented_places, String birthday) {
        this.name = name;
        this.city = city;
        this.neighborhood = neighborhood;
        this.email = email;
        this.school = school;
        this.description = description;
        this.frequented_places = frequented_places;
        this.birthday = birthday;
    }
}

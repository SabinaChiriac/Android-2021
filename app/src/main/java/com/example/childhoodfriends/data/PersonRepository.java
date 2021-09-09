package com.example.childhoodfriends.data;

import android.os.AsyncTask;

import com.example.childhoodfriends.ApplicationController;
import com.example.childhoodfriends.data.tasks.GetAllPersonsTask;
import com.example.childhoodfriends.data.tasks.InsertPersonTask;
import com.example.childhoodfriends.models.dbEntities.PersonItem;

import java.util.List;

public class PersonRepository  {
    public static interface OnSuccesListener {
        void onSucces();
    }

    public static interface OnGetPersonListener {
        void onSucces(List<PersonItem> persons);
    }

    private PersonDatabase personDatabase;

    public PersonRepository() {
        personDatabase = ApplicationController.getPersonDatabase();
    }

    public void insertPerson(PersonItem personItem, OnSuccesListener listener) {
        new InsertPersonTask(personDatabase, listener).execute(personItem);
    }

    public void getAllPersons(OnGetPersonListener listener) {
        new GetAllPersonsTask(personDatabase, listener).execute();
    }
}

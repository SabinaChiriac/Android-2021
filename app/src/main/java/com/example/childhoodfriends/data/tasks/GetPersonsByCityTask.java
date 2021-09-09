package com.example.childhoodfriends.data.tasks;

import android.os.AsyncTask;

import com.example.childhoodfriends.data.PersonDatabase;
import com.example.childhoodfriends.data.PersonRepository;
import com.example.childhoodfriends.models.dbEntities.PersonItem;

import java.util.List;

public class GetPersonsByCityTask extends AsyncTask<String, Void, List<PersonItem>> {
    private PersonDatabase personDatabase;
    private PersonRepository.OnGetPersonListener listener;

    public GetPersonsByCityTask(PersonDatabase personDatabase, PersonRepository.OnGetPersonListener listener) {
        this.personDatabase = personDatabase;
        this.listener = listener;
    }

    @Override
    protected List<PersonItem> doInBackground(String... cities) {
      return  personDatabase.personDAO().getPersonsByCity(cities[0], cities[1]);
    }

    @Override
    protected void onPostExecute(List<PersonItem> personItems) {
        super.onPostExecute(personItems);
    }
}

package com.example.childhoodfriends.data.tasks;

import android.os.AsyncTask;

import com.example.childhoodfriends.data.PersonDatabase;
import com.example.childhoodfriends.data.PersonRepository;
import com.example.childhoodfriends.models.dbEntities.PersonItem;

import java.util.List;

public class GetAllPersonsTask extends AsyncTask<Void,Void, List<PersonItem>> {
    private PersonDatabase personDatabase;
    private PersonRepository.OnGetPersonListener listener;

    public GetAllPersonsTask(PersonDatabase personDatabase, PersonRepository.OnGetPersonListener listener) {
        this.personDatabase = personDatabase;
        this.listener = listener;
    }

    @Override
    protected List<PersonItem> doInBackground(Void... voids) {
        return personDatabase.personDAO().getAll();
    }

    @Override
    protected void onPostExecute(List<PersonItem> personItems) {
        super.onPostExecute(personItems);
        listener.onSucces(personItems);
    }
}

package com.example.childhoodfriends.data.tasks;

import android.os.AsyncTask;

import com.example.childhoodfriends.data.PersonDatabase;
import com.example.childhoodfriends.data.PersonRepository;
import com.example.childhoodfriends.models.dbEntities.PersonItem;

public class DeleteByNamePersonTask extends AsyncTask<String,Void,Void> {
    private PersonDatabase personDatabase;
    private PersonRepository.OnSuccesListener listener;

    public DeleteByNamePersonTask(PersonDatabase personDatabase,PersonRepository.OnSuccesListener listener){
        this.personDatabase=personDatabase;
        this.listener=listener;
    }
    @Override
    protected Void doInBackground(String... personItems){
        personDatabase.personDAO().deleteByName(personItems[0]);
        return  null;
    }
    @Override
    protected void onPostExecute(Void aVoid){
        super.onPostExecute(aVoid);
        listener.onSucces();
    }
}

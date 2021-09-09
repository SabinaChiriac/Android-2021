package com.example.childhoodfriends.data.tasks;

import android.os.AsyncTask;

import com.example.childhoodfriends.data.PersonDatabase;
import com.example.childhoodfriends.data.PersonRepository;
import com.example.childhoodfriends.models.dbEntities.PersonItem;

public class UpdatePersonTask extends AsyncTask<PersonItem,Void,Void>{
    private PersonDatabase personDatabase;
    private PersonRepository.OnSuccesListener listener;

    public UpdatePersonTask(PersonDatabase personDatabase,PersonRepository.OnSuccesListener listener){
        this.personDatabase=personDatabase;
        this.listener=listener;
    }
    @Override
    protected Void doInBackground(PersonItem... personItems){
        personDatabase.personDAO().Update(personItems[0]);
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid){
        super.onPostExecute(aVoid);
        listener.onSucces();
    }
}

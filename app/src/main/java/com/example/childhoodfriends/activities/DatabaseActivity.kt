package com.example.childhoodfriends.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.childhoodfriends.R
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.childhoodfriends.data.PersonRepository
import com.example.childhoodfriends.helpers.errorLog
import com.example.childhoodfriends.models.dbEntities.PersonItem

class DatabaseActivity : AppCompatActivity() {

    private val personRepository=PersonRepository()
    private var editText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_data_base)
        setupViews()
    }
    fun setupViews(){

    }
    fun getToDos() {
       /// progressBar?.visibility = View.VISIBLE
        personRepository.getAllPersons { PersonList ->
            Log.e("Eroare", "Aceasta e o eroare!")
            "Aceasta e o eroare!".errorLog()
            if (PersonList.size > 0) {
                val personItem = PersonList.get(0)
                personItem.name = "to do done"
                personItem.city = "to do done"
                updateItem(personItem)
            }
            if (PersonList.size > 1) {
                val personItem = PersonList.get(1)
                deleteItem(personItem)
            }
        }
    }
    fun updateItem(personItem: PersonItem){
        personRepository.updatePerson(personItem,object:PersonRepository.OnSuccesListener{
            override fun onSucces() {
               "Succes".errorLog()
            }
        })
    }
    fun deleteItem(personItem: PersonItem){
        personRepository.deletePerson((personItem)){
            "Delete".errorLog()
        }
    }
    fun deleteByNamePerson(){
        val name= editText?.text?.toString() ?: return
        when(name.isEmpty()){
            true->return
        }
        personRepository.deleteByNamePerson(name){
            Toast.makeText(
                this,"Deleted",
                Toast.LENGTH_SHORT
            ).show()
        }
    }



}
package com.example.childhoodfriends.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.childhoodfriends.R
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.childhoodfriends.adapters.PersonAdapter
import com.example.childhoodfriends.data.PersonRepository
import com.example.childhoodfriends.helpers.errorLog
import com.example.childhoodfriends.models.dbEntities.PersonItem
import com.example.childhoodfriends.models.dbEntities.PersonItemElement

class DatabaseActivity : AppCompatActivity() {

    private val personRepository=PersonRepository()
    private var editTextCity: EditText? = null
    private var editTextNeighborhood: EditText? = null
    private var personAdapter: PersonAdapter? = null
    private var buttonMyAccount: Button? = null
    private var buttonSearch: Button? = null

    private val personList by lazy {
        ArrayList<PersonItemElement>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_data_base)
        setupViews()


    }
    fun setupViews(){
        setupRecyclerView()

        buttonMyAccount = findViewById(R.id.btn_account)
        buttonMyAccount?.setOnClickListener {
            setContentView(R.layout.fragment_my_account)
        }

        buttonSearch = findViewById(R.id.btn_search)
        buttonSearch?.setOnClickListener {
            getAllPersonsByCity()
        }
    }

    private fun setupRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.rv_persons)
        val layoutManager: LinearLayoutManager =
                LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        personAdapter = PersonAdapter(personList)
        recyclerView.adapter = personAdapter
    }

    fun getPersons() {
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

    fun getAllPersonsByCity() {
        val city = editTextCity?.text?.toString() ?: return
        val neighborhood = editTextNeighborhood?.text?.toString() ?: return

        when(city.isEmpty()) { true -> return}

        when(neighborhood.isEmpty()) { true ->return}

        personRepository.getPersonsbyCity(city, neighborhood) {
            Toast.makeText(
                    this, "Searched.",
                    Toast.LENGTH_SHORT
            ).show()
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

}
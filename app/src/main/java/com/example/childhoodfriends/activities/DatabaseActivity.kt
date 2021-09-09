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
import com.example.childhoodfriends.Constants.Companion.ARG_FB_PERSONS
import com.example.childhoodfriends.adapters.PersonAdapter
import com.example.childhoodfriends.data.PersonRepository
import com.example.childhoodfriends.helpers.errorLog
import com.example.childhoodfriends.models.dbEntities.PersonItem
import com.example.childhoodfriends.models.dbEntities.PersonItemElement
import com.example.childhoodfriends.models.dbEntities.PersonItemElementFB
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DatabaseActivity : AppCompatActivity() {

    private val personRepository=PersonRepository()
    private var editTextCity: EditText? = null
    private var editTextNeighborhood: EditText? = null
    private var personAdapter: PersonAdapter? = null
    private var buttonMyAccount: Button? = null
    private var buttonSearch: Button? = null
    private var buttonAdd: Button? = null

    private val personList by lazy {
        ArrayList<PersonItemElement>()
    }

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Firebase.database.reference
        setContentView(R.layout.activity_local_data_base)

        setupViews()
    }

    override fun onStart() {
        super.onStart()
        database.addValueEventListener(personListener)
    }

    override fun onStop() {
        super.onStop()

        database.removeEventListener(personListener)
    }

    val personListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // Get Post object and use the values to update the UI
            personList.clear()

            val personsSnapshot = dataSnapshot.child(ARG_FB_PERSONS)
            personsSnapshot?.children?.forEach { itemSnapshot ->
                val personItemFB = itemSnapshot.getValue(PersonItemElementFB::class.java)
                personItemFB?.convert()?.let { personItem ->
                    personList.add(personItem.convert())
                }
            }

            personAdapter?.notifyDataSetChanged()
            // ...
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Getting Post failed, log a message
            // Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
        }
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

        buttonAdd = findViewById(R.id.add)
        buttonAdd?.setOnClickListener {
           // insertPerson()
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
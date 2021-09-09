package com.example.childhoodfriends.fragments;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.childhoodfriends.R;
import com.example.childhoodfriends.models.dbEntities.PersonItem;
import com.example.childhoodfriends.models.dbEntities.PersonItemElement;
import com.example.childhoodfriends.models.dbEntities.PersonItemElementFB;
import com.google.firebase.database.DatabaseReference;


public class MyAccountFragment extends Fragment {

    private ProgressBar progressBar;
    private EditText editTextName;
    private EditText editTextCity;
    private EditText editTextNeighborhood;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextSchool;
    private EditText editTextDescription;
    private EditText editTextFreqPlaces;
    private EditText editTextBirthday;
    private Button btnAdd;

    private DatabaseReference database;

    public static MyAccountFragment newInstance() {
        Bundle args = new Bundle();

        MyAccountFragment fragment = new MyAccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    @Override
    public void onStart() {
        super.onStart();
       
    }

    public void insertPerson() {
        String name = editTextName.getText().toString();
        if(name.isEmpty())
            editTextName.setError("Required field!");
            else
                editTextName.setError(null);

        String city = editTextCity.getText().toString();
        if(city.isEmpty())
            editTextCity.setError("Required field!");
        else
            editTextCity.setError(null);

        String neighborhood = editTextNeighborhood.getText().toString();
        if(neighborhood.isEmpty())
            editTextNeighborhood.setError("Required field!");
        else
            editTextNeighborhood.setError(null);

        String email = editTextEmail.getText().toString();
        if(email.isEmpty())
            editTextEmail.setError("Required field!");
        else
            editTextEmail.setError(null);

        String school = editTextSchool.getText().toString();
        if(school.isEmpty())
            editTextSchool.setError("Required field!");
        else
            editTextSchool.setError(null);

        String description = editTextDescription.getText().toString();
        if(description.isEmpty())
            editTextDescription.setError("Required field!");
        else
            editTextDescription.setError(null);

        String freq_places = editTextFreqPlaces.getText().toString();
        if(freq_places.isEmpty())
            editTextFreqPlaces.setError("Required field!");
        else
            editTextFreqPlaces.setError(null);

        String birthday = editTextBirthday.getText().toString();
        if(birthday.isEmpty())
            editTextBirthday.setError("Required field!");
        else
            editTextBirthday.setError(null);


        PersonItemElementFB personItemFB = new PersonItemElementFB(name, city, neighborhood, email,
                school, description, freq_places, birthday);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        editTextName = view.findViewById(R.id.editable_name);
        editTextCity = view.findViewById(R.id.editable_city);
        editTextNeighborhood = view.findViewById(R.id.editable_neighborhood);
        editTextEmail = view.findViewById(R.id.editable_email);
        editTextPassword = view.findViewById(R.id.editable_password);
        editTextSchool = view.findViewById(R.id.editable_school);
        editTextDescription = view.findViewById(R.id.editable_description);
        editTextFreqPlaces = view.findViewById(R.id.editable_freq_places);
        editTextBirthday = view.findViewById(R.id.editable_birthday);
        btnAdd = view.findViewById(R.id.btn_add);
        progressBar = view.findViewById(R.id.pb_loading);

    }
}

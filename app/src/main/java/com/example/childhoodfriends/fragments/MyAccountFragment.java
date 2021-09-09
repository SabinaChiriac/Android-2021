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
    private Button btn_Register;

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
        btn_Register = view.findViewById(R.id.btn_register);
        progressBar = view.findViewById(R.id.pb_loading);

    }
}

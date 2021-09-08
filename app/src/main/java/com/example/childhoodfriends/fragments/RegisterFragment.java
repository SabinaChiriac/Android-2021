package com.example.childhoodfriends.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.childhoodfriends.R;
import com.example.childhoodfriends.activities.MainActivity;
import com.example.childhoodfriends.Constants;
import com.example.childhoodfriends.VolleyConfigSingleton;
import com.example.childhoodfriends.helpers.UtilsValidators;
import com.google.firebase.BuildConfig;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class RegisterFragment extends Fragment {

    public static final String TAG_FRAGMENT_REGISTER = "TAG_FRAGMENT_REGISTER";

    private FirebaseAuth mAuth;

    private EditText emailEditText;
    private EditText passwordEditText;


    public static RegisterFragment newInstance() {
        Bundle args = new Bundle();

        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn_register).setOnClickListener(v -> {
            validateEmailAndPassword();
        });


        if(BuildConfig.DEBUG) {
            emailEditText = view.findViewById(R.id.edt_email);
            passwordEditText = view.findViewById(R.id.edt_password);

            emailEditText.setText("eve.holt@reqres.in");
            passwordEditText.setText("cityslicka");
        }
    }

    private void validateEmailAndPassword() {
        if (getView() == null) {
            return;
        }

        emailEditText = getView().findViewById(R.id.edt_email);
        passwordEditText = getView().findViewById(R.id.edt_password);

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (!UtilsValidators.isValidEmail(email)) {
            emailEditText.setError("Invalid Email");
            return;
        } else {
            emailEditText.setError(null);
        }

        if (!UtilsValidators.isValidPassword(password)) {
            passwordEditText.setError("Invalid Password");
            return;
        } else {
            passwordEditText.setError(null);
        }

        createFirebaseUser(email, password);
    }

    private void createFirebaseUser(String email, String password) {
        if (getActivity() == null) {
            return;
        }


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), task -> {
                    if (task.isSuccessful()) {

                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(getContext(), "Authentication success.",
                                Toast.LENGTH_SHORT).show();
                        goToDatabaseActivity();
                    } else {

                        Toast.makeText(getContext(), "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void createNewUser() {
        if (getActivity() == null) {
            return;
        }

        String url = "https://reqres.in/api/users";
        JSONObject postData = new JSONObject();
        try {
            postData.put("name", "Florin");
            postData.put("job", "student");
        } catch (JSONException ex) {
            Toast.makeText(
                    getContext(),
                    "ERROR: get users failed with error: ${ex.message}",
                    Toast.LENGTH_SHORT
            ).show();
        }

        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                postData,
                response -> {

                    Log.e("register", "succes");
                    handleResponse(response);
                },
                error -> {
                    Toast.makeText(
                            getContext(),
                            "ERROR: get users failed with error: ${error.message}",
                            Toast.LENGTH_SHORT).show();
                }
        );
        VolleyConfigSingleton.getInstance(Objects.requireNonNull(getActivity()).getApplicationContext()).addToRequestQueue(jsonRequest);
    }

    private void handleResponse(JSONObject response) {
        try {
            String token = response.getString("token");
            saveAccessTokenToSharedPrefs(token);

            goToDatabaseActivity();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void saveAccessTokenToSharedPrefs(String accessToken) {
        SharedPreferences sharedPrefs = Objects.requireNonNull(getContext()).getSharedPreferences(Constants.ARG_SHARED_PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(Constants.ARG_ACCESS_TOKEN, accessToken);
        editor.apply();
    }

    private void goToDatabaseActivity() {
        startActivity(new Intent(getActivity(), MainActivity.class));
        Objects.requireNonNull(getActivity()).finish();
    }

}

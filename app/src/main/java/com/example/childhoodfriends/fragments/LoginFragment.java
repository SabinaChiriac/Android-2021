package com.example.childhoodfriends.fragments;

import android.content.Context;
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
import com.example.childhoodfriends.models.Constants;
import com.example.childhoodfriends.models.VolleyConfigSingleton;
import com.google.firebase.BuildConfig;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class LoginFragment extends Fragment {

    public static final String TAG_FRAGMENT_LOGIN = "TAG_FRAGMENT_LOGIN";

    private FirebaseAuth mAuth;
    private EditText emailEditText;
    private EditText passwordEditText;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
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
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn_login).setOnClickListener(v -> {
            validateEmailAndPassword();
        });


        emailEditText = view.findViewById(R.id.edt_email);
        passwordEditText = view.findViewById(R.id.edt_password);

        if(BuildConfig.DEBUG) {
            emailEditText.setText("eve.holt@reqres.in");
            passwordEditText.setText("cityslicka");
        }
    }

    private void validateEmailAndPassword() {
        if(getView() == null) {
            return;
        }

        emailEditText = getView().findViewById(R.id.edt_email);
        passwordEditText = getView().findViewById(R.id.edt_password);

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if(!com.example.liste.helpers.UtilsValidators.isValidEmail(email)) {
            emailEditText.setError("Invalid Email");
            return;
        } else {
            emailEditText.setError(null);
        }

        if(!com.example.liste.helpers.UtilsValidators.isValidPassword(password)) {
            passwordEditText.setError("Invalid Password");
            return;
        } else {
            passwordEditText.setError(null);
        }

        loginUser(email, password);
    }

    private void loginUser(String email, String password) {
        String url = "https://reqres.in/api/login";
        JSONObject postData = new JSONObject();
        try {
            postData.put("email", email);
            postData.put("password", password);
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
                    Log.e("login", "succes");

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

    private void goToDatabaseActivity() {
        //TO DO
    }

    private void saveAccessTokenToSharedPrefs(String token) {
        SharedPreferences sharedPrefs = Objects.requireNonNull(getContext()).getSharedPreferences(Constants.ARG_SHARED_PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(Constants.ARG_ACCESS_TOKEN, token);
        editor.apply();
    }
}

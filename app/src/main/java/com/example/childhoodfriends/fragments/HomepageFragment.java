package com.example.childhoodfriends.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.childhoodfriends.R;
import com.example.childhoodfriends.interfaces.OnFragmentActivityCommunication;

public class HomepageFragment extends Fragment{

    public static final String TAG_FRAGMENT_HOMEPAGE = "TAG_FRAGMENT_HOMEPAGE";

    private OnFragmentActivityCommunication activityCommunication;

    public static Fragment newInstance() {
        Bundle args = new Bundle();

        HomepageFragment fragment = new HomepageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof OnFragmentActivityCommunication) {
            activityCommunication = (OnFragmentActivityCommunication) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn_register).setOnClickListener(v -> {
            goToRegister();
        });

        view.findViewById(R.id.btn_login).setOnClickListener(v -> {
            goToLogin();
        });
    }

    private void goToRegister() {
        if(activityCommunication != null) {
            activityCommunication.onReplaceFragment(RegisterFragment.TAG_FRAGMENT_REGISTER);
        }
    }

    private void goToLogin() {
        if(activityCommunication != null) {
            activityCommunication.onReplaceFragment(LoginFragment.TAG_FRAGMENT_LOGIN);
        }
    }
    
}
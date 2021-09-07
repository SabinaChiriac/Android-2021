package com.example.childhoodfriends.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.childhoodfriends.R;
import com.example.childhoodfriends.activities.LoginRegisterActivity;
import com.example.childhoodfriends.interfaces.OnFragmentActivityCommunication;

import java.util.Objects;

public class WelcomeFragment extends Fragment{

    public static final String TAG_FRAGMENT_WELCOME = "TAG_FRAGMENT_WELCOME";

    private OnFragmentActivityCommunication activityCommunication;

    public static WelcomeFragment newInstance() {
        Bundle args = new Bundle();

        WelcomeFragment fragment = new WelcomeFragment();
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

        view.findViewById(R.id.btn_get_started).setOnClickListener(v -> {
            goToLoginRegisterActivity();
        });
    }

    private void goToLoginRegisterActivity() {
      startActivity(new Intent(getActivity(), LoginRegisterActivity.class));
        Objects.requireNonNull(getActivity()).finish();

    }
}

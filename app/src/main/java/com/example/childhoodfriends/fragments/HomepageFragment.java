package com.example.childhoodfriends.fragments;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
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

//        view.findViewById(R.id.btn_register).setOnClickListener(v -> {
//            goToRegister();
//        });
//
//        view.findViewById(R.id.btn_login).setOnClickListener(v -> {
//            goToLogin();
//        });

       Button btnLogin = (Button) view.findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickMeLogin();
                goToLogin();
            }
        });

        Button btnRegister =  view.findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickMeRegister();
                goToRegister();
            }
        });
    }

    private void ClickMeLogin() {
        NotificationCompat.Builder mBuilder;
        mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(getContext())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Log in notification")
                .setContentText("Successfully log in.");

        NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, mBuilder.build());
    }

    private void ClickMeRegister() {
        NotificationCompat.Builder mBuilder;
        mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(getContext())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Register notification")
                .setContentText("Successfully registered.");

        NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, mBuilder.build());
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
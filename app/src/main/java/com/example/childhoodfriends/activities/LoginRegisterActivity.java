package com.example.childhoodfriends.activities;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.childhoodfriends.R;
import com.example.childhoodfriends.fragments.HomepageFragment;
import com.example.childhoodfriends.fragments.LoginFragment;
import com.example.childhoodfriends.fragments.RegisterFragment;
import com.example.childhoodfriends.fragments.WelcomeFragment;
import com.example.childhoodfriends.interfaces.OnFragmentActivityCommunication;

import static com.example.childhoodfriends.fragments.LoginFragment.TAG_FRAGMENT_LOGIN;
import static com.example.childhoodfriends.fragments.RegisterFragment.TAG_FRAGMENT_REGISTER;

public class LoginRegisterActivity extends AppCompatActivity implements OnFragmentActivityCommunication {

    private Button btnLogin;
    private Button btnRegister;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        onAddHomepageFragment();

        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickMeLogin();
            }
        });

        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickMeRegister();
            }
        });
    }

    private void ClickMeLogin() {
        NotificationCompat.Builder mBuilder;
        mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Log in notification")
                .setContentText("Successfully log in.");

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, mBuilder.build());
    }

    private void ClickMeRegister() {
        NotificationCompat.Builder mBuilder;
        mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Register notification")
                .setContentText("Successfully registered.");

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, mBuilder.build());
    }

    private void onAddHomepageFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.login_register, HomepageFragment.newInstance(), WelcomeFragment.TAG_FRAGMENT_WELCOME);
        fragmentTransaction.commit();
    }

    public void onReplaceFragment(String TAG) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment;

        switch (TAG) {
            case TAG_FRAGMENT_REGISTER: {
                fragment = RegisterFragment.newInstance();
                break;
            }

            case TAG_FRAGMENT_LOGIN: {
                fragment = LoginFragment.newInstance();
                break;
            }

            default: return;
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.login_register, fragment,TAG);
        fragmentTransaction.commit();
    }
}

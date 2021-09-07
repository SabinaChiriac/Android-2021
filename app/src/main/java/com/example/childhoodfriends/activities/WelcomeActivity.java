package com.example.childhoodfriends.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.childhoodfriends.R;
import com.example.childhoodfriends.fragments.WelcomeFragment;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        onAddWelcomeFragment();
    }

    private void onAddWelcomeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fly_container, WelcomeFragment.newInstance(), WelcomeFragment.TAG_FRAGMENT_WELCOME);
        fragmentTransaction.commit();
    }
}

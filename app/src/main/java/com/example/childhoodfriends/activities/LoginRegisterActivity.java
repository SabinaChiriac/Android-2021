package com.example.childhoodfriends.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.childhoodfriends.R;
import com.example.childhoodfriends.fragments.HomepageFragment;
import com.example.childhoodfriends.fragments.LoginFragment;
import com.example.childhoodfriends.fragments.RegisterFragment;
import com.example.childhoodfriends.fragments.WelcomeFragment;

import static com.example.childhoodfriends.fragments.LoginFragment.TAG_FRAGMENT_LOGIN;
import static com.example.childhoodfriends.fragments.RegisterFragment.TAG_FRAGMENT_REGISTER;

public class LoginRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        onAddHomepageFragment();
    }

    private void onAddHomepageFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fly_container, HomepageFragment.newInstance(), WelcomeFragment.TAG_FRAGMENT_WELCOME);
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
        fragmentTransaction.replace(R.id.fly_container, fragment,TAG);
        fragmentTransaction.commit();
    }
}

package com.example.routeranger.ui.activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;

import com.example.routeranger.R;
import com.example.routeranger.databinding.ActivityLoginBinding;
import com.example.routeranger.ui.fragment.LoginFragment;
import com.example.routeranger.ui.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    protected Fragment createFragment() {
        return new LoginFragment();
    }
    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;
    private static final String TAG = "LoginActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i(TAG, "Calling onCreate()");

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.container);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }

    }
    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "Calling onPause()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "Calling onResume()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Calling onDestroy()");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "Calling onStop()");
    }
}
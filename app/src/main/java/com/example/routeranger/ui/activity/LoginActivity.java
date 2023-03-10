package com.example.routeranger.ui.activity;

import android.app.Activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.routeranger.R;
import com.example.routeranger.databinding.ActivityLoginBinding;
import com.example.routeranger.ui.fragment.LoginFragment;
import com.example.routeranger.ui.viewmodel.LoggedInUserView;
import com.example.routeranger.ui.viewmodel.LoginFormState;
import com.example.routeranger.ui.viewmodel.LoginResult;
import com.example.routeranger.ui.viewmodel.LoginViewModel;
import com.example.routeranger.ui.viewmodel.LoginViewModelFactory;

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
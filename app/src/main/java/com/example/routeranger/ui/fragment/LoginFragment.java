package com.example.routeranger.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.routeranger.databinding.ActivityLoginBinding;
import com.example.routeranger.ui.viewmodel.LoginViewModel;
import com.example.routeranger.ui.viewmodel.LoginViewModelFactory;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;
    private static final String TAG = "LoginFragment";

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Log.i(TAG, "Fragment Created!");
        Activity activity = requireActivity();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;
        Log.i(TAG, "Fragment View Created!");
        Activity activity = requireActivity();

        v = inflater.inflate();
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.login;
        final ProgressBar loadingProgressBar = binding.loading;
    }
}

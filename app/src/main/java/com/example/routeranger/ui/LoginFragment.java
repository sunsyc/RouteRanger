package com.example.routeranger.ui;

import android.app.Activity;

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
import com.example.routeranger.ui.login.LoginViewModel;
import com.example.routeranger.ui.login.LoginViewModelFactory;
import com.example.routeranger.databinding.ActivityLoginBinding;

public class LoginFragment extends AppCompatFragment {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;

    private static final String TAG = "LoginFragment";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;
        Timber.tag(TAG).d("onCreateView()");
        Activity activity = requireActivity();

        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();

        if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) {
            v = inflater.inflate(R.layout.fragment_login_land, container, false);
        } else {
            v = inflater.inflate(R.layout.fragment_login, container, false);
        }

        mUsernameEditText = v.findViewById(R.id.username_text);
        mPasswordEditText = v.findViewById(R.id.password_text);

        final Button loginButton = v.findViewById(R.id.login_button);
        if (loginButton != null) {
            loginButton.setOnClickListener(this);
        }
        final Button cancelButton = v.findViewById(R.id.cancel_button);
        if (cancelButton != null) {
            cancelButton.setOnClickListener(this);
        }

        final Button newUserButton = v.findViewById(R.id.new_user_button);
        if (newUserButton != null) {
            if (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180) {
                newUserButton.setOnClickListener(this);
            } else {
                newUserButton.setVisibility(View.GONE);
                newUserButton.invalidate();
            }
        }

        return v;
    }

}
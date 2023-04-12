package com.example.routeranger.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.routeranger.R;
import com.example.routeranger.ui.activity.NewRouteActivity;
import com.example.routeranger.ui.viewmodel.LoggedInUserView;
import com.example.routeranger.ui.viewmodel.LoginViewModel;
import com.example.routeranger.ui.viewmodel.ViewModelFactory;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private static final String TAG = "LoginFragment";

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Activity activity = requireActivity();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;
        Log.i(TAG, "Calling onCreateView()");
        Activity activity = requireActivity();

        v = inflater.inflate(R.layout.fragment_login, container, false);

        loginViewModel = new ViewModelProvider(this, new ViewModelFactory(activity.getApplicationContext()))
                .get(LoginViewModel.class);

        final EditText usernameEditText = v.findViewById(R.id.username);
        final EditText passwordEditText = v.findViewById(R.id.password);
        final Button loginButton = v.findViewById(R.id.login);
        final ProgressBar loadingProgressBar = v.findViewById(R.id.loading);;
        final Button backButton = v.findViewById(R.id.login_back_button);

        loginViewModel.getLoginFormState().observe(getViewLifecycleOwner(), loginFormState -> {
            if (loginFormState == null) {
                return;
            }
            loginButton.setEnabled(loginFormState.isDataValid());
            if (loginFormState.getUsernameError() != null) {
                usernameEditText.setError(getString(loginFormState.getUsernameError()));
            }
            if (loginFormState.getPasswordError() != null) {
                passwordEditText.setError(getString(loginFormState.getPasswordError()));
            }
        });

        loginViewModel.getLoginResult().observe(getViewLifecycleOwner(), loginResult -> {
            if (loginResult == null) {
                return;
            }
            loadingProgressBar.setVisibility(View.GONE);
            if (loginResult.getError() != null) {
                showLoginFailed(loginResult.getError(), activity);
            }
            if (loginResult.getSuccess() != null) {
                updateUiWithUser(loginResult.getSuccess(), activity);
            }
            activity.setResult(Activity.RESULT_OK);

            //Complete and destroy login fragment once successful
            activity.finish();
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener((v1, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
            return false;
        });

        loginButton.setOnClickListener(v12 -> {
            loadingProgressBar.setVisibility(View.VISIBLE);
            loginViewModel.login(usernameEditText.getText().toString(),
                    passwordEditText.getText().toString());
            switchToNewRoute();

        });

        backButton.setOnClickListener(view -> getActivity().finish());

        return v;
    }

    private void updateUiWithUser(LoggedInUserView model, Activity activity) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(activity.getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString, Activity activity) {
        Toast.makeText(activity.getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    private void switchToNewRoute() {
        Intent switchActivityIntent = new Intent(getActivity(), NewRouteActivity.class);
        startActivity(switchActivityIntent);
    }
}

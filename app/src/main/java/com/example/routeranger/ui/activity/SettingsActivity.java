package com.example.routeranger.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.routeranger.R;
import com.example.routeranger.model.User;
import com.example.routeranger.ui.viewmodel.SettingsViewModel;
import com.example.routeranger.ui.viewmodel.ViewModelFactory;
import com.google.common.util.concurrent.ListenableFuture;

public class SettingsActivity extends AppCompatActivity {

    private static final String TAG = "SettingsActivity";
    private SettingsViewModel settingsViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        EditText usernameEditText = findViewById(R.id.nameInput);
        EditText passwordEditText = findViewById(R.id.passwordInput);
        EditText locationEditText = findViewById(R.id.locationInput);
        final Button updateSettingsButton = findViewById(R.id.update_settings_button);
        final Button backButton = findViewById(R.id.settings_back_button);
        final Button deleteButton = findViewById(R.id.settings_delete_button);

        settingsViewModel = new ViewModelProvider(this, new ViewModelFactory(getApplicationContext()))
                .get(SettingsViewModel.class);

        if (!settingsViewModel.isUserLoggedIn()) {
            this.finish();
        }

        settingsViewModel.populateFields(usernameEditText, passwordEditText, locationEditText);

        updateSettingsButton.setOnClickListener(view -> {
            settingsViewModel.updateSettings(
                    usernameEditText.getText().toString(),
                    passwordEditText.getText().toString(),
                    locationEditText.getText().toString()
            );
            this.finish();
        });

        backButton.setOnClickListener(view -> this.finish());

        deleteButton.setOnClickListener(view -> {
            settingsViewModel.deleteUser();
            switchToHome();
        });
    }

    private void switchToHome() {
        Intent switchActivityIntent = new Intent(this, HomePageActivity.class);
        startActivity(switchActivityIntent);
    }
}
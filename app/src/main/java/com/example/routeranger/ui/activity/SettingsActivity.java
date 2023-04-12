package com.example.routeranger.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.routeranger.R;
import com.example.routeranger.ui.viewmodel.SettingsViewModel;
import com.example.routeranger.ui.viewmodel.ViewModelFactory;

public class SettingsActivity extends AppCompatActivity {

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

        settingsViewModel = new ViewModelProvider(this, new ViewModelFactory(getApplicationContext()))
                .get(SettingsViewModel.class);

        if (!settingsViewModel.isUserLoggedIn()) {
            this.finish();
        }

        settingsViewModel.populateFields();
        usernameEditText.setText(settingsViewModel.initName);
        passwordEditText.setText(settingsViewModel.initPass);
        locationEditText.setText(settingsViewModel.initLoc);

        updateSettingsButton.setOnClickListener((view -> settingsViewModel.updateSettings(usernameEditText.getText().toString(),
                passwordEditText.getText().toString(),
                locationEditText.getText().toString())));

        backButton.setOnClickListener(view -> this.finish());
    }
}
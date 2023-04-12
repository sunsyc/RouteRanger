package com.example.routeranger.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.routeranger.R;

public class NewRouteActivity extends AppCompatActivity {

    private Button settingsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_route);

        final Button routeNameButton = findViewById(R.id.routeNameButton);
        final Button startDestinationButton = findViewById(R.id.startDestinationButton);
        final Button endDestinationButton = findViewById(R.id.endDestinationButton);
        final Button desiredLengthTimeButton = findViewById(R.id.desiredLengthTimeButton);
        final Button generateRoutesButton = findViewById(R.id.generateRoutesButton);
        final Button backButton = findViewById(R.id.newroute_back_button);

        routeNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle routeNameButton click here
            }
        });

        startDestinationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle startDestinationButton click here
            }
        });

        endDestinationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle endDestinationButton click here
            }
        });

        desiredLengthTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle desiredLengthTimeButton click here
            }
        });

        generateRoutesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle generateRoutesButton click here
            }
        });
        settingsButton = findViewById(R.id.settings_button);

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSettings();
            }
        });

        backButton.setOnClickListener(view -> this.finish());
    }

    private void switchToSettings() {
        Intent switchActivityIntent = new Intent(this, SettingsActivity.class);
        startActivity(switchActivityIntent);
    }
}


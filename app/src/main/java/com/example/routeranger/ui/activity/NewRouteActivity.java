package com.example.routeranger.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.routeranger.R;

public class NewRouteActivity extends AppCompatActivity {

    private Button settingsButton;
    private String startDestination;
    private String endDestination;


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
                showInputDialog("Enter Start Destination", (dialog, which) -> {
                    startDestination = ((EditText)((AlertDialog)dialog).findViewById(R.id.input_dialog_text)).getText().toString();
                });
            }
        });

        endDestinationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog("Enter End Destination", (dialog, which) -> {
                    endDestination = ((EditText)((AlertDialog)dialog).findViewById(R.id.input_dialog_text)).getText().toString();
                });
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
                if (startDestination != null && !startDestination.isEmpty() &&
                        endDestination != null && !endDestination.isEmpty()) {
                    Intent intent = new Intent(NewRouteActivity.this, MapActivity.class);
                    intent.putExtra("startDestination", startDestination);
                    intent.putExtra("endDestination", endDestination);
                    startActivity(intent);
                } else {
                    Toast.makeText(NewRouteActivity.this, "Please enter both start and end destinations.", Toast.LENGTH_SHORT).show();
                }
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

    private void showInputDialog(String title, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setId(R.id.input_dialog_text);
        builder.setView(input);

        builder.setPositiveButton("OK", listener);
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }


}


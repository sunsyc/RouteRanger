package com.example.routeranger.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.routeranger.R;
import com.example.routeranger.ui.viewmodel.NewRouteViewModel;
import com.example.routeranger.ui.viewmodel.ViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NewRouteActivity extends AppCompatActivity {

    private FloatingActionButton settingsButton;
    private NewRouteViewModel newRouteViewModel;

    private EditText routeNameET, startDestET, endDestET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_route);

//        final Button desiredLengthTimeButton = findViewById(R.id.desiredLengthTimeButton);
        final Button generateRoutesButton = findViewById(R.id.generateRoutesButton);
        final Button backButton = findViewById(R.id.newroute_back_button);

        routeNameET = findViewById(R.id.routeNameET);
        startDestET = findViewById(R.id.startDestET);
        endDestET = findViewById(R.id.endDestET);

        newRouteViewModel = new ViewModelProvider(this, new ViewModelFactory(getApplicationContext()))
                .get(NewRouteViewModel.class);
//        desiredLengthTimeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle desiredLengthTimeButton click here
//            }
//        });

        generateRoutesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkConnected()) {
                    String routeName = routeNameET.getText().toString();
                    String startDestination = startDestET.getText().toString();
                    String endDestination = endDestET.getText().toString();
                    if (startDestination != null && !startDestination.isEmpty() &&
                            endDestination != null && !endDestination.isEmpty()) {
                        Intent intent = new Intent(NewRouteActivity.this, MapActivity.class);
                        intent.putExtra("startDestination", startDestination);
                        intent.putExtra("endDestination", endDestination);
                        newRouteViewModel.saveRoute(routeName, startDestination, endDestination);
                        startActivity(intent);
                    } else {
                        Toast.makeText(NewRouteActivity.this, "Please enter both start and end destinations.", Toast.LENGTH_SHORT)
                                .show();
                    }
                } else {
                    Toast.makeText(NewRouteActivity.this, "No internet connection detected, please connect to the Internet", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

        settingsButton = findViewById(R.id.settings_button);

        settingsButton.setOnClickListener(v -> switchToSettings());

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

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onResume() {
        super.onResume();
        routeNameET.setText("");
        startDestET.setText("");
        endDestET.setText("");
    }


}


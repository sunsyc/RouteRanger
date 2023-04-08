package com.example.routeranger.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.routeranger.R;

public class NewRouteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_route);

        Button routeNameButton = findViewById(R.id.routeNameButton);
        Button startDestinationButton = findViewById(R.id.startDestinationButton);
        Button endDestinationButton = findViewById(R.id.endDestinationButton);
        Button desiredLengthTimeButton = findViewById(R.id.desiredLengthTimeButton);
        Button generateRoutesButton = findViewById(R.id.generateRoutesButton);

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
    }
}

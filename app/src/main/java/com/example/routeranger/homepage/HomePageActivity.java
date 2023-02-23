//package com.example.routeranger.homepage;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class HomePageActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home_page);
//
//        Button newRouteButton = findViewById(R.id.new_route_button);
//        newRouteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(HomePageActivity.this, NewRouteActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        Button existingRouteButton = findViewById(R.id.existing_route_button);
//        existingRouteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(HomePageActivity.this, ExistingRouteActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        Button settingsButton = findViewById(R.id.settings_button);
//        settingsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(HomePageActivity.this, SettingsActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//}
//

package com.example.routeranger.ui.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.example.routeranger.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.android.PolyUtil;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;

import java.util.List;



public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String startDestination = getIntent().getStringExtra("startDestination");
        String endDestination = getIntent().getStringExtra("endDestination");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        String startDestination = getIntent().getStringExtra("startDestination");
        String endDestination = getIntent().getStringExtra("endDestination");

        GeoApiContext geoApiContext = new GeoApiContext.Builder()
                .apiKey("AIzaSyD8IYdpVdJrkoSbhLD3mBonwGh2F1DBVuA")
                .build();

        DirectionsApi.getDirections(geoApiContext, startDestination, endDestination)
                .setCallback(new PendingResult.Callback<DirectionsResult>() {
                    @Override
                    public void onResult(DirectionsResult result) {
                        // Get the first route (the most optimal route)
                        DirectionsRoute route = result.routes[0];

                        // Decode the overview polyline of the route
                        List<LatLng> decodedPath = PolyUtil.decode(route.overviewPolyline.getEncodedPath());

                        runOnUiThread(() -> {
                            // Draw the polyline on the map
                            mMap.addPolyline(new PolylineOptions().addAll(decodedPath));

                            // Move the camera to show the entire route
                            LatLngBounds.Builder builder = new LatLngBounds.Builder();
                            for (LatLng point : decodedPath) {
                                builder.include(point);
                            }
                            LatLngBounds bounds = builder.build();
                            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));
                        });
                    }


                    @Override
                    public void onFailure(Throwable e) {
                        // Handle the error
                        Log.e("RouteResult", "Error fetching directions", e);
                    }
                });
    }

}

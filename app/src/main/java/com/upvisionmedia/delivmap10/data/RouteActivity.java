package com.upvisionmedia.delivmap10.data;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.upvisionmedia.delivmap10.R;

import java.util.ArrayList;

public class RouteActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_activity);

        // Retrieve destinations from intent extras
        ArrayList<String> destinations = getIntent().getStringArrayListExtra("destinations");

        // Display the destinations in the layout
        TextView destinationsTextView = findViewById(R.id.destinations_textview);
        StringBuilder destinationsBuilder = new StringBuilder();
        assert destinations != null;
        for (String destination : destinations) {
            destinationsBuilder.append(destination).append("\n");
        }
        destinationsTextView.setText(destinationsBuilder.toString());

        // Initialize the map fragment
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap map) {

        // Add markers for each destination
        ArrayList<String> destinations = getIntent().getStringArrayListExtra("destinations");
        assert destinations != null;
        for (String destination : destinations) {
            // Parse the destination coordinates (assuming format: "latitude,longitude")
            String[] coordinates = destination.split(",");
            double latitude = Double.parseDouble(coordinates[0]);
            double longitude = Double.parseDouble(coordinates[1]);

            // Create a marker and add it to the map
            LatLng location = new LatLng(latitude, longitude);
            MarkerOptions markerOptions = new MarkerOptions().position(location);
            Marker marker = map.addMarker(markerOptions);
        }

        // Set the camera position to the first destination
        String[] firstCoordinates = destinations.get(0).split(",");
        double firstLatitude = Double.parseDouble(firstCoordinates[0]);
        double firstLongitude = Double.parseDouble(firstCoordinates[1]);
        LatLng firstLocation = new LatLng(firstLatitude, firstLongitude);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(firstLocation, 12));
    }
}

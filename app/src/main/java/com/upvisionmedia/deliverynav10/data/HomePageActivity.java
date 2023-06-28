package com.upvisionmedia.deliverynav10.data;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.upvisionmedia.deliverynav10.R;

import java.io.IOException;
import java.util.List;

public class HomePageActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private EditText destinationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        destinationEditText = findViewById(R.id.destinationEditText);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

        Button goButton = findViewById(R.id.goButton);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String destination = destinationEditText.getText().toString();
                if (!destination.isEmpty()) {
                    findDestination(destination);
                } else {
                    Toast.makeText(HomePageActivity.this, "Please enter a destination", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        LatLng defaultLocation = new LatLng(37.7749, -122.4194); // Default location (e.g., San Francisco)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 12f));
    }

    private void findDestination(String destination) {
        Geocoder geocoder = new Geocoder(this);
        try {
            List<Address> addressList = geocoder.getFromLocationName(destination, 1);
            if (addressList != null && addressList.size() > 0) {
                Address address = addressList.get(0);
                LatLng destinationLatLng = new LatLng(address.getLatitude(), address.getLongitude());
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(destinationLatLng, 12f));
            } else {
                Toast.makeText(this, "Destination not found", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error finding destination", Toast.LENGTH_SHORT).show();
        }
    }
}

package com.upvisionmedia.delivmap10.data;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.upvisionmedia.delivmap10.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private List<String> destinations;
    private DestinationAdapter destinationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        destinations = new ArrayList<>();
        destinationAdapter = new DestinationAdapter(destinations);

        RecyclerView destinationRecyclerView = findViewById(R.id.destinationRecyclerView);
        destinationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        destinationRecyclerView.setAdapter(destinationAdapter);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

        Button goButton = findViewById(R.id.goButton);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!destinations.isEmpty()) {
                    for (String destination : destinations) {
                        findDestination(destination);
                    }
                } else {
                    Toast.makeText(HomePageActivity.this, "Please enter at least one destination", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button addDestinationButton = findViewById(R.id.addDestinationButton);
        addDestinationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDestinationField();
            }
        });

        addDestinationField();
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
                Toast.makeText(this, "Destination not found: " + destination, Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error finding destination: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    private void addDestinationField() {
        destinations.add("");
        destinationAdapter.notifyItemInserted(destinations.size() - 1);
    }

    private class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.ViewHolder> {

        private List<String> destinations;

        public DestinationAdapter(List<String> destinations) {
            this.destinations = destinations;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.destination_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            String destination = destinations.get(position);
            holder.destinationEditText.setText(destination);
            holder.destinationEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        destinations.set(holder.getAdapterPosition(), holder.destinationEditText.getText().toString());
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return destinations.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            EditText destinationEditText;

            public ViewHolder(View itemView) {
                super(itemView);
                destinationEditText = itemView.findViewById(R.id.destinationEditText);
            }
        }
    }
}

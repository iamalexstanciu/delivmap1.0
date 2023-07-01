package com.upvisionmedia.delivmap10.data;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.upvisionmedia.delivmap10.R;

import java.util.ArrayList;

public class RouteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_activity);

        // Retrieve destinations from intent extras
        ArrayList<String> destinations = getIntent().getStringArrayListExtra("destinations");

        // Display the destinations in the layout
        TextView destinationsTextView = findViewById(R.id.destinations_textview);
        StringBuilder destinationsBuilder = new StringBuilder();
        for (String destination : destinations) {
            destinationsBuilder.append(destination).append("\n");
        }
        destinationsTextView.setText(destinationsBuilder.toString());
    }
}

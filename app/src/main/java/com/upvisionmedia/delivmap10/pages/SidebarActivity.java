package com.upvisionmedia.delivmap10.pages;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.upvisionmedia.delivmap10.R;

public class SidebarActivity extends AppCompatActivity implements View.OnClickListener {
    private Button profile_button, historyButton, settingsButton, contactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sidebar);

        // Find buttons by their IDs
        profile_button = findViewById(R.id.profile_button);
        historyButton = findViewById(R.id.historyButton);
        settingsButton = findViewById(R.id.settingsButton);
        contactButton = findViewById(R.id.contactButton);

        // Set click listeners for the buttons
        profile_button.setOnClickListener(this);
        historyButton.setOnClickListener(this);
        settingsButton.setOnClickListener(this);
        contactButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // Handle button clicks
        if (view.getId() == R.id.profile_button) {
            // Handle Profile button click
            // Add your implementation here
        } else if (view.getId() == R.id.historyButton) {
            // Handle History button click
            // Add your implementation here
        } else if (view.getId() == R.id.settingsButton) {
            // Handle Settings button click
            // Add your implementation here
        } else if (view.getId() == R.id.contactButton) {
            // Handle Contact button click
            // Add your implementation here
        }
    }
}

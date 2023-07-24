package com.upvisionmedia.delivmap10.pages.sidebar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.upvisionmedia.delivmap10.R;

public class SidebarActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sidebar);

        // Buttons on sidebar
        Button profile_button = findViewById(R.id.profile_button);
        Button historyButton = findViewById(R.id.historyButton);
        Button settingsButton = findViewById(R.id.settingsButton);
        Button contactButton = findViewById(R.id.contactButton);

        // Listeners on buttons
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
            openProfileLayout();
        } else if (view.getId() == R.id.historyButton) {
            // Handle History button click
            openHistoryLayout();
        } else if (view.getId() == R.id.settingsButton) {
            // Handle Settings button click
            openSettingsLayout();
        } else if (view.getId() == R.id.contactButton) {
            // Handle Contact button click
            openContactLayout();
        }

    }
    private void openProfileLayout() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    private void openHistoryLayout() {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    private void openSettingsLayout() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    private void openContactLayout() {
        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }

}

package com.upvisionmedia.delivmap10.homepage;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.upvisionmedia.delivmap10.R;
import com.upvisionmedia.delivmap10.pages.fragments.DelivFragment;

public class MessagesActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messages_activity);

        // Bottom app bar functionality

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        floatingActionButton = findViewById(R.id.fab);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_home) {
                // Handle home button click
                Intent intent = new Intent(this, MainMenu.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.nav_message) {
                // Handle messages button click
                Intent intent = new Intent(this, MessagesActivity.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.nav_settings) {
                // Handle settings button click
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.nav_profile) {
                // Handle profile button click
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                return true;
            }
            return false;
        });

        // Floating button functionality

        floatingActionButton.setOnClickListener(v -> {
            replaceFragment(new DelivFragment());
        });

    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_main_menu, fragment)
                .addToBackStack(null)
                .commit();
    }

}
package com.upvisionmedia.delivmap10.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.upvisionmedia.delivmap10.R;
import com.upvisionmedia.delivmap10.pages.fragments.DelivFragment;

public class MainMenu extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FloatingActionButton floatingActionButton;
    TextView usernameDisplayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

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


        // Display user email if available, or "Not logged in!" if not
        String userEmail = getIntent().getStringExtra("user_email");
        usernameDisplayed = findViewById(R.id.usernameDisplayed);
        if (usernameDisplayed != null) {
            if (userEmail != null && !userEmail.isEmpty()) {
                usernameDisplayed.setText(userEmail);
            } else {
                String notLogged = "Not logged in!";
                usernameDisplayed.setText(notLogged);
            }
        }


    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_main_menu, fragment)
                .addToBackStack(null)
                .commit();
    }


}

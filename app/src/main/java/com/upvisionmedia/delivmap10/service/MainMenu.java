package com.upvisionmedia.delivmap10.service;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.core.view.GravityCompat;


import com.upvisionmedia.delivmap10.R;
import com.upvisionmedia.delivmap10.homepage.DelivFragment;
import com.upvisionmedia.delivmap10.homepage.MessagesFragment;
import com.upvisionmedia.delivmap10.homepage.TrafficFragment;
import com.upvisionmedia.delivmap10.pages.sidebar.HomeFragment;
import com.upvisionmedia.delivmap10.pages.sidebar.ProfileFragment;
import com.upvisionmedia.delivmap10.pages.sidebar.SettingsFragment;
import com.upvisionmedia.delivmap10.pages.sidebar.StatsFragment;

public class MainMenu extends AppCompatActivity {

    private Fragment deliveries, messages, traffic, profile, settings, stats;
    TextView usernameDisplayed;

    ImageView menuIcon;
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        deliveries = new DelivFragment();
        messages = new MessagesFragment();
        traffic = new TrafficFragment();
        profile = new ProfileFragment();
        settings = new SettingsFragment();
        stats = new StatsFragment();

        LinearLayout layoutDeliv = findViewById(R.id.layoutDeliveries);
        LinearLayout layoutMessages = findViewById(R.id.layoutMessages);
        LinearLayout layoutTraffic = findViewById(R.id.layoutTraffic);
        LinearLayout layoutProfile = findViewById(R.id.layoutProfile);
        LinearLayout layoutSettings = findViewById(R.id.layoutSettings);
        LinearLayout layoutStats = findViewById(R.id.layoutStatistics);

        layoutDeliv.setOnClickListener(v -> replaceFragment(deliveries));
        layoutMessages.setOnClickListener(v -> replaceFragment(messages));
        layoutTraffic.setOnClickListener(v -> replaceFragment(traffic));
        layoutProfile.setOnClickListener(v -> replaceFragment(profile));
        layoutSettings.setOnClickListener(v -> replaceFragment(settings));
        layoutStats.setOnClickListener(v -> replaceFragment(stats));


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
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_main_menu, fragment).addToBackStack(null).commit();
    }


}

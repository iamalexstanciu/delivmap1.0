package com.upvisionmedia.delivmap10.service;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.upvisionmedia.delivmap10.R;
import com.upvisionmedia.delivmap10.homepage.DelivFragment;
import com.upvisionmedia.delivmap10.homepage.MessagesFragment;
import com.upvisionmedia.delivmap10.homepage.TrafficFragment;
import com.upvisionmedia.delivmap10.pages.sidebar.ProfileFragment;
import com.upvisionmedia.delivmap10.pages.sidebar.SettingsFragment;
import com.upvisionmedia.delivmap10.pages.sidebar.StatsFragment;

public class MainMenu extends AppCompatActivity {

    private Fragment deliveries, messages, traffic, profile, settings, stats;
    TextView usernameDisplayed;


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

        layoutDeliv.setOnClickListener( v-> replaceFragment(deliveries));
        layoutMessages.setOnClickListener( v-> replaceFragment(messages));
        layoutTraffic.setOnClickListener( v-> replaceFragment(traffic));
        layoutProfile.setOnClickListener( v-> replaceFragment(profile));
        layoutSettings.setOnClickListener( v-> replaceFragment(settings));
        layoutStats.setOnClickListener( v-> replaceFragment(stats));


        // diplay user email

        String userEmail = getIntent().getStringExtra("user_email");
        usernameDisplayed = findViewById(R.id.usernameDisplayed);
        usernameDisplayed.setText(userEmail);
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_main_menu, fragment)
                .addToBackStack(null)
                .commit();
    }


}

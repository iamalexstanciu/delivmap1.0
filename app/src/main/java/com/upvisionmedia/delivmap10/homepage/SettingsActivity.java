package com.upvisionmedia.delivmap10.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.upvisionmedia.delivmap10.R;
import com.upvisionmedia.delivmap10.pages.fragments.DelivFragment;
import com.upvisionmedia.delivmap10.pages.fragments.settings.AboutFragment;
import com.upvisionmedia.delivmap10.pages.fragments.settings.AccountFragment;
import com.upvisionmedia.delivmap10.pages.fragments.settings.GeneralFragment;
import com.upvisionmedia.delivmap10.pages.fragments.settings.LanguageFragment;
import com.upvisionmedia.delivmap10.pages.fragments.settings.NotificationsFragment;
import com.upvisionmedia.delivmap10.pages.fragments.settings.SupportFragment;
import com.upvisionmedia.delivmap10.pages.fragments.settings.ThemeFragment;

public class SettingsActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        // Buttons functionality

        Button buttonAccount = findViewById(R.id.buttonAccount);
        Button buttonAbout = findViewById(R.id.buttonAbout);
        Button buttonSupport = findViewById(R.id.buttonSupport);
        Button buttonGeneral = findViewById(R.id.buttonGeneral);
        Button buttonLanguage = findViewById(R.id.buttonLanguage);
        Button buttonTheme = findViewById(R.id.buttonTheme);
        Button buttonNotifications = findViewById(R.id.buttonNotifications);


        buttonAbout.setOnClickListener(view -> loadFragment(new AboutFragment()));
        buttonAccount.setOnClickListener(view -> loadFragment(new AccountFragment()));
        buttonSupport.setOnClickListener(view -> loadFragment(new SupportFragment()));
        buttonGeneral.setOnClickListener(view -> loadFragment(new GeneralFragment()));
        buttonLanguage.setOnClickListener(view -> loadFragment(new LanguageFragment()));
        buttonTheme.setOnClickListener(view -> loadFragment(new ThemeFragment()));
        buttonNotifications.setOnClickListener(view -> loadFragment(new NotificationsFragment()));



        // Bottom app bar functionality

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

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


    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_settings, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}

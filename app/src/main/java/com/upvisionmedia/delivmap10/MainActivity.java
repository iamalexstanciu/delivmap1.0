package com.upvisionmedia.delivmap10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.upvisionmedia.delivmap10.pages.HomePageActivity;
import com.upvisionmedia.delivmap10.service.SignInActivity;

public class MainActivity extends AppCompatActivity {

    private Button signInButton;
    private Button infoButton;
    private Button sidebarButton;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView welcomeText = findViewById(R.id.welcomeText);
        signInButton = findViewById(R.id.signInButton);
        infoButton = findViewById(R.id.infoButton);
        sidebarButton = findViewById(R.id.sidebarButton);
        drawerLayout = findViewById(R.id.drawerLayout);

        welcomeText.setText("Welcome to DelivMap");

        signInButton.setOnClickListener(v -> {
            // Start the sign-in activity
            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(intent);
        });

        infoButton.setOnClickListener(v -> {
            // Start the home page activity
            Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
            startActivity(intent);
        });

        sidebarButton.setOnClickListener(v -> {
            // Open the sidebar
            openSidebar();
        });
    }

    private void openSidebar() {
        if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }
}

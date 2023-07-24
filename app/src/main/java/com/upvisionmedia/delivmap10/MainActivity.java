package com.upvisionmedia.delivmap10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.upvisionmedia.delivmap10.pages.HomePageActivity;
import com.upvisionmedia.delivmap10.service.SignInActivity;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView welcomeText = findViewById(R.id.welcomeText);
        Button signInButton = findViewById(R.id.signInButton);
        Button infoButton = findViewById(R.id.infoButton);
        Button sidebarButton = findViewById(R.id.sidebarButton);
        drawerLayout = findViewById(R.id.drawerLayout);

        welcomeText.setText(R.string.welcome_to_delivmap2);

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

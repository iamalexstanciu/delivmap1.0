package com.upvisionmedia.deliverynav10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button signInButton;
    private Button infoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView welcomeText = findViewById(R.id.welcomeText);
        signInButton = findViewById(R.id.signInButton);
        infoButton = findViewById(R.id.infoButton);

        welcomeText.setText("Welcome to DeliveryNav");

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the sign-in activity
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
            }

        });
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the sign-in activity
                Intent intent = new Intent(MainActivity.this, InfoDisplayActivity.class);
                startActivity(intent);
            }

        });
    }
}

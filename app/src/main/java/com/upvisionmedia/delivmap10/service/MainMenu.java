package com.upvisionmedia.delivmap10.service;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.upvisionmedia.delivmap10.R;

public class MainMenu extends AppCompatActivity {

    TextView usernameDisplayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);


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

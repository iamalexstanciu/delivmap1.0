package com.upvisionmedia.delivmap10.pages.sidebar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.upvisionmedia.delivmap10.R;
import com.upvisionmedia.delivmap10.model.User;
import com.upvisionmedia.delivmap10.service.LoginActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Find views by their IDs
        ImageView profileImageView = findViewById(R.id.profile_image_view);
        TextView firstNameTextView = findViewById(R.id.first_name_text_view);
        TextView lastNameTextView = findViewById(R.id.last_name_text_view);
        TextView emailTextView = findViewById(R.id.email_text_view);
        Button logoutButton = findViewById(R.id.logout_button);

        // Set user data
        User user = getUserData(); // Replace this with your own logic to fetch user data
        profileImageView.setImageResource(user.getProfileImage());
        firstNameTextView.setText(user.getFirstName());
        lastNameTextView.setText(user.getLastName());
        emailTextView.setText(user.getEmail());

        // Set click listener for the logout button
        logoutButton.setOnClickListener(view -> logout());
    }

    private User getUserData() {
        // Replace this with your own logic to fetch user data from wherever it is stored
        // Return a User object containing the required data (profile image, first name, last name, email)
        // Example:
        int profileImage = R.drawable.profile;
        String firstName = "John";
        String lastName = "Doe";
        String email = "johndoe@example.com";

        return new User(profileImage, firstName, lastName, email);
    }

    private void logout() {
        // Perform logout actions here, such as clearing session data, navigating back to the login screen, etc.

        // Example: Clear session data
        clearSessionData();

        // Example: Navigate back to the login screen
        navigateToLoginScreen();
    }

    private void clearSessionData() {
        // Replace this with your own logic to clear session data, such as clearing shared preferences, removing stored tokens, etc.
        // Example:
        SharedPreferences preferences = getSharedPreferences("MyApp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    private void navigateToLoginScreen() {
        // Replace this with your own logic to navigate back to the login screen, such as starting a new LoginActivity or finishing the current activity.
        // Example:
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}

package com.upvisionmedia.delivmap10.homepage;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.upvisionmedia.delivmap10.R;
import com.upvisionmedia.delivmap10.pages.fragments.DelivFragment;
import com.upvisionmedia.delivmap10.service.user.SignInActivity;

public class ProfileActivity extends Activity {

    GoogleSignInClient googleClient;
    GoogleSignInOptions googleOptions;
    FirebaseAuth firebaseAuth;
    private BottomNavigationView bottomNavigationView;


    private ImageView profileImage;

    private static final int GALLERY_REQUEST_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

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

        // Initialize the Spinner and ArrayAdapter
        Spinner spinnerCountry = findViewById(R.id.spinnerCountry);
        ArrayAdapter<CharSequence> countryAdapter = ArrayAdapter.createFromResource(
                this, R.array.country_names, android.R.layout.simple_spinner_item
        );

        // Specify the layout to use when the list of choices appears
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerCountry.setAdapter(countryAdapter);

        // Now you can access views by their ID using 'findViewById'
        Button logout = findViewById(R.id.logoutButton);
        profileImage = findViewById(R.id.profileImage);
        EditText editTextFirstname = findViewById(R.id.editTextFirstname);
        EditText editTextLastName = findViewById(R.id.editTextLastName);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        Button gallerySelect = findViewById(R.id.galleryButton);

        googleOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleClient = GoogleSignIn.getClient(this, googleOptions);
        firebaseAuth = FirebaseAuth.getInstance();

        GoogleSignInAccount googleAccount = GoogleSignIn.getLastSignedInAccount(this);

        // Display user email if logged w/Google
        if (googleAccount != null) {
            String googleName = googleAccount.getDisplayName();

            assert googleName != null;
            String[] names = googleName.split("\\s+");
            String firstName = names[0];
            String lastName = names[names.length - 1];

            String email = googleAccount.getEmail();

            editTextEmail.setText(email);
            editTextFirstname.setText(firstName);
            editTextLastName.setText(lastName);
            // Set the Google Account profile image
            if (googleAccount.getPhotoUrl() != null) {
                String imageUrl = googleAccount.getPhotoUrl().toString();
                Glide.with(this).load(imageUrl).into(profileImage);
            }
        }

        // Set profile picture
        gallerySelect.setOnClickListener(v -> {
            Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
            galleryIntent.setType("image/*");
            startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
        });

        // Check if is logged with Google for logout
        if (googleAccount != null || firebaseAuth.getCurrentUser() != null) {
            logout.setVisibility(View.VISIBLE);
        } else {
            logout.setVisibility(View.GONE);
        }

        logout.setOnClickListener(v -> {
            // Show a confirmation dialog to the user before logging out
            new AlertDialog.Builder(this)
                    .setTitle("Logout")
                    .setMessage("Are you sure you want to logout?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        // User confirmed

                        // Check if is logged using Google Account
                        if (googleAccount != null) {
                            googleClient.signOut().addOnCompleteListener(task -> {
                                // Redirect to SignIn
                                navigateToSignIn();
                            });
                        } else {
                            // Firebase logout
                            firebaseAuth.signOut();

                            // Redirect to SignIn
                            navigateToSignIn();
                        }
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                        // User chose no
                        dialog.dismiss();
                    })
                    .show();
        });
    }


    private void navigateToSignIn() {
        Intent intent = new Intent(this, SignInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                // Get the selected image URI
                Uri selectedImageUri = data.getData();
                // Set the profile image with the selected image
                Glide.with(this).load(selectedImageUri).into(profileImage);
            }
        }
    }
}

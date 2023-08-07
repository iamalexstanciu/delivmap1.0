package com.upvisionmedia.delivmap10.pages.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.upvisionmedia.delivmap10.R;
import com.upvisionmedia.delivmap10.service.user.SignInActivity;

public class ProfileFragment extends Fragment {

    GoogleSignInClient googleClient;
    GoogleSignInOptions googleOptions;
    FirebaseAuth firebaseAuth;

    private ImageView profileImage;

    private Uri selectedImageUri;
    private ActivityResultLauncher<String> galleryLauncher;
    private static final int GALLERY_REQUEST_CODE = 1001;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Now you can access views by their ID using 'view'
        Button logout = view.findViewById(R.id.logoutButton);
        profileImage = view.findViewById(R.id.profileImage);
        EditText editTextFirstname = view.findViewById(R.id.editTextFirstname);
        EditText editTextLastName = view.findViewById(R.id.editTextLastName);
        Button gallerySelect = view.findViewById(R.id.galleryButton);


        googleOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleClient = GoogleSignIn.getClient(requireContext(), googleOptions);
        firebaseAuth = FirebaseAuth.getInstance();

        GoogleSignInAccount googleAccount = GoogleSignIn.getLastSignedInAccount(requireContext());

        // Display user email if logged w/Google

        if (googleAccount != null) {
            String googleName = googleAccount.getDisplayName();

            assert googleName != null;
            String[] names = googleName.split("\\s+");
            String firstName = names[0];
            String lastName = names[names.length - 1];

            editTextFirstname.setText(firstName);
            editTextLastName.setText(lastName);
            // Set the Google Account profile image
            if (googleAccount.getPhotoUrl() != null) {
                String imageUrl = googleAccount.getPhotoUrl().toString();
                Glide.with(requireContext()).load(imageUrl).into(profileImage);
            }
        }

        // Set profile picture

        galleryLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), r->{
            if(r != null){
                //Get the selected image URI
                selectedImageUri = r;
                //Set the profile image with the selected image
                Glide.with(requireContext()).load(selectedImageUri).into(profileImage);
            }
        });

        // onClickListener for gallery button

        gallerySelect.setOnClickListener(v->{
            galleryLauncher.launch("image/*");
        });

        // Check if is logged with Google for logout

        if (googleAccount != null) {
            logout.setVisibility(View.VISIBLE);
        } else {
            logout.setVisibility(View.GONE);
        }

        if (firebaseAuth.getCurrentUser() != null){
            logout.setVisibility(View.VISIBLE);
        }

        logout.setOnClickListener(v -> {
            // Show a confirmation dialog to the user before logging out
            new AlertDialog.Builder(requireContext())
                    .setTitle("Logout")
                    .setMessage("Are you sure you want to logout?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        // User confirmed

                        //Check if is logged using Google Account
                        if (googleAccount != null) {
                            googleClient.signOut().addOnCompleteListener(t -> {
                                //Redirect to SignIn
                                navigateToSignIn();
                            });
                        } else {
                            //Firebase logout
                            firebaseAuth.signOut();

                            // Redirect to SignIn
                            navigateToSignIn();
                        }
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                        // User choose no
                        dialog.dismiss();
                    })
                    .show();


        });
        return view;
    }

    private void navigateToSignIn() {

        Intent intent = new Intent(requireContext(), SignInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                // Get the selected image URI
                selectedImageUri = data.getData();
                // Set the profile image with the selected image
                Glide.with(requireContext()).load(selectedImageUri).into(profileImage);
            }
        }
    }
}

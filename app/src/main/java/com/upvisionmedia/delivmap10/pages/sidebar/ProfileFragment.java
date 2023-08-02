package com.upvisionmedia.delivmap10.pages.sidebar;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Now you can access views by their ID using 'view'
        TextView username = view.findViewById(R.id.username);
        Button logout = view.findViewById(R.id.logoutButton);

        googleOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleClient = GoogleSignIn.getClient(requireContext(), googleOptions);
        firebaseAuth = FirebaseAuth.getInstance();

        GoogleSignInAccount googleAccount = GoogleSignIn.getLastSignedInAccount(requireContext());

        if (googleAccount != null) {
            String googleName = googleAccount.getDisplayName();
            username.setText(googleName);
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
}

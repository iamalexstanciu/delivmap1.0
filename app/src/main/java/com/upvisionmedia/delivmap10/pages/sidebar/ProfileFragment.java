package com.upvisionmedia.delivmap10.pages.sidebar;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.upvisionmedia.delivmap10.R;
import com.upvisionmedia.delivmap10.service.user.SignInActivity;

public class ProfileFragment extends Fragment {

    private TextView username;
    private Button logout;
    GoogleSignInClient googleClient;
    GoogleSignInOptions googleOptions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Now you can access views by their ID using 'view'
        username = view.findViewById(R.id.username);
        logout = view.findViewById(R.id.logoutButton);
        googleOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleClient = GoogleSignIn.getClient(this, googleOptions);

        GoogleSignInAccount googleAccount = GoogleSignIn.getLastSignedInAccount(this);

        if(googleAccount != null) {
        String googleName = googleAccount.getDisplayName();
        username.setText(googleName);
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        finish();
                        startActivity(new Intent(ProfileFragment.this, SignInActivity.class));
                    }
                });
            }
        });

        // Now you can use 'username' and 'logout' as references to the views.

        return view;
    }
}

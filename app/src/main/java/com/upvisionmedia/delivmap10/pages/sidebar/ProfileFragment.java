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

public class ProfileFragment extends Fragment  {

    GoogleSignInClient googleClient;
    GoogleSignInOptions googleOptions;

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

        GoogleSignInAccount googleAccount = GoogleSignIn.getLastSignedInAccount(requireContext());

        if(googleAccount != null) {
        String googleName = googleAccount.getDisplayName();
        username.setText(googleName);
        }
        logout.setOnClickListener(view1 -> googleClient.signOut().addOnCompleteListener(task -> {
            if (getActivity() != null) {
                getActivity().finish(); // Finish the hosting activity, if it exists.
                startActivity(new Intent(getActivity(), SignInActivity.class)); // Start the new activity.
            }
        }));

        // Now you can use 'username' and 'logout' as references to the views.

        return view;
    }
}

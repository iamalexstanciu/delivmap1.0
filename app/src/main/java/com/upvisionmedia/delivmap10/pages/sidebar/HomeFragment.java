package com.upvisionmedia.delivmap10.pages.sidebar;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.upvisionmedia.delivmap10.R;
import com.upvisionmedia.delivmap10.service.SignInActivity;
import com.upvisionmedia.delivmap10.pages.DestinationMain;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView welcomeText = view.findViewById(R.id.welcomeText);
        Button signInButton = view.findViewById(R.id.signInButton);
        Button infoButton = view.findViewById(R.id.infoButton);

        welcomeText.setText(R.string.welcome_to_delivmap2);

        signInButton.setOnClickListener(v -> {
            // Start the sign-in activity
            Intent intent = new Intent(getActivity(), SignInActivity.class);
            startActivity(intent);
        });

        infoButton.setOnClickListener(v -> {
            // Start the home page activity
            Intent intent = new Intent(getActivity(), DestinationMain.class);
            startActivity(intent);
        });

        return view;
    }
}

package com.upvisionmedia.delivmap10.pages.sidebar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.upvisionmedia.delivmap10.R;
import com.upvisionmedia.delivmap10.service.user.SignInActivity;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button signInButton = view.findViewById(R.id.signInButton);


        signInButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SignInActivity.class);
            startActivity(intent);
        });

        return view;
    }

}

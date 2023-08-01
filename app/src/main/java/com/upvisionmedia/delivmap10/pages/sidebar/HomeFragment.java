package com.upvisionmedia.delivmap10.pages.sidebar;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.upvisionmedia.delivmap10.R;
import com.upvisionmedia.delivmap10.service.user.SignInActivity;
import com.upvisionmedia.delivmap10.service.MainMenu;

public class HomeFragment extends Fragment {

        private DrawerLayout drawerLayout;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_home, container, false);

            // Your existing code
            Button signInButton = view.findViewById(R.id.signInButton);
            Button infoButton = view.findViewById(R.id.infoButton);


            signInButton.setOnClickListener(v -> {
                // Start the sign-in activity
                Intent intent = new Intent(getActivity(), SignInActivity.class);
                startActivity(intent);
            });

            infoButton.setOnClickListener(v -> {
                // Start the home page activity
                Intent intent = new Intent(getActivity(), MainMenu.class);
                startActivity(intent);
            });

            ImageView menu = view.findViewById(R.id.menu_icon);
            menu.setOnClickListener(v -> openSidebar());

            return view;
        }

        public void openSidebar() {
            if (drawerLayout != null) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        }

        // Add this method to set the DrawerLayout from MainActivity
        public void setDrawerLayout(DrawerLayout drawerLayout) {
            this.drawerLayout = drawerLayout;
        }
    }

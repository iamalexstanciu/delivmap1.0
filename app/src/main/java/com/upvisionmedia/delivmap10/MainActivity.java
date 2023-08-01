package com.upvisionmedia.delivmap10;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.upvisionmedia.delivmap10.pages.sidebar.AboutFragment;
import com.upvisionmedia.delivmap10.pages.sidebar.HomeFragment;
import com.upvisionmedia.delivmap10.service.MainMenu;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sidebar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            HomeFragment homeFragment = new HomeFragment();
            homeFragment.setDrawerLayout(drawerLayout);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
       int itemId = item.getItemId();

      if(itemId == R.id.nav_home){
          Intent intent = new Intent(this, MainMenu.class);
          startActivity(intent);
      } else if (itemId == R.id.nav_about){
          replaceFragment(new AboutFragment());
      }

      drawerLayout.closeDrawers();
      return true;
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        super.onBackPressed();
    }
}

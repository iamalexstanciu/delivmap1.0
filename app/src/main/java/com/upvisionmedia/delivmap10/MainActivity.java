package com.upvisionmedia.delivmap10;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.upvisionmedia.delivmap10.pages.sidebar.HomeFragment;


public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        }

    }
}

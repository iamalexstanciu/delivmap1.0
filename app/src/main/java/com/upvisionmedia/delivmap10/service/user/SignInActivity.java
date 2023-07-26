package com.upvisionmedia.delivmap10.service.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.upvisionmedia.delivmap10.MainActivity;
import com.upvisionmedia.delivmap10.R;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText signInEmail, signInPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

    }
}

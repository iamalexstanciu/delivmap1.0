package com.upvisionmedia.delivmap10.service.user;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.upvisionmedia.delivmap10.MainActivity;
import com.upvisionmedia.delivmap10.R;
import com.upvisionmedia.delivmap10.pages.sidebar.HomeFragment;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText signInEmail, signInPassword;
    private TextView signUpRedirect;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        auth = FirebaseAuth.getInstance();
        signInEmail = findViewById(R.id.sign_in_email);
        signInPassword = findViewById(R.id.sign_in_password);
        loginButton = findViewById(R.id.sign_in_button);
        signUpRedirect = findViewById(R.id.registerRedirect);

        loginButton.setOnClickListener(view -> {
            String email = signInEmail.getText().toString();
            String password = signInPassword.getText().toString();

            if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                if (!password.isEmpty()) {
                    auth.signInWithEmailAndPassword(email, password)
                            .addOnSuccessListener(authResult -> {
                                Toast.makeText(SignInActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignInActivity.this, HomeFragment.class));
                                finish();
                            }).addOnFailureListener(e -> Toast.makeText(SignInActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show());
                } else {
                    signInPassword.setError("Password cannot be empty!");
                }
            } else if (email.isEmpty()) {
                signInEmail.setError("Email cannot be empty!");
            }
        });
        signUpRedirect.setOnClickListener(view -> startActivity(new Intent(SignInActivity.this, SignUpActivity.class)));
    }
}

package com.upvisionmedia.delivmap10.service.user;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.upvisionmedia.delivmap10.R;

public class SignUpActivity extends Activity {

    private FirebaseAuth auth;
    private EditText signUpEmail, signUpPassword;
    private Button signUp;
    private TextView loginRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();
        signUpEmail = findViewById(R.id.sign_up_email);
        signUpPassword = findViewById(R.id.sign_up_password);
        signUp = findViewById(R.id.signup_button);

    signUp.setOnClickListener(v -> {
        String user = signUpEmail.getText().toString().trim();
        String password = signUpPassword.getText().toString().trim();

        if(user.isEmpty()){
            signUpEmail.setError("Email cannot be empty!");
        }
        if(password.isEmpty()){
            signUpPassword.setError("Password cannot be empty!");
        }

    });

    }
}

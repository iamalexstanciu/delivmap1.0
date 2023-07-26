package com.upvisionmedia.delivmap10.service.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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
        else {
            auth.createUserWithEmailAndPassword(user, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(SignUpActivity.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                    }
                    else {
                        Toast.makeText(SignUpActivity.this, "SignUp Failed" + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    });

    loginRedirect.setOnClickListener(view -> startActivity(new Intent(SignUpActivity.this, SignInActivity.class)));

    }
}

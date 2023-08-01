package com.upvisionmedia.delivmap10.service.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.developer.gbuttons.GoogleSignInButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.upvisionmedia.delivmap10.R;
import com.upvisionmedia.delivmap10.service.MainMenu;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText signInEmail, signInPassword;

    GoogleSignInButton googleButton;
    GoogleSignInOptions googleOptions;
    GoogleSignInClient googleClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        auth = FirebaseAuth.getInstance();
        signInEmail = findViewById(R.id.sign_in_email);
        signInPassword = findViewById(R.id.sign_in_password);
        Button loginButton = findViewById(R.id.sign_in_button);
        TextView signUpRedirect = findViewById(R.id.registerRedirect);
        googleButton = findViewById(R.id.google_signin);

        loginButton.setOnClickListener(view -> {
            String email = signInEmail.getText().toString();
            String password = signInPassword.getText().toString();

            if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                if (!password.isEmpty()) {
                    auth.signInWithEmailAndPassword(email, password)
                            .addOnSuccessListener(authResult -> {
                                Toast.makeText(SignInActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignInActivity.this, MainMenu.class));
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

        googleOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleClient = GoogleSignIn.getClient(this, googleOptions);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if(account != null){
            finish();
            Intent intent = new Intent(SignInActivity.this, MainMenu.class);
            startActivity(intent);
        }
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK){
                Intent data = result.getData();
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                try {
                    task.getResult(ApiException.class);
                    finish();
                } catch (ApiException e) {
                    Toast.makeText(SignInActivity.this, "Somenthing went wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        googleButton.setOnClickListener(view -> {
            Intent signInIntent = googleClient.getSignInIntent();
            activityResultLauncher.launch(signInIntent);
        });
    }
}

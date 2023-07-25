package com.upvisionmedia.delivmap10.service;



import static com.upvisionmedia.delivmap10.service.DatabaseHelper.COLUMN_EMAIL;
import static com.upvisionmedia.delivmap10.service.DatabaseHelper.COLUMN_PASSWORD;
import static com.upvisionmedia.delivmap10.service.DatabaseHelper.TABLE_USERS;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.upvisionmedia.delivmap10.pages.DestinationMain;
import com.upvisionmedia.delivmap10.R;


public class LoginActivity extends Activity {


    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Perform login logic here
                if (isValidCredentials(email, password)) {
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginActivity.this, DestinationMain.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidCredentials(String email, String password) {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String selection = COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(
                TABLE_USERS,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isValid;
    }

}

package com.example.truckshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.truckshare.Data.UserDatabaseHelper;
import com.example.truckshare.UserModel.User;

public class MainActivity extends AppCompatActivity {

    Button signup;
    Button logon;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signup = findViewById(R.id.signupButton);
        logon = findViewById(R.id.loginButton);
        username = findViewById(R.id.usernameEditText);
        password = findViewById(R.id.passwordEditText);

        // set onClickListener for the signup button
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goSignup = new Intent(MainActivity.this, signUpPage.class);
                startActivity(goSignup);
            }
        });

    // set onClickListener for the login button
        logon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                UserDatabaseHelper db = new UserDatabaseHelper(MainActivity.this);
                if (db.checkCredentials(user, pass)) {
                    Intent goHome = new Intent(MainActivity.this, trucksPage.class);
                    goHome.putExtra("username", user);
                    startActivity(goHome);
                } else {
                    Toast.makeText(MainActivity.this, "INVALID ACCOUNT", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

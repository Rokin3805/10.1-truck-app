package com.example.truckshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.truckshare.Data.UserDatabaseHelper;

public class signUpPage extends AppCompatActivity {
    //other entry fields are not used in wireframe implementation, so only taking what is needed for implementation
    Button create;
    EditText user;
    EditText pass;
    EditText confirmPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);


        create = findViewById(R.id.createAccountButton);
        user = findViewById(R.id.usernameEditText);
        pass = findViewById(R.id.passwordEditText);
        confirmPass = findViewById(R.id.confirmPasswordEditText);
        ImageView imageView = findViewById(R.id.imageView);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getText().toString().trim();
                String password = pass.getText().toString().trim();
                String confirmPassword = confirmPass.getText().toString().trim();
                //emptry credentials not allowed
                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(signUpPage.this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
                }
                //passwords must match
                else if (!password.equals(confirmPassword)) {
                    Toast.makeText(signUpPage.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                }
                //update database with new user if the username isnt taken
                else {
                    UserDatabaseHelper dbHelper = new UserDatabaseHelper(signUpPage.this);
                    boolean userExists = dbHelper.checkUser(username);
                    if (userExists) {
                        Toast.makeText(signUpPage.this, "Username already taken!", Toast.LENGTH_SHORT).show();
                    } else {
                        long result = dbHelper.insertUser(username, password);
                        if (result != -1) {
                            Intent intent = new Intent(signUpPage.this, MainActivity.class);
                            intent.putExtra("username", username);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(signUpPage.this, "Failed to create account!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });


    }
}
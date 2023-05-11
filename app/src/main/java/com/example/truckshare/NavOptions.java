package com.example.truckshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NavOptions extends AppCompatActivity {
    Button homeButton;
    Button ordersButton;
    Button signOutButton;

    Button placeOrder;

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_options);

        //always keep track of username
        username = getIntent().getStringExtra("username");

        homeButton = findViewById(R.id.homeButton);
        ordersButton = findViewById(R.id.ordersButton);
        signOutButton = findViewById(R.id.signOutButton);
        placeOrder = findViewById(R.id.placeOrderButton);

        //all button onclicks navigate to relevant page and bring username (except signout, which also removes nav menu from backStack)
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(NavOptions.this, trucksPage.class);
                homeIntent.putExtra("username", username);
                startActivity(homeIntent);
            }
        });

        ordersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myOrdersIntent = new Intent(NavOptions.this, myOrdersPage.class);
                myOrdersIntent.putExtra("username", username);
                startActivity(myOrdersIntent);
            }
        });

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signOutIntent = new Intent(NavOptions.this, MainActivity.class);
                startActivity(signOutIntent);
                finish();
            }
        });

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavOptions.this, orderDate.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }
}

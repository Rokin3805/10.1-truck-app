package com.example.truckshare;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class trucksPage extends AppCompatActivity {

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trucks_page);

        //get the username passed from the previous activity

        username = getIntent().getStringExtra("username");;

        //navigation menu button (also brings username so no amount of page navigation before ordering loses it)
        Button navButton = findViewById(R.id.navButton);
        navButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(trucksPage.this, NavOptions.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });



        //set onClickListener for each tile, create intent with relevant info
        RelativeLayout heavyTile = findViewById(R.id.subLayout1);
        heavyTile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(trucksPage.this, TruckDetails.class);
                intent.putExtra("username", username);
                intent.putExtra("heading", "Heavy");
                startActivity(intent);
            }
        });

        RelativeLayout mediumTile = findViewById(R.id.subLayout2);
        mediumTile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(trucksPage.this, TruckDetails.class);
                intent.putExtra("username", username);
                intent.putExtra("heading", "Medium");
                startActivity(intent);
            }
        });

        RelativeLayout lightTile = findViewById(R.id.subLayout3);
        lightTile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(trucksPage.this, TruckDetails.class);
                intent.putExtra("username", username);
                intent.putExtra("heading", "Light");
                startActivity(intent);
            }
        });

        RelativeLayout otherTile = findViewById(R.id.subLayout4);
        otherTile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(trucksPage.this, TruckDetails.class);
                intent.putExtra("username", username);
                intent.putExtra("heading", "Other");
                startActivity(intent);
            }
        });
    }
}

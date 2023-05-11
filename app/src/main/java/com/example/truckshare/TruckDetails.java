package com.example.truckshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class TruckDetails extends AppCompatActivity {

    int defaultWeight = 150;
    int defaultHeight = 40;
    int defaultLength = 200;
    int defaultWidth = 30;

    TextView typeTextView;
    TextView weightTextView;
    TextView heightTextView;
    TextView lengthTextView;
    TextView widthTextView;

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truck_details);

        //get the username and heading from intent
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String heading = intent.getStringExtra("heading");


        typeTextView = findViewById(R.id.type_text_view);
        weightTextView = findViewById(R.id.weight_text_view);
        heightTextView = findViewById(R.id.height_text_view);
        lengthTextView = findViewById(R.id.length_text_view);
        widthTextView = findViewById(R.id.width_text_view);
        image= findViewById(R.id.truckImage);

        //set the typeTextView to the heading value (brought over in intent)
        typeTextView.setText("Type: " + heading);

        //create and set the weight, height, length, and width depending on truck type (arbitrary but different) and matching picture
        int weight;
        int height;
        int length;
        int width;
        if (heading.equals("Heavy"))
        {
            weight = defaultWeight * 4;
            height = defaultHeight * 4;
            length = defaultLength * 4;
            width = defaultWidth * 4;
            image.setImageResource(R.drawable.heavy_image_foreground);
        }
        else if (heading.equals("Medium"))
        {
            weight = defaultWeight * 3;
            height = defaultHeight * 3;
            length = defaultLength * 3;
            width = defaultWidth * 3;
            image.setImageResource(R.drawable.medium_image_foreground);
        }
        else if (heading.equals("Light"))
        {
            weight = defaultWeight * 2;
            height = defaultHeight * 2;
            length = defaultLength * 2;
            width = defaultWidth * 2;
            image.setImageResource(R.drawable.light_image_foreground);
        } else {
            weight = defaultWeight;
            height = defaultHeight;
            length = defaultLength;
            width = defaultWidth;
            image.setImageResource(R.drawable.other_image_foreground);
        }
        weightTextView.setText("Max Weight: " + weight);
        heightTextView.setText("Max Height: " + height);
        lengthTextView.setText("Max Length: " + length);
        widthTextView.setText("Max Width: " + width);
    }
}

package com.example.truckshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class orderDate extends AppCompatActivity {

    EditText receiverNameInputText;
    EditText pickupTimeInputText;
    EditText pickupLocationInputText;
    DatePicker pickupDateCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_date);

        //all widgets which generate values needed to varry over
        receiverNameInputText = findViewById(R.id.receiverNameInputText);
        pickupTimeInputText = findViewById(R.id.pickupTimeInputText);
        pickupLocationInputText = findViewById(R.id.pickupLocationInputText);
        pickupDateCalendar = findViewById(R.id.pickupDateCalendar);

        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String receiverName = receiverNameInputText.getText().toString().trim();
                String pickupTime = pickupTimeInputText.getText().toString().trim();
                String pickupLocation = pickupLocationInputText.getText().toString().trim();
                String pickupDate = pickupDateCalendar.getDayOfMonth() + "/" + (pickupDateCalendar.getMonth()+1) + "/" + pickupDateCalendar.getYear();

                //make sure all fields given values
                if(!receiverName.isEmpty() && !pickupTime.isEmpty() && !pickupLocation.isEmpty())
                {
                    Intent intent = new Intent(orderDate.this, orderFinalize.class);
                    //get the username and add it along with all strings created from input fields to intent
                    String username = getIntent().getStringExtra("username");
                    intent.putExtra("username", username);
                    intent.putExtra("receiverName", receiverName);
                    intent.putExtra("pickupTime", pickupTime);
                    intent.putExtra("pickupLocation", pickupLocation);
                    intent.putExtra("pickupDate", pickupDate);
                    startActivity(intent);

                }
                //notify user input fields are mandatory
                else
                {
                    Toast.makeText(orderDate.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

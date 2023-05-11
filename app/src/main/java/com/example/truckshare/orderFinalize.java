package com.example.truckshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truckshare.Data.OrderDatabaseHelper;
import com.example.truckshare.OrderModel.Order;

public class orderFinalize extends AppCompatActivity {

    TextView fromText;
    TextView pickupTimeText;
    TextView deliveryTimeText;
    TextView recipientText;
    EditText weightInput;
    EditText widthInput;
    EditText lengthInput;
    EditText typeInput;
    EditText heightInput;
    EditText quantityInput;
    String from; String pickupTime;
    String deliveryTime;
    String recipient;
    String weight;
    String width;
    String length;
    String type;
    String height;
    String quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_finalize);

        //get all the strings brought over
        Intent intent = getIntent();
        from = intent.getStringExtra("username");
        pickupTime = intent.getStringExtra("pickupTime");
        deliveryTime = intent.getStringExtra("deliveryTime");
        recipient = intent.getStringExtra("recipient");

        //put previous values in right placeholders
        fromText = findViewById(R.id.from_text);
        fromText.setText(from);

        pickupTimeText = findViewById(R.id.pickup_time_text);
        pickupTimeText.setText(pickupTime);

        deliveryTimeText = findViewById(R.id.delivery_time_text);
        deliveryTimeText.setText(deliveryTime);

        recipientText = findViewById(R.id.recicpient);
        recipientText.setText(recipient);

        //all relevant editText fields assigned to their Id
        weightInput = findViewById(R.id.weight_input);
        widthInput = findViewById(R.id.width_input);
        lengthInput = findViewById(R.id.length_input);
        typeInput = findViewById(R.id.type_input);
        heightInput = findViewById(R.id.height_input);
        quantityInput = findViewById(R.id.quantity_input);

        //onClick method for the createOrder button
        Button createOrderButton = findViewById(R.id.createOrder);
        createOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cast all inputs from this page into strings
                weight = weightInput.getText().toString();
                width = widthInput.getText().toString();
                length = lengthInput.getText().toString();
                type = typeInput.getText().toString();
                height = heightInput.getText().toString();
                quantity = quantityInput.getText().toString();

                //create dbHelper object from class
                OrderDatabaseHelper dbHelper = new OrderDatabaseHelper(orderFinalize.this);
                //create order object
                //create a new Order object
                Order order = new Order(from, from, recipient, type, from, pickupTime, deliveryTime, type, weight, width, length, height, quantity);


                long result = dbHelper.insertOrder(order);

                // show a toast message to the user indicating whether the order was successful or not
                String message;
                if (result > -1) {
                    message = "Order placed successfully";
                } else {
                    message = "Failed to place order";
                }
                Toast.makeText(orderFinalize.this, message, Toast.LENGTH_SHORT).show();

                // go to myOrdersPage.class, bringing only the username via putExtra
                Intent intent = new Intent(orderFinalize.this, myOrdersPage.class);
                intent.putExtra("username", from);
                startActivity(intent);
            }
        });

    }
}

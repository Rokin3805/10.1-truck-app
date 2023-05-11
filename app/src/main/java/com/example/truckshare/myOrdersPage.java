package com.example.truckshare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.truckshare.Data.OrderDatabaseHelper;
import com.example.truckshare.OrderModel.Order;

import java.util.ArrayList;

public class myOrdersPage extends AppCompatActivity implements myOrdersAdapter.OnRowClickListener {

    String username;
    RecyclerView recyclerView;
    myOrdersAdapter adapter;
    ArrayList<Order> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders_page);

        username = getIntent().getStringExtra("username");

        //navigation menu button (also brings username so no amount of page navigation before ordering loses it)
        Button navButton = findViewById(R.id.navigationButton);
        navButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myOrdersPage.this, NavOptions.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        // retrieve orders from database
        OrderDatabaseHelper dbHelper = new OrderDatabaseHelper(this);
        orderList = dbHelper.retrieveOrders(username);

        // set up recycler view
        recyclerView = findViewById(R.id.OrdersRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new myOrdersAdapter(this, orderList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "Calling driver", Toast.LENGTH_SHORT).show();
    }
}

package com.example.truckshare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truckshare.OrderModel.Order;

import java.util.ArrayList;

public class myOrdersAdapter extends RecyclerView.Adapter<myOrdersAdapter.OrderViewHolder> {

    private ArrayList<Order> ordersList;
    private OnRowClickListener listener;

    public myOrdersAdapter(Context context, ArrayList<Order> ordersList, OnRowClickListener listener) {
        this.ordersList = ordersList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_details, parent, false);
        return new OrderViewHolder(view, listener);
    }


    @Override
    public void onBindViewHolder(@NonNull myOrdersAdapter.OrderViewHolder holder, int position) {
        holder.bind(ordersList.get(position));
    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView fromDetail;
        private TextView pickupDetail;
        private TextView recipientDetail;
        private TextView weightDetail;
        private TextView widthDetail;
        private TextView heightDetail;
        private TextView lengthDetail;
        private TextView truckTypeDetail;

        private OnRowClickListener listener;

        public OrderViewHolder(@NonNull View itemView, OnRowClickListener listener) {
            super(itemView);

            fromDetail = itemView.findViewById(R.id.fromDetail);
            pickupDetail = itemView.findViewById(R.id.pickupDetail);
            recipientDetail = itemView.findViewById(R.id.recipientDetail);
            weightDetail = itemView.findViewById(R.id.weightDetail);
            widthDetail = itemView.findViewById(R.id.widthDetail);
            heightDetail = itemView.findViewById(R.id.heightDetail);
            lengthDetail = itemView.findViewById(R.id.lengthDetail);
            truckTypeDetail = itemView.findViewById(R.id.truckDetail);
            this.listener = listener;

            //set the click listener on the itemView
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition());
        }

        public void bind(Order order)
        {
            fromDetail.setText(order.getSender());
            pickupDetail.setText(order.getPickUpTime());
            recipientDetail.setText(order.getLocation());
            weightDetail.setText(order.getWeight());
            heightDetail.setText(order.getHeight());
            lengthDetail.setText(order.getLength());
            truckTypeDetail.setText(order.getTruckType());
        }
    }

    public interface OnRowClickListener{
        void onItemClick(int position);
    }
}

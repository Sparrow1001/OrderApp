package com.example.orderapp.Presentation.View.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderapp.Presentation.View.MainActivity;
import com.example.orderapp.Presentation.View.OrderDetailActivity;
import com.example.orderapp.Repository.Model.OrderDTO;
import com.example.orderapp.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderListHolder> {
    private List<OrderDTO> orders = new ArrayList<>();

    @NonNull
    @Override
    public OrderListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_orders, parent, false);
        return new OrderListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListHolder holder, int position) {
        OrderDTO currentOrder = orders.get(position);
        holder.placeTv.setText(currentOrder.getPlace());
        holder.nameTv.setText(currentOrder.getCustomer());
        holder.numOfVisitorsTv.setText(String.valueOf(currentOrder.getNumOfVisitors()) + " visitors");
        holder.dateTv.setText(currentOrder.getArrivalTime() + " within " + currentOrder.getTimeOfStay() + " hours");
        holder.foodTv.setText("Your order: "+ currentOrder.getChooseFood());

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setOrders(List<OrderDTO> orders){
        this.orders = orders;
        notifyDataSetChanged();
    }

    public void rewrite(){
        notifyDataSetChanged();
    }

    public List<OrderDTO> getData() {
        return orders;
    }

    class OrderListHolder extends RecyclerView.ViewHolder{
        private TextView placeTv;
        private TextView nameTv;
        private TextView numOfVisitorsTv;
        private TextView dateTv;
        private TextView foodTv;
        private MaterialCardView orderCv;

        public OrderListHolder(@NonNull View itemView) {
            super(itemView);
            placeTv = itemView.findViewById(R.id.placeTv);
            nameTv = itemView.findViewById(R.id.nameTv);
            numOfVisitorsTv = itemView.findViewById(R.id.numOfVisitorsTv);
            dateTv = itemView.findViewById(R.id.dateTv);
            foodTv = itemView.findViewById(R.id.foodTv);
            orderCv = itemView.findViewById(R.id.orderCv);

        }
    }

}

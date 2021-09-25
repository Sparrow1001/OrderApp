package com.example.orderapp.Presentation.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orderapp.Presentation.ViewModel.OrderDetailViewModel;
import com.example.orderapp.R;
import com.example.orderapp.Repository.Model.OrderDTO;

import org.w3c.dom.Text;

public class OrderDetailActivity extends AppCompatActivity {

    private LiveData<OrderDTO> order;
    private OrderDetailViewModel orderDetailViewModel;
    private TextView restNameTv, customerTv, visitorsTv, dateTv, clickToCallTv;
    private Button shareBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        restNameTv = findViewById(R.id.restNameTv);
        customerTv = findViewById(R.id.customerTv);
        visitorsTv = findViewById(R.id.visitorsTv);
        dateTv = findViewById(R.id.dateTv);
        clickToCallTv = findViewById(R.id.clickToCallTv);
        shareBtn = findViewById(R.id.shareBtn);

        orderDetailViewModel = new OrderDetailViewModel(getApplication());

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        orderDetailViewModel.getOrderById(id).observe(this, new Observer<OrderDTO>() {
            @Override
            public void onChanged(OrderDTO orderDTO) {
                restNameTv.setText(orderDTO.getPlace());
                customerTv.setText(orderDTO.getCustomer());
                visitorsTv.setText(String.valueOf(orderDTO.getNumOfVisitors()));
                dateTv.setText(orderDTO.getArrivalTime());
                Toast.makeText(OrderDetailActivity.this, ""+orderDTO.getPlace(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
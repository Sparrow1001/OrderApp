package com.example.orderapp.Presentation.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.orderapp.Presentation.Repository.Model.OrderDTO;
import com.example.orderapp.Presentation.View.Adapters.OrderListAdapter;
import com.example.orderapp.Presentation.ViewModel.OrderViewModel;
import com.example.orderapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private OrderViewModel orderViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttAddOrder = findViewById(R.id.fab);
        buttAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddOrderActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView_orders);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        OrderListAdapter adapter = new OrderListAdapter();
        recyclerView.setAdapter(adapter);

        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        orderViewModel.getAllOrders().observe(this, new Observer<List<OrderDTO>>() {
            @Override
            public void onChanged(List<OrderDTO> orders) {
                adapter.setOrders(orders);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                orderViewModel.delete(((OrderListAdapter) recyclerView.getAdapter()).getData().get(position));
            }
        }).attachToRecyclerView(recyclerView);

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1 && resultCode == RESULT_OK){
//            String place = data.getStringExtra(AddOrderActivity.EXTRA_PLACE);
//            String numOfVisitors = data.getStringExtra(AddOrderActivity.EXTRA_NUMOFVISITORS);
//            String arrivalTime = data.getStringExtra(AddOrderActivity.EXTRA_ARRIVALTIME);
//
//            OrderDTO orderDTO = new OrderDTO("Evgeniy", place, arrivalTime, Integer.parseInt(numOfVisitors));
//            orderViewModel.insert(orderDTO);
//        }
//    }
}
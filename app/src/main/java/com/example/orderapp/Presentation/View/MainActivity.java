package com.example.orderapp.Presentation.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.orderapp.BuildConfig;
import com.example.orderapp.Repository.Model.OrderDTO;
import com.example.orderapp.Presentation.View.Adapters.OrderListAdapter;
import com.example.orderapp.Presentation.ViewModel.OrderViewModel;
import com.example.orderapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yandex.mapkit.MapKitFactory;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private OrderViewModel orderViewModel;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MapKitFactory.setApiKey(BuildConfig.YANDEX_API_KEY);
        MapKitFactory.initialize(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttAddOrder = findViewById(R.id.fab);
        buttAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddOrderActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });

        name = getIntent().getStringExtra("name");

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

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Intent intent = new Intent(MainActivity.this, OrderDetailActivity.class);
                intent.putExtra("id", (((OrderListAdapter) recyclerView.getAdapter()).getData().get(position)).getId());
                intent.putExtra("name", name);
                startActivity(intent);
                adapter.rewrite();
            }
        }).attachToRecyclerView(recyclerView);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Uri income = getIntent().getData();
        if (income != null) {
            String[] parts = income.toString().split("/");
            String id = parts[parts.length - 1];
            Intent intent = new Intent(MainActivity.this, OrderDetailActivity.class);
            intent.putExtra("id", Integer.parseInt(id));
            getIntent().setData(null);
            startActivity(intent);
        }
    }
}
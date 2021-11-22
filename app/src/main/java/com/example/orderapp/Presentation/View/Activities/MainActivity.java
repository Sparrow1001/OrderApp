package com.example.orderapp.Presentation.View.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.orderapp.BuildConfig;
import com.example.orderapp.Repository.Model.OrderDTO;
import com.example.orderapp.Presentation.View.Adapters.OrderListAdapter;
import com.example.orderapp.Presentation.ViewModel.OrderViewModel;
import com.example.orderapp.R;
import com.example.orderapp.Repository.Model.PersonDTO;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yandex.mapkit.MapKitFactory;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private OrderViewModel orderViewModel;
    private String name;
    private String email;
    private ImageButton exitBt, profileBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            MapKitFactory.setApiKey(BuildConfig.YANDEX_API_KEY);
            MapKitFactory.initialize(this);
        }catch (AssertionError error){}

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");

        FloatingActionButton buttAddOrder = findViewById(R.id.fab);


        exitBt = findViewById(R.id.exitBt);
        profileBt = findViewById(R.id.profileBt);

        RecyclerView recyclerView = findViewById(R.id.recyclerView_orders);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        OrderListAdapter adapter = new OrderListAdapter(name);
        recyclerView.setAdapter(adapter);

        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);

        orderViewModel.getPersonByEmail(email).observe(this, new Observer<PersonDTO>() {
            @Override
            public void onChanged(PersonDTO personDTO) {
                if (personDTO.getRole().equals("admin")){
                    orderViewModel.getAllOrders().observe(MainActivity.this, new Observer<List<OrderDTO>>() {
                        @Override
                        public void onChanged(List<OrderDTO> orders) {
                            adapter.setOrders(orders);
                        }
                    });
                }else {
                    orderViewModel.getOrdersByEmail(email).observe(MainActivity.this, new Observer<List<OrderDTO>>() {
                        @Override
                        public void onChanged(List<OrderDTO> orders) {
                            adapter.setOrders(orders);
                        }
                    });
                }
                MainActivity.this.name = personDTO.getFirstName() + " " + personDTO.getLastName();
            }
        });

        buttAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddOrderActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        profileBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                orderViewModel.getPersonByEmail(email).observe(MainActivity.this, new Observer<PersonDTO>() {
                    @Override
                    public void onChanged(PersonDTO personDTO) {

                        if (personDTO.getRole().equals("admin") || personDTO.getRole().equals("moder")){
                            Intent intent = new Intent(MainActivity.this, AdminTableActivity.class);
                            startActivity(intent);
                        }else{
                            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                            startActivity(intent);
                        }

                    }
                });


            }
        });


        exitBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExitDialog();
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

    private void signOut() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                });
    }

    private void showExitDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Вы уверены что хотите выйти из аккаунта?");

        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                signOut();
            }
        });

        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }
}
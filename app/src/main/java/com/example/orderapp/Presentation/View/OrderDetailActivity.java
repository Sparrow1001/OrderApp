package com.example.orderapp.Presentation.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orderapp.Presentation.ViewModel.OrderDetailViewModel;
import com.example.orderapp.R;
import com.example.orderapp.Repository.Model.OrderDTO;
import com.example.orderapp.Repository.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class OrderDetailActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_ACCESS = 100;
    private LiveData<OrderDTO> order;
    private OrderDetailViewModel orderDetailViewModel;
    private TextView restNameTv, customerTv, visitorsTv, dateTv, clickToCallTv;
    private ImageButton shareBtn, calendarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        restNameTv = findViewById(R.id.restNameTv);
        customerTv = findViewById(R.id.customerTv);
        visitorsTv = findViewById(R.id.visitorsTv);
        dateTv = findViewById(R.id.dateTv);
        shareBtn = findViewById(R.id.shareBtn);
        calendarBtn = findViewById(R.id.calendarBtn);

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

                calendarBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(OrderDetailActivity.this,new String[]{Manifest.permission.WRITE_CALENDAR}, MY_PERMISSIONS_REQUEST_ACCESS);
                        }
                        else if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_CALENDAR) == PackageManager.PERMISSION_GRANTED){

                        ContentResolver cr = getContentResolver();
                        ContentValues cv = new ContentValues();

                        Calendar startTime = Calendar.getInstance();
                        SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ENGLISH);
                            try {
                                startTime.setTime(formater.parse(orderDTO.getArrivalTime()));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                        cv.put(CalendarContract.Events.TITLE, orderDTO.getPlace());
                        cv.put(CalendarContract.Events.DESCRIPTION, orderDTO.getCustomer() + "\n"+ orderDTO.getNumOfVisitors() + " visitors");
                        cv.put(CalendarContract.Events.DTSTART, startTime.getTimeInMillis());
                        cv.put(CalendarContract.Events.DTEND, startTime.getTimeInMillis()+1000*120*60);
                        cv.put(CalendarContract.Events.CALENDAR_ID, 2);
                        cv.put(CalendarContract.Events.EVENT_TIMEZONE, Calendar.getInstance().getTimeZone().getID());
                        cr.insert(CalendarContract.Events.CONTENT_URI, cv);
                        Toast.makeText(OrderDetailActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();}
                    }
                });

                shareBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, "https://or.order_app/" + orderDTO.getId());
                        sendIntent.setType("text/plain");

                        Intent shareIntent = Intent.createChooser(sendIntent, null);
                        startActivity(shareIntent);

                    }
                });

            }
        });




    }
}
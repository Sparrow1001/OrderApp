package com.example.orderapp.Presentation.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.orderapp.Presentation.ViewModel.OrderViewModel;
import com.example.orderapp.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class AddOrderActivity extends AppCompatActivity {

    private EditText placeEt, arrivalTimeEt, numOfVisitorsEt, timeOfStayEt, chooseFoodEt;
    private Button button_save;
    private LocalDateTime time;

    private OrderViewModel orderViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

        placeEt = findViewById(R.id.placeEt);
        numOfVisitorsEt = findViewById(R.id.numOfVisitorsEt);
        arrivalTimeEt = findViewById(R.id.arrivalTimeEt);
        button_save = findViewById(R.id.button_save);
        timeOfStayEt = findViewById(R.id.timeOfStayEt);
        chooseFoodEt = findViewById(R.id.chooseFoodEt);

        arrivalTimeEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();

                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar.set(Calendar.MINUTE, minute);

                                time = LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId());
                                arrivalTimeEt.setText(time.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
                            }
                        };

                        new TimePickerDialog(AddOrderActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
                    }
                };

                new DatePickerDialog(AddOrderActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!placeEt.getText().toString().isEmpty()) {
                    String place = placeEt.getText().toString();
                    String numOfVisitors = numOfVisitorsEt.getText().toString();
                    String arrivalTime = arrivalTimeEt.getText().toString();
                    String timeOfStay = timeOfStayEt.getText().toString();
                    String choosedFood = chooseFoodEt.getText().toString();

                    orderViewModel = new OrderViewModel(getApplication());
                    orderViewModel.addOrder(place, Integer.parseInt(numOfVisitors), arrivalTime, Integer.parseInt(timeOfStay), choosedFood);
                    finish();
                }else {
                    Toast.makeText(AddOrderActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

}
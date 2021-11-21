package com.example.orderapp.Presentation.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.orderapp.Presentation.ViewModel.OrderViewModel;
import com.example.orderapp.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class AddOrderActivity extends AppCompatActivity {

    private EditText arrivalTimeEt, numOfVisitorsEt, timeOfStayEt, chooseFoodEt;
    private Button button_save;
    private LocalDateTime time;
    private Spinner placeSp;
    private String name;
    private String email;

    private OrderViewModel orderViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

        placeSp = findViewById(R.id.placeSp);
        numOfVisitorsEt = findViewById(R.id.numOfVisitorsEt);
        arrivalTimeEt = findViewById(R.id.arrivalTimeEt);
        button_save = findViewById(R.id.button_save);
        timeOfStayEt = findViewById(R.id.timeOfStayEt);
        chooseFoodEt = findViewById(R.id.chooseFoodEt);

        chooseFoodEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showFoodChooseDialog();
            }
        });

        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");

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
                if(!placeSp.getSelectedItem().toString().isEmpty() &&
                        !numOfVisitorsEt.getText().toString().isEmpty() &&
                        !arrivalTimeEt.getText().toString().isEmpty() &&
                        !timeOfStayEt.getText().toString().isEmpty() &&
                        !chooseFoodEt.getText().toString().isEmpty())
                {
                    String place = placeSp.getSelectedItem().toString();
                    String numOfVisitors = numOfVisitorsEt.getText().toString();
                    String arrivalTime = arrivalTimeEt.getText().toString();
                    String timeOfStay = timeOfStayEt.getText().toString();
                    String choosedFood = chooseFoodEt.getText().toString();
                    String address = "";

                    if (placeSp.getSelectedItem().toString().equals("Ресторан Одинцово")){
                        address = "Одинцово, улица Маршала Неделина, 9 ";
                    } else if (placeSp.getSelectedItem().toString().equals("Ресторан Москва Юг")){
                        address = "Москва, 1-й Дорожный проезд, 5 ";
                    }else if (placeSp.getSelectedItem().toString().equals("Ресторан Москва Запад")){
                        address = "Москва, Кунцевская улица, 15 ";
                    }else if (placeSp.getSelectedItem().toString().equals("Ресторан Москва Север")){
                        address = "Москва, Дубнинская улица, 16 ";
                    }else if (placeSp.getSelectedItem().toString().equals("Ресторан Лыткарино")){
                        address = "Лыткарино, Коммунистическая улица, 18 ";
                    }

                    orderViewModel = new OrderViewModel(getApplication());
                    orderViewModel.addOrder(name,
                            email,
                            place,
                            Integer.parseInt(numOfVisitors),
                            arrivalTime,
                            Integer.parseInt(timeOfStay),
                            choosedFood, address);
                    finish();
                }else {
                    Toast.makeText(AddOrderActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

//    private void showFoodChooseDialog(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Вы хотите сделать заказ заранее");
//
//        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//            }
//        });
//
//        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//            }
//        });
//
//        builder.create().show();
//    }
}
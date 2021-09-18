package com.example.orderapp.Presentation.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.orderapp.R;

public class AddOrderActivity extends AppCompatActivity {
    public static final String EXTRA_PLACE =
            "com.example.orderapp.Presentation.View.EXTRA_PLACE";
    public static final String EXTRA_NUMOFVISITORS =
            "com.example.orderapp.Presentation.View.EXTRA_NUMOFVISITORS";
    public static final String EXTRA_ARRIVALTIME =
            "com.example.orderapp.Presentation.View.EXTRA_ARRIVALTIME";

    private EditText placeEt;
    private EditText numOfVisitorsEt;
    private EditText arrivalTimeEt;
    private Button button_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

        placeEt = findViewById(R.id.placeEt);
        numOfVisitorsEt = findViewById(R.id.numOfVisitorsEt);
        arrivalTimeEt = findViewById(R.id.arrivalTimeEt);
        button_save = findViewById(R.id.button_save);

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String place = placeEt.getText().toString();
                String numOfVisitors = numOfVisitorsEt.getText().toString();
                String arrivalTime = arrivalTimeEt.getText().toString();

                Intent data = new Intent();
                data.putExtra(EXTRA_PLACE, place);
                data.putExtra(EXTRA_NUMOFVISITORS, numOfVisitors);
                data.putExtra(EXTRA_ARRIVALTIME, arrivalTime);

                setResult(RESULT_OK, data);
                finish();
            }
        });


    }
}
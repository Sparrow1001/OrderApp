package com.example.orderapp.Presentation.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


import com.example.orderapp.R;

public class LoginActivity extends AppCompatActivity {
    private EditText loginEt, passwordEt;
    private Button loginBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEt = findViewById(R.id.loginEt);
        passwordEt = findViewById(R.id.passwordEt);
        loginBt = findViewById(R.id.loginBt);


        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = loginEt.getText().toString().trim();
                String password = passwordEt.getText().toString().trim();
            }
        });



    }
}
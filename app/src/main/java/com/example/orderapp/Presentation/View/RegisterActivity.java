package com.example.orderapp.Presentation.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.orderapp.Presentation.ViewModel.LoginViewModel;
import com.example.orderapp.R;
import com.example.orderapp.Repository.Model.PersonDTO;

public class RegisterActivity extends AppCompatActivity {

    private EditText fNameEt, lNameEt, phoneEt, passwordEt, confirmPasswordEt, emailEt;
    private Button registerBt;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fNameEt = findViewById(R.id.fNameEt);
        lNameEt = findViewById(R.id.lNameEt);
        phoneEt = findViewById(R.id.phoneEt);
        passwordEt = findViewById(R.id.passwordEt);
        confirmPasswordEt = findViewById(R.id.confirmPasswordEt);
        emailEt = findViewById(R.id.emailEt);
        registerBt = findViewById(R.id.registerBt);

        registerBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fName = fNameEt.getText().toString().trim();
                String lName = lNameEt.getText().toString().trim();
                String phone = phoneEt.getText().toString().trim();
                String email = emailEt.getText().toString().trim();
                String password = passwordEt.getText().toString().trim();
                String confirmPassword = confirmPasswordEt.getText().toString().trim();

                if (password.equals(confirmPassword)){
                    PersonDTO person = new PersonDTO();

                    person.setFirstName(fName);
                    person.setLastName(lName);
                    person.setEmail(email);
                    person.setPhone(phone);
                    person.setPassword(password);

                    loginViewModel = new LoginViewModel(getApplication());
                    loginViewModel.insertPerson(person);

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                }else {
                    Toast.makeText(RegisterActivity.this, "Пароли не совпадают", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}
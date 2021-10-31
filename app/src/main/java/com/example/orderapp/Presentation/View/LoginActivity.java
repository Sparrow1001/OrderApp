package com.example.orderapp.Presentation.View;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.orderapp.Presentation.ViewModel.LoginViewModel;
import com.example.orderapp.R;
import com.example.orderapp.Repository.Model.PersonDTO;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {
    private Button loginBt;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInAccount account;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBt = findViewById(R.id.loginBt);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null){
            updateUI(account);
        }

        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 100);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            loginViewModel = new LoginViewModel(getApplication());

            PersonDTO person = new PersonDTO();
            person.setEmail(account.getEmail());
            person.setFirstName(account.getGivenName());
            person.setLastName(account.getFamilyName());
            loginViewModel.insertPerson(person);

            updateUI(account);

        } catch (ApiException e) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }

    private void updateUI(GoogleSignInAccount account){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("name", account.getDisplayName());
        startActivity(intent);
        finish();
    }
}
package com.example.orderapp.Presentation.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.orderapp.Repository.Model.PersonDTO;
import com.example.orderapp.Repository.Repository;

public class LoginViewModel extends AndroidViewModel {

    private Repository rep = new Repository();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        rep.initBase(application);
    }

    public void insertPerson(PersonDTO person){
        rep.insertPerson(person);
    }

}

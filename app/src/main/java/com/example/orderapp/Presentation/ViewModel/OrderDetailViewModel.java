package com.example.orderapp.Presentation.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.orderapp.Repository.Model.OrderDTO;
import com.example.orderapp.Repository.Repository;

public class OrderDetailViewModel extends AndroidViewModel {
    private Repository rep = new Repository();

    public OrderDetailViewModel(@NonNull Application application) {
        super(application);
        rep.initBase(application);
    }

    public LiveData<OrderDTO> getOrderById(int id){
        return rep.getOrderById(id);
    }


}

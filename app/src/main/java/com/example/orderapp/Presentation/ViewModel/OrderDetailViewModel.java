package com.example.orderapp.Presentation.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.orderapp.Repository.Model.OrderDTO;
import com.example.orderapp.Repository.Network.CoordinatesLogic;
import com.example.orderapp.Repository.Network.Geo_center;
import com.example.orderapp.Repository.Repository;

import java.util.List;

public class OrderDetailViewModel extends AndroidViewModel {
    private Repository rep = new Repository();

    public OrderDetailViewModel(@NonNull Application application) {
        super(application);
        rep.initBase(application);
    }

    public LiveData<OrderDTO> getOrderById(int id){
        return rep.getOrderById(id);
    }

    public LiveData<Geo_center> getCoordinate(String query){
        return rep.getCoordinate(query);
    }

}

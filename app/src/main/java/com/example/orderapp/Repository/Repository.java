package com.example.orderapp.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.orderapp.Repository.Model.OrderDTO;
import com.example.orderapp.Repository.Room.OrderRepository;

import java.util.List;

public class Repository {
    private OrderRepository repository;
    private LiveData<List<OrderDTO>> allOrders;

    public void initBase(Application application){

        if (repository == null){
            repository = new OrderRepository(application);
        }

    }

    public LiveData<List<OrderDTO>> getAllOrders(){
        allOrders = repository.getAllOrders();
        return allOrders;
    }

    public void insert(OrderDTO order){
        repository.insert(order);
    }

    public void delete(OrderDTO order){
        repository.delete(order);
    }


}
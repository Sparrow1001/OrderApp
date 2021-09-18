package com.example.orderapp.Presentation.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.orderapp.Presentation.Repository.Model.OrderDTO;
import com.example.orderapp.Presentation.Repository.Room.OrderRepository;

import java.util.List;

public class OrderViewModel extends AndroidViewModel {

    private OrderRepository repository;
    private LiveData<List<OrderDTO>> allOrders;

    public OrderViewModel(@NonNull Application application) {
        super(application);
        repository = new OrderRepository(application);
        allOrders = repository.getAllOrders();
    }

    public void insert(OrderDTO order){
        repository.insert(order);
    }
    public void update(OrderDTO order){
        repository.update(order);
    }
    public void delete(OrderDTO order){
        repository.delete(order);
    }
    public void deleteAllOrders(OrderDTO order){
        repository.deleteAllOrders(order);
    }
    public LiveData<List<OrderDTO>> getAllOrders(){
        return allOrders;
    }

}

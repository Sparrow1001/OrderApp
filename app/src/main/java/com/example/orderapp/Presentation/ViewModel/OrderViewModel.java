package com.example.orderapp.Presentation.ViewModel;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.orderapp.Presentation.Repository.Model.OrderDTO;
import com.example.orderapp.Presentation.Repository.Room.OrderRepository;
import com.example.orderapp.Presentation.View.AddOrderActivity;

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

    public void addOrder(String place,
                         int numOfVisitors,
                         String arrivalTime){

        OrderDTO orderDTO = new OrderDTO("Evgeniy", place, arrivalTime, numOfVisitors);
        insert(orderDTO);

    }
}

package com.example.orderapp.Presentation.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.orderapp.Repository.Model.OrderDTO;
import com.example.orderapp.Repository.Model.PersonDTO;
import com.example.orderapp.Repository.Repository;
import com.example.orderapp.Repository.Room.OrderRepository;

import java.util.List;

public class OrderViewModel extends AndroidViewModel {

    private LiveData<List<OrderDTO>> allOrders;
    private Repository rep = new Repository();

    public OrderViewModel(@NonNull Application application) {
        super(application);
        rep.initBase(application);
        allOrders = rep.getAllOrders();
    }

    public void insert(OrderDTO order){
        rep.insert(order);
    }

    public void delete(OrderDTO order){
        rep.delete(order);
    }

    public LiveData<List<OrderDTO>> getAllOrders(){
        return allOrders;
    }

    public LiveData<List<OrderDTO>> getOrdersByEmail(String email){
        return rep.getOrdersByEmail(email);
    }

    public LiveData<PersonDTO> getPersonByEmail(String email){
        return rep.getPersonByEmail(email);
    }

    public void addOrder(String name,
                         String customerEmail,
                         String place,
                         int numOfVisitors,
                         String arrivalTime,
                         int timeOfStay,
                         String choosedFood,
                         String address){

        OrderDTO orderDTO = new OrderDTO(name, customerEmail, place, arrivalTime, numOfVisitors, timeOfStay, choosedFood, address);
        insert(orderDTO);

    }
}

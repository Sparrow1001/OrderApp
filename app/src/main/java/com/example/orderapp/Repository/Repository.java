package com.example.orderapp.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.orderapp.Repository.Model.OrderDTO;
import com.example.orderapp.Repository.Model.PersonDTO;
import com.example.orderapp.Repository.Network.MapsAPI.CoordinatesLogic;
import com.example.orderapp.Repository.Model.Geo_center;
import com.example.orderapp.Repository.Room.OrderRepository;

import java.util.List;

public class Repository {
    private OrderRepository repository;
    private LiveData<List<OrderDTO>> allOrders;
    private CoordinatesLogic coordinatesLogic = new CoordinatesLogic();

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
        repository.insertOrder(order);
    }

    public void insertPerson(PersonDTO person){
        repository.insertPerson(person);
    }

    public LiveData<PersonDTO> checkPerson(String email, String password){
        return repository.checkPerson(email, password);
    }


    public void delete(OrderDTO order){
        repository.deleteOrder(order);
    }

    public LiveData<OrderDTO> getOrderById(int id){
        return repository.getOrderById(id);
    }

    public LiveData<Geo_center> getCoordinate(String query){
        return coordinatesLogic.getCoordinate(query);
    }


}

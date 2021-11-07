package com.example.orderapp.Repository.Room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.orderapp.Repository.Model.OrderDTO;
import com.example.orderapp.Repository.Model.PersonDTO;
import com.example.orderapp.Repository.Room.DAO.OrderDAO;
import com.example.orderapp.Repository.Room.DAO.PersonDAO;

import java.util.List;

public class OrderRepository {

    private OrderDAO orderDAO;
    private PersonDAO personDAO;
    private LiveData<OrderDTO> order;
    private LiveData<List<OrderDTO>> allOrders;

    public OrderRepository(Application application){
        OrderRoomDatabase database = OrderRoomDatabase.getDatabase(application);
        orderDAO = database.orderDAO();
        personDAO = database.personDAO();
        allOrders= orderDAO.getAllOrders();
    }

    public void insertOrder(OrderDTO order){
        OrderRoomDatabase.databaseWriteExecutor.execute(() -> {
            orderDAO.addOrder(((OrderDTO) order));
        });
    }

    public void insertPerson(PersonDTO person){
        OrderRoomDatabase.databaseWriteExecutor.execute(() -> {
            personDAO.addPerson(((PersonDTO) person));
        });
    }

    public LiveData<PersonDTO> checkPerson(String email, String password){
        return personDAO.checkPerson(email, password);
    }

    public void updateOrder(OrderDTO order){

    }

    public void deleteOrder(OrderDTO order){
        OrderRoomDatabase.databaseWriteExecutor.execute(() -> {
            orderDAO.deleteOrder(((OrderDTO) order));
        });
    }

    public void deleteAllOrders(OrderDTO order){

    }

    public LiveData<List<OrderDTO>> getAllOrders(){
        return allOrders;
    }

    public LiveData<OrderDTO> getOrderById(int id){
        return order = orderDAO.getOrderById(id);
    }

}

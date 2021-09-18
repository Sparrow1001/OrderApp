package com.example.orderapp.Presentation.Repository.Room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.orderapp.Domain.Model.Order;
import com.example.orderapp.Presentation.Repository.Model.OrderDTO;
import com.example.orderapp.Presentation.Repository.Room.DAO.OrderDAO;

import java.util.List;

public class OrderRepository {

    private OrderDAO orderDAO;
    private LiveData<List<OrderDTO>> allOrders;

    public OrderRepository(Application application){
        OrderRoomDatabase database = OrderRoomDatabase.getDatabase(application);
        orderDAO = database.orderDAO();
        allOrders= orderDAO.getAllOrders();
    }

    public void insert(OrderDTO order){
        OrderRoomDatabase.databaseWriteExecutor.execute(() -> {
            orderDAO.addOrder(((OrderDTO) order));
        });
    }

    public void update(OrderDTO order){

    }

    public void delete(OrderDTO order){
        OrderRoomDatabase.databaseWriteExecutor.execute(() -> {
            orderDAO.deleteOrder(((OrderDTO) order));
        });
    }

    public void deleteAllOrders(OrderDTO order){

    }

    public LiveData<List<OrderDTO>> getAllOrders(){
        return allOrders;
    }

}

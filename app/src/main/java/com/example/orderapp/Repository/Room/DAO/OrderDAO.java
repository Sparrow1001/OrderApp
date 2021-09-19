package com.example.orderapp.Repository.Room.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.orderapp.Repository.Model.OrderDTO;

import java.util.List;

@Dao
public interface OrderDAO {

    @Insert
    void addOrder(OrderDTO order);

    @Delete
    void deleteOrder(OrderDTO order);

    @Update
    void updateOrder(OrderDTO order);

    @Query("SELECT * FROM `order`")
    LiveData<List<OrderDTO>> getAllOrders();

}

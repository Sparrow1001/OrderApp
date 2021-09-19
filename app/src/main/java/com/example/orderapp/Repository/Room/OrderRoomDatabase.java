package com.example.orderapp.Repository.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.orderapp.Repository.Model.OrderDTO;
import com.example.orderapp.Repository.Room.DAO.OrderDAO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {OrderDTO.class}, version = 1)
public abstract class OrderRoomDatabase extends RoomDatabase {

    public abstract OrderDAO orderDAO();

    private static volatile OrderRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static OrderRoomDatabase getDatabase(final Context context){

        if (INSTANCE == null) {
            synchronized (OrderRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            OrderRoomDatabase.class, "order_database")
                            .build();
                }
            }
        }
        return INSTANCE;

    }


}

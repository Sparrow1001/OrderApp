package com.example.orderapp.Repository.Room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.orderapp.Repository.Model.OrderDTO;
import com.example.orderapp.Repository.Model.PersonDTO;
import com.example.orderapp.Repository.Room.DAO.OrderDAO;
import com.example.orderapp.Repository.Room.DAO.PersonDAO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {OrderDTO.class, PersonDTO.class}, version = 4, exportSchema = false)
public abstract class OrderRoomDatabase extends RoomDatabase {

    public abstract OrderDAO orderDAO();
    public abstract PersonDAO personDAO();

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
//                            .addCallback(new Callback() {
//                                @Override
//                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
//                                    super.onCreate(db);
//                                    databaseWriteExecutor.execute(() ->{
//                                        PersonDTO user = new PersonDTO();
//                                        user.setEmail("user@mirea.ru");
//                                        user.setPassword("user");
//
//                                    });
//                                }
//                            })
                            .build();
                }
            }
        }
        return INSTANCE;

    }


}

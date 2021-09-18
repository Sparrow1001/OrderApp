package com.example.orderapp.Presentation.Repository.Mock;

import androidx.lifecycle.MutableLiveData;

import com.example.orderapp.Domain.Model.Order;
import com.example.orderapp.Domain.Model.Person;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MockBase {
    MutableLiveData<List<Order>> data;
    List<Order> list;

    public MutableLiveData<List<Order>> getAllOrders(){return data;}

    public MockBase(){

        list = new ArrayList<>();

        Order order1 = new Order("Bloody Mary Bar",
                new Person("Ivan", "Ivanov", "+79771231111"),
                LocalDateTime.of(2021, 12, 25, 18, 30),
                4);
        list.add(order1);
        data = new MutableLiveData<>(list);
    }

    public <T extends Order> void addOrder(T order) {
        list.add(order);

        data.setValue(list);
    }

    public <T extends Order> void deleteOrder(T order) {
        list.remove(order);

        data.setValue(list);
    }

}

package com.example.orderapp.Repository.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "order")
public class OrderDTO {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo
    public String customer;
    @ColumnInfo
    public String place;
    @ColumnInfo
    public String arrivalTime;
    @ColumnInfo
    public int numOfVisitors;

    public OrderDTO() {
    }

    public OrderDTO(String customer, String place, String arrivalTime, int numOfVisitors) {
        this.customer = customer;
        this.place = place;
        this.arrivalTime = arrivalTime;
        this.numOfVisitors = numOfVisitors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getNumOfVisitors() {
        return numOfVisitors;
    }

    public void setNumOfVisitors(int numOfVisitors) {
        this.numOfVisitors = numOfVisitors;
    }
}

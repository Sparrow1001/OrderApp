package com.example.orderapp.Repository.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
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
    public String customerEmail;
    @ColumnInfo
    public String place;
    @ColumnInfo
    public String arrivalTime;
    @ColumnInfo
    public int numOfVisitors;
    @ColumnInfo
    public int timeOfStay;
    @ColumnInfo
    public String chooseFood;
    @ColumnInfo
    public String address;

    @Ignore
    public OrderDTO() {
    }

    public OrderDTO(String customer, String customerEmail, String place, String arrivalTime, int numOfVisitors, int timeOfStay, String chooseFood, String address) {
        this.customer = customer;
        this.place = place;
        this.arrivalTime = arrivalTime;
        this.numOfVisitors = numOfVisitors;
        this.timeOfStay = timeOfStay;
        this.chooseFood = chooseFood;
        this.address = address;
        this.customerEmail = customerEmail;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTimeOfStay() {
        return timeOfStay;
    }

    public void setTimeOfStay(int timeOfStay) {
        this.timeOfStay = timeOfStay;
    }

    public String getChooseFood() {
        return chooseFood;
    }

    public void setChooseFood(String chooseFood) {
        this.chooseFood = chooseFood;
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

package com.example.orderapp.Domain.Model;

import java.time.LocalDateTime;

public class Order {

    private String place;
    private Person customer;
    private LocalDateTime arrivalTime;
    private int numberOfVisitors;

    public Order(String place, Person customer, LocalDateTime arrivalTime, int numberOfVisitors) {
        this.place = place;
        this.customer = customer;
        this.arrivalTime = arrivalTime;
        this.numberOfVisitors = numberOfVisitors;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Person getCustomer() {
        return customer;
    }

    public void setCustomer(Person customer) {
        this.customer = customer;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getNumberOfVisitors() {
        return numberOfVisitors;
    }

    public void setNumberOfVisitors(int numberOfVisitors) {
        this.numberOfVisitors = numberOfVisitors;
    }
}

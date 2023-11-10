package com.clothingstore.models;

import java.sql.Timestamp;

public class OrderModel {
    private int id;
    private int customerId;
    private int userId;
    private Timestamp orderDate;
    private double totalPrice;

    public OrderModel() {
    }

    public OrderModel(int id, int customerId, int userId, Timestamp orderDate, double totalPrice) {
        this.id = id;
        this.customerId = customerId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

}

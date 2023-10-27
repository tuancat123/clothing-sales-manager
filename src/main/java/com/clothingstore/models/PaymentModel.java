package com.clothingstore.models;

import java.sql.Timestamp;

public class PaymentModel {

    private int id;
    private int orderId;
    private int paymentMethodId;
    private Timestamp paymentDate;
    private double totalPrice;

    public PaymentModel() {
    }

    public PaymentModel(int id, int orderId, int paymentMethodId, Timestamp paymentDate, double totalPrice) {
        this.id = id;
        this.orderId = orderId;
        this.paymentMethodId = paymentMethodId;
        this.paymentDate = paymentDate;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

}

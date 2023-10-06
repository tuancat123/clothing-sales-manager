package com.clothingstore.models;

import java.sql.Timestamp;

public class PurchaseModel {
    private int id;
    private int customerId;
    private Timestamp purchaseDate;
    private double totalAmount;

    public PurchaseModel() {
    }

    public PurchaseModel(int id, int customerId, Timestamp purchaseDate, double totalAmount) {
        this.id = id;
        this.customerId = customerId;
        this.purchaseDate = purchaseDate;
        this.totalAmount = totalAmount;
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

    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public PurchaseModel id(int id) {
        setId(id);
        return this;
    }

    public PurchaseModel customerId(int customerId) {
        setCustomerId(customerId);
        return this;
    }

    public PurchaseModel purchaseDate(Timestamp purchaseDate) {
        setPurchaseDate(purchaseDate);
        return this;
    }

    public PurchaseModel totalAmount(double totalAmount) {
        setTotalAmount(totalAmount);
        return this;
    }

    @Override
    public String toString() {
        return "PurchaseModel{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", purchaseDate=" + purchaseDate +
                ", totalAmount=" + totalAmount +
                '}';
    }
}

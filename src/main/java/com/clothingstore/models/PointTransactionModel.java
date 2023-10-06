package com.clothingstore.models;

import java.sql.Timestamp;

public class PointTransactionModel {
    private int id;
    private int customerId;
    private Timestamp transactionDate;
    private int pointsEarned;
    private int pointsUsed;

    public PointTransactionModel() {
    }

    public PointTransactionModel(int id, int customerId, Timestamp transactionDate, int pointsEarned, int pointsUsed) {
        this.id = id;
        this.customerId = customerId;
        this.transactionDate = transactionDate;
        this.pointsEarned = pointsEarned;
        this.pointsUsed = pointsUsed;
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

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getPointsEarned() {
        return pointsEarned;
    }

    public void setPointsEarned(int pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public int getPointsUsed() {
        return pointsUsed;
    }

    public void setPointsUsed(int pointsUsed) {
        this.pointsUsed = pointsUsed;
    }

    public PointTransactionModel id(int id) {
        setId(id);
        return this;
    }

    public PointTransactionModel customerId(int customerId) {
        setCustomerId(customerId);
        return this;
    }

    public PointTransactionModel transactionDate(Timestamp transactionDate) {
        setTransactionDate(transactionDate);
        return this;
    }

    public PointTransactionModel pointsEarned(int pointsEarned) {
        setPointsEarned(pointsEarned);
        return this;
    }

    public PointTransactionModel pointsUsed(int pointsUsed) {
        setPointsUsed(pointsUsed);
        return this;
    }

    @Override
    public String toString() {
        return "PointTransactionModel{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", transactionDate=" + transactionDate +
                ", pointsEarned=" + pointsEarned +
                ", pointsUsed=" + pointsUsed +
                '}';
    }
}

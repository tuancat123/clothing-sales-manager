package com.clothingstore.models;

public class PointModel {
    private int id;
    private int customerId;
    private int pointsEarned;
    private int pointsUsed;

    public PointModel() {
    }

    public PointModel(int id, int customerId, int pointsEarned, int pointsUsed) {
        this.id = id;
        this.customerId = customerId;
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

    public PointModel id(int id) {
        setId(id);
        return this;
    }

    public PointModel customerId(int customerId) {
        setCustomerId(customerId);
        return this;
    }

    public PointModel pointsEarned(int pointsEarned) {
        setPointsEarned(pointsEarned);
        return this;
    }

    public PointModel pointsUsed(int pointsUsed) {
        setPointsUsed(pointsUsed);
        return this;
    }
}

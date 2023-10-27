package com.clothingstore.models;

import java.sql.Timestamp;

public class DiscountModel {

    private int id;
    private String discountName;
    private int discountPercentage;
    private Timestamp startDay;
    private Timestamp endDay;

    public DiscountModel() {
    }

    public DiscountModel(int id, String discountName, int discountPercentage, Timestamp startDay, Timestamp endDay) {
        this.id = id;
        this.discountName = discountName;
        this.discountPercentage = discountPercentage;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Timestamp getStartDay() {
        return startDay;
    }

    public void setStartDay(Timestamp startDay) {
        this.startDay = startDay;
    }

    public Timestamp getEndDay() {
        return endDay;
    }

    public void setEndDay(Timestamp endDay) {
        this.endDay = endDay;
    }

}

package com.clothingstore.models;

public class DiscountItemModel {
    private int id;
    private int categoryId;
    private int discountId;

    public DiscountItemModel() {
    }

    public DiscountItemModel(int id, int categoryId, int discountId) {
        this.id = id;
        this.categoryId = categoryId;
        this.discountId = discountId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

}

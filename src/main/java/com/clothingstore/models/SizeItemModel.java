package com.clothingstore.models;

public class SizeItemModel {
    private int productId;
    private int sizeId;
    private int quantity;

    public SizeItemModel() {
    }

    public SizeItemModel(int productId, int sizeId, int quantity) {
        this.productId = productId;
        this.sizeId = sizeId;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public SizeItemModel productId(int productId) {
        setProductId(productId);
        return this;
    }

    public SizeItemModel sizeId(int sizeId) {
        setSizeId(sizeId);
        return this;
    }

    public SizeItemModel quantity(int quantity) {
        setQuantity(quantity);
        return this;
    }
}
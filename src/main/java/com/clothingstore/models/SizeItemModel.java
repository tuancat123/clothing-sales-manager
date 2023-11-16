package com.clothingstore.models;

public class SizeItemModel {
    private int id;
    private int productId;
    private int sizeId;
    private int quantity;

    public SizeItemModel() {
    }

    public SizeItemModel(int id,int productId, int sizeId, int quantity) {
        this.id = id;
        this.productId = productId;
        this.sizeId = sizeId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public SizeItemModel id(int id) {
        setId(id);
        return this;
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

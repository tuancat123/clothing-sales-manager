package com.clothingstore.models;

public class OrderItemModel {
  private int id;
  private int orderId;
  private int productId;
  private int sizeId;
  private int quantity;
  private double price;

  public OrderItemModel() {
  }

  public OrderItemModel(int id, int orderId, int productId, int sizeId, int quantity, double price) {
    this.id = id;
    this.orderId = orderId;
    this.productId = productId;
    this.sizeId = sizeId;
    this.quantity = quantity;
    this.price = price;
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

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

}

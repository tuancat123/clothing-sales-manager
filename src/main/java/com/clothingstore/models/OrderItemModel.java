package com.clothingstore.models;

public class OrderItemModel {
  private int id;
  private int orderId;
  private int productId;
  private int quantity;
  private int price;

  public OrderItemModel() {
  }

  public OrderItemModel(int id, int orderId, int productId, int quantity, int price) {
    this.id = id;
    this.orderId = orderId;
    this.productId = productId;
    this.quantity = quantity;
    this.price = price;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getOrderId() {
    return this.orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public int getProductId() {
    return this.productId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int getPrice() {
    return this.price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public OrderItemModel id(int id) {
    setId(id);
    return this;
  }

  public OrderItemModel orderId(int orderId) {
    setOrderId(orderId);
    return this;
  }

  public OrderItemModel productId(int productId) {
    setProductId(productId);
    return this;
  }

  public OrderItemModel quantity(int quantity) {
    setQuantity(quantity);
    return this;
  }

  public OrderItemModel price(int price) {
    setPrice(price);
    return this;
  }
}

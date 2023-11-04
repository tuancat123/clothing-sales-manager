package com.clothingstore.models;

public class ImportItemsModel {
  private int id, import_id, product_id, size_id, quantity;
  private double price;

  public ImportItemsModel() {
  }

  public ImportItemsModel(int id, int import_id, int product_id, int size_id, int quantity, double price) {
    this.id = id;
    this.import_id = import_id;
    this.product_id = product_id;
    this.size_id = size_id;
    this.quantity = quantity;
    this.price = price;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getImport_id() {
    return import_id;
  }

  public void setImport_id(int import_id) {
    this.import_id = import_id;
  }

  public int getProduct_id() {
    return product_id;
  }

  public void setProduct_id(int product_id) {
    this.product_id = product_id;
  }

  public int getSize_id() {
    return size_id;
  }

  public void setSize_id(int size_id) {
    this.size_id = size_id;
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

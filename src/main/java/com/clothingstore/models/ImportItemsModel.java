package com.clothingstore.models;

public class ImportItemsModel {
  private int id, import_id, product_id, quantity;
  private double price;

  public ImportItemsModel() {
  }

  public ImportItemsModel(int id, int import_id, int product_id, int quantity, double price) {
    this.id = id;
    this.import_id = import_id;
    this.product_id = product_id;
    this.quantity = quantity;
    this.price = price;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getImport_id() {
    return this.import_id;
  }

  public void setImport_id(int import_id) {
    this.import_id = import_id;
  }

  public int getProduct_id() {
    return this.product_id;
  }

  public void setProduct_id(int product_id) {
    this.product_id = product_id;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public ImportItemsModel id(int id) {
    setId(id);
    return this;
  }

  public ImportItemsModel import_id(int import_id) {
    setImport_id(import_id);
    return this;
  }

  public ImportItemsModel product_id(int product_id) {
    setProduct_id(product_id);
    return this;
  }

  public ImportItemsModel quantity(int quantity) {
    setQuantity(quantity);
    return this;
  }

  public ImportItemsModel price(double price) {
    setPrice(price);
    return this;
  }

}

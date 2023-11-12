package com.clothingstore.models;

public class ProductModel {
  private int id;
  private String name;
  private int categoryId;
  private String image;
  private double price;
  private int status, gender;

  public ProductModel() {
  }

  public ProductModel(int id, String name, int categoryId, String image, int gender, double price, int status) {
    this.id = id;
    this.name = name;
    this.categoryId = categoryId;
    this.image = image;
    this.gender = gender;
    this.price = price;
    this.status = status;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public int getGender() {
    return gender;
  }

  public void setGender(int gender) {
    this.gender = gender;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

}

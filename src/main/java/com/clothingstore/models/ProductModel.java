package com.clothingstore.models;

public class ProductModel {
  private int id;
  private String name;
  private int categoryId;
  private String image, gender;
  private double price;

  public ProductModel() {
  }

  public ProductModel(int id, String name, int categoryId, String image, String gender, double price) {
    this.id = id;
    this.name = name;
    this.categoryId = categoryId;
    this.image = image;
    this.gender = gender;
    this.price = price;
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

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

}

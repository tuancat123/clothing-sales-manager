package com.clothingstore.models;

public class ProductModel {
  private int id;
  private float rating;
  private String name, category, image, gender;

  public ProductModel() {
  }

  public ProductModel(int id, float rating, String name, String category, String image, String gender) {
    this.id = id;
    this.rating = rating;
    this.name = name;
    this.category = category;
    this.image = image;
    this.gender = gender;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public float getRating() {
    return this.rating;
  }

  public void setRating(float rating) {
    this.rating = rating;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCategory() {
    return this.category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getImage() {
    return this.image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getGender() {
    return this.gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public ProductModel id(int id) {
    setId(id);
    return this;
  }

  public ProductModel rating(float rating) {
    setRating(rating);
    return this;
  }

  public ProductModel name(String name) {
    setName(name);
    return this;
  }

  public ProductModel category(String category) {
    setCategory(category);
    return this;
  }

  public ProductModel image(String image) {
    setImage(image);
    return this;
  }

  public ProductModel gender(String gender) {
    setGender(gender);
    return this;
  }

}

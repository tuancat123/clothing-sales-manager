package com.clothingstore.models;

import java.time.LocalDate;

public class ImportModel {
  private int id;
  private int userId;
  private LocalDate importDate;
  private double totalPrice;

  public ImportModel() {
  }

  public ImportModel(int id, int userId, LocalDate importDate, double totalPrice) {
    this.id = id;
    this.userId = userId;
    this.importDate = importDate;
    this.totalPrice = totalPrice;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public LocalDate getImportDate() {
    return importDate;
  }

  public void setImportDate(LocalDate importDate) {
    this.importDate = importDate;
  }

  public double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }

}

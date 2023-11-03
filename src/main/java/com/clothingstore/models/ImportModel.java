package com.clothingstore.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ImportModel {
  private int id;
  private int userId;
  private LocalDateTime importDate;
  private double totalPrice;

  public ImportModel() {
  }

  public ImportModel(int id, int userId, LocalDateTime importDate, double totalPrice) {
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

  public LocalDateTime getImportDate() {
    return importDate;
  }

  public void setImportDate(LocalDateTime importDate) {
    this.importDate = importDate;
  }

  public double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }

}

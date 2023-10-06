package com.clothingstore.models;

import java.time.LocalDate;

public class ImportModel {
  private int id;
  private LocalDate importDate;
  private double totalCost;

  public ImportModel() {
  }

  public ImportModel(int id, LocalDate importDate, double totalCost) {
    this.id = id;
    this.importDate = importDate;
    this.totalCost = totalCost;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public LocalDate getImportDate() {
    return this.importDate;
  }

  public void setImportDate(LocalDate importDate) {
    this.importDate = importDate;
  }

  public double getTotalCost() {
    return this.totalCost;
  }

  public void setTotalCost(double totalCost) {
    this.totalCost = totalCost;
  }

  public ImportModel id(int id) {
    setId(id);
    return this;
  }

  public ImportModel importDate(LocalDate importDate) {
    setImportDate(importDate);
    return this;
  }

  public ImportModel totalCost(double totalCost) {
    setTotalCost(totalCost);
    return this;
  }

}

package com.clothingstore.models;

public class PaymentMethodModel {
  private int id;
  private String methodName;

  public PaymentMethodModel() {
  }

  public PaymentMethodModel(int id, String methodName) {
    this.id = id;
    this.methodName = methodName;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getMethodName() {
    return this.methodName;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }

  public PaymentMethodModel id(int id) {
    setId(id);
    return this;
  }

  public PaymentMethodModel methodName(String methodName) {
    setMethodName(methodName);
    return this;
  }
}

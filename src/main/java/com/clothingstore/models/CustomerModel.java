package com.clothingstore.models;

public class CustomerModel {
  private int id;
  private String customerName, phone, email;

  public CustomerModel() {
  }

  public CustomerModel(int id, String customerName, String phone, String email) {
    this.id = id;
    this.customerName = customerName;
    this.phone = phone;
    this.email = email;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCustomerName() {
    return this.customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getPhone() {
    return this.phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public CustomerModel id(int id) {
    setId(id);
    return this;
  }

  public CustomerModel customerName(String customerName) {
    setCustomerName(customerName);
    return this;
  }

  public CustomerModel phone(String phone) {
    setPhone(phone);
    return this;
  }

  public CustomerModel email(String email) {
    setEmail(email);
    return this;
  }

}

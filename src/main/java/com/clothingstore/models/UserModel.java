package com.clothingstore.models;

import com.clothingstore.enums.UserStatus;

public class UserModel {
  private int id;
  private String username, password, email, name, phone, address;
  private int gender;
  private String image;
  private int roleId;
  private UserStatus userStatus;

  public UserModel() {
  }

  public UserModel(String username, String password, String email, String name, String phone, String address,
      int gender, String image, int roleId, UserStatus userStatus) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.name = name;
    this.phone = phone;
    this.address = address;
    this.gender = gender;
    this.image = image;
    this.roleId = roleId;
    this.userStatus = userStatus;
  }

  

  public UserModel(int id, String username, String password, String email, String name, String phone, String address,
      int gender, String image, int roleId, UserStatus userStatus) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.email = email;
    this.name = name;
    this.phone = phone;
    this.address = address;
    this.gender = gender;
    this.image = image;
    this.roleId = roleId;
    this.userStatus = userStatus;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getGender() {
    return gender;
  }

  public void setGender(int gender) {
    this.gender = gender;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public int getRoleId() {
    return roleId;
  }

  public void setRoleId(int roleId) {
    this.roleId = roleId;
  }

  public UserStatus getUserStatus() {
    return userStatus;
  }

  public void setUserStatus(UserStatus userStatus) {
    this.userStatus = userStatus;
  }

}
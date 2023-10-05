package com.clothingstore.models;

import com.clothingstore.enums.UserStatus;

public class UserModel {
  private String username, password, email, name, phone, image, address;
  private int id, roleId, gender;
  private UserStatus userStatus;

  public UserModel() {
  }

  public UserModel(String username, String password, String email, String name, String phone, String image,
      String address, int id, int roleId, int gender, UserStatus userStatus) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.name = name;
    this.phone = phone;
    this.image = image;
    this.address = address;
    this.id = id;
    this.roleId = roleId;
    this.gender = gender;
    this.userStatus = userStatus;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return this.phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getImage() {
    return this.image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getRoleId() {
    return this.roleId;
  }

  public void setRoleId(int roleId) {
    this.roleId = roleId;
  }

  public int getGender() {
    return this.gender;
  }

  public void setGender(int gender) {
    this.gender = gender;
  }

  public UserStatus getUserStatus() {
    return this.userStatus;
  }

  public void setUserStatus(UserStatus userStatus) {
    this.userStatus = userStatus;
  }

  public UserModel username(String username) {
    setUsername(username);
    return this;
  }

  public UserModel password(String password) {
    setPassword(password);
    return this;
  }

  public UserModel email(String email) {
    setEmail(email);
    return this;
  }

  public UserModel name(String name) {
    setName(name);
    return this;
  }

  public UserModel phone(String phone) {
    setPhone(phone);
    return this;
  }

  public UserModel image(String image) {
    setImage(image);
    return this;
  }

  public UserModel address(String address) {
    setAddress(address);
    return this;
  }

  public UserModel id(int id) {
    setId(id);
    return this;
  }

  public UserModel roleId(int roleId) {
    setRoleId(roleId);
    return this;
  }

  public UserModel gender(int gender) {
    setGender(gender);
    return this;
  }

  public UserModel userStatus(UserStatus userStatus) {
    setUserStatus(userStatus);
    return this;
  }

}
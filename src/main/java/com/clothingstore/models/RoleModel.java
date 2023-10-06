package com.clothingstore.models;

public class RoleModel {
  private int id;
  private String name;

  public RoleModel() {
  }

  public RoleModel(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RoleModel id(int id) {
    setId(id);
    return this;
  }

  public RoleModel name(String name) {
    setName(name);
    return this;
  }
}

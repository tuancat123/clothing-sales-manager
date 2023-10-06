package com.clothingstore.models;

public class PermissionModel {
    private int id;
    private String permissionName;

    public PermissionModel() {
    }

    public PermissionModel(int id, String permissionName) {
        this.id = id;
        this.permissionName = permissionName;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPermissionName() {
        return this.permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public PermissionModel id(int id) {
        setId(id);
        return this;
    }

    public PermissionModel permissionName(String permissionName) {
        setPermissionName(permissionName);
        return this;
    }
}

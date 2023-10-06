package com.clothingstore.models;

public class RolePermissionModel {
    private int roleId;
    private int permissionId;

    public RolePermissionModel() {
    }

    public RolePermissionModel(int roleId, int permissionId) {
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public RolePermissionModel roleId(int roleId) {
        setRoleId(roleId);
        return this;
    }

    public RolePermissionModel permissionId(int permissionId) {
        setPermissionId(permissionId);
        return this;
    }
}

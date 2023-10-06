package com.clothingstore.bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.clothingstore.dao.RolePermissionDAO;
import com.clothingstore.interfaces.IBUS;
import com.clothingstore.models.RolePermissionModel;

public class RolePermissionBUS implements IBUS<RolePermissionModel> {
    private final List<RolePermissionModel> rolePermissionList = new ArrayList<>();
    private static RolePermissionBUS instance;

    public static RolePermissionBUS getInstance() {
        if (instance == null) {
            instance = new RolePermissionBUS();
        }
        return instance;
    }

    private RolePermissionBUS() {
        this.rolePermissionList.addAll(RolePermissionDAO.getInstance().readDatabase());
    }

    @Override
    public List<RolePermissionModel> getAllModels() {
        return Collections.unmodifiableList(rolePermissionList);
    }

    @Override
    public void refreshData() {
        rolePermissionList.clear();
        rolePermissionList.addAll(RolePermissionDAO.getInstance().readDatabase());
    }

    @Override
    public RolePermissionModel getModelById(int id) {
        refreshData();
        for (RolePermissionModel rolePermission : rolePermissionList) {
            if (rolePermission.getRoleId() == id) {
                return rolePermission;
            }
        }
        return null;
    }

    private RolePermissionModel mapToEntity(RolePermissionModel from) {
        RolePermissionModel to = new RolePermissionModel();
        updateEntityFields(from, to);
        return to;
    }

    private void updateEntityFields(RolePermissionModel from, RolePermissionModel to) {
        to.setRoleId(from.getRoleId());
        to.setPermissionId(from.getPermissionId());
    }

    @Override
    public int addModel(RolePermissionModel rolePermissionModel) {
        if (rolePermissionModel == null) {
            throw new IllegalArgumentException("The rolePermissionModel cannot be null");
        }
        int roleId = rolePermissionModel.getRoleId();
        int permissionId = rolePermissionModel.getPermissionId();
        if (roleId <= 0 || permissionId <= 0) {
            throw new IllegalArgumentException("Role ID and Permission ID must be greater than zero");
        }

        int id = RolePermissionDAO.getInstance().insert(mapToEntity(rolePermissionModel));
        rolePermissionList.add(rolePermissionModel);
        return id;
    }

    @Override
    public int updateModel(RolePermissionModel rolePermissionModel) {
        int updatedRows = RolePermissionDAO.getInstance().update(rolePermissionModel);
        if (updatedRows > 0) {
            int index = rolePermissionList.indexOf(rolePermissionModel);
            if (index != -1) {
                rolePermissionList.set(index, rolePermissionModel);
            }
        }
        return updatedRows;
    }

    @Override
    public int deleteModel(int roleId) {
        RolePermissionModel rolePermissionModel = getModelById(roleId);
        if (rolePermissionModel == null) {
            throw new IllegalArgumentException(
                    "Role Permission with Role ID: " + roleId + " doesn't exist.");
        }
        int deletedRows = RolePermissionDAO.getInstance().delete(roleId);
        if (deletedRows > 0) {
            rolePermissionList.remove(rolePermissionModel);
        }
        return deletedRows;
    }

    @Override
    public List<RolePermissionModel> searchModel(String value, String[] columns) {
        // RolePermissionBUS does not support search, return an empty list.
        return Collections.emptyList();
    }

    public boolean checkForDuplicate(int roleId, int permissionId) {
        Optional<RolePermissionModel> optionalRolePermission = RolePermissionBUS.getInstance().getAllModels().stream()
                .filter(rolePermission -> rolePermission.getRoleId() == roleId
                        && rolePermission.getPermissionId() == permissionId)
                .findFirst();
        return optionalRolePermission.isPresent();
    }
}

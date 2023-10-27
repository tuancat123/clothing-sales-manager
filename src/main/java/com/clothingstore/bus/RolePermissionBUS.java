package com.clothingstore.bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
      if (rolePermission.getId() == id) {
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
    to.setId(from.getId());
    to.setRoleId(from.getRoleId());
    to.setPermissionId(from.getPermissionId());
  }

  @Override
  public int addModel(RolePermissionModel model) {
    if (model == null || model.getRoleId() <= 0 || model.getPermissionId() <= 0) {
      throw new IllegalArgumentException(
          "There may be errors in required fields, please check your input and try again.");
    }
    int id = RolePermissionDAO.getInstance().insert(mapToEntity(model));
    rolePermissionList.add(model);
    return id;
  }

  @Override
  public int updateModel(RolePermissionModel model) {
    int updatedRows = RolePermissionDAO.getInstance().update(model);
    if (updatedRows > 0) {
      int index = rolePermissionList.indexOf(model);
      if (index != -1) {
        rolePermissionList.set(index, model);
      }
    }
    return updatedRows;
  }

  @Override
  public int deleteModel(int id) {
    RolePermissionModel rolePermission = getModelById(id);
    if (rolePermission == null) {
      throw new IllegalArgumentException(
          "RolePermission with ID: " + id + " doesn't exist.");
    }
    int deletedRows = RolePermissionDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      rolePermissionList.remove(rolePermission);
    }
    return deletedRows;
  }

  private boolean checkFilter(
      RolePermissionModel rolePermission,
      String value,
      String[] columns) {
    for (String column : columns) {
      switch (column.toLowerCase()) {
        case "id" -> {
          if (Integer.parseInt(value) == rolePermission.getId()) {
            return true;
          }
        }
        case "role_id" -> {
          if (Integer.parseInt(value) == rolePermission.getRoleId()) {
            return true;
          }
        }
        case "permission_id" -> {
          if (Integer.parseInt(value) == rolePermission.getPermissionId()) {
            return true;
          }
        }
        default -> {
          if (checkAllColumns(rolePermission, value)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private boolean checkAllColumns(RolePermissionModel rolePermission, String value) {
    return (rolePermission.getId() == Integer.parseInt(value) ||
        rolePermission.getRoleId() == Integer.parseInt(value) ||
        rolePermission.getPermissionId() == Integer.parseInt(value));
  }

  @Override
  public List<RolePermissionModel> searchModel(String value, String[] columns) {
    List<RolePermissionModel> results = new ArrayList<>();
    List<RolePermissionModel> entities = RolePermissionDAO.getInstance().search(value, columns);
    for (RolePermissionModel entity : entities) {
      RolePermissionModel model = mapToEntity(entity);
      if (checkFilter(model, value, columns)) {
        results.add(model);
      }
    }
    return results;
  }
}

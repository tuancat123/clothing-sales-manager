package com.clothingstore.bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.clothingstore.dao.PermissionDAO;
import com.clothingstore.interfaces.IBUS;
import com.clothingstore.models.PermissionModel;

public class PermissionBUS implements IBUS<PermissionModel> {
  private final List<PermissionModel> permissionList = new ArrayList<>();
  private static PermissionBUS instance;

  public static PermissionBUS getInstance() {
    if (instance == null) {
      instance = new PermissionBUS();
    }
    return instance;
  }

  private PermissionBUS() {
    this.permissionList.addAll(PermissionDAO.getInstance().readDatabase());
  }

  @Override
  public List<PermissionModel> getAllModels() {
    return Collections.unmodifiableList(permissionList);
  }

  @Override
  public void refreshData() {
    permissionList.clear();
    permissionList.addAll(PermissionDAO.getInstance().readDatabase());
  }

  @Override
  public PermissionModel getModelById(int id) {
    refreshData();
    for (PermissionModel permission : permissionList) {
      if (permission.getId() == id) {
        return permission;
      }
    }
    return null;
  }

  private PermissionModel mapToEntity(PermissionModel from) {
    PermissionModel to = new PermissionModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(PermissionModel from, PermissionModel to) {
    to.setId(from.getId());
    to.setPermissionName(from.getPermissionName());
  }

  @Override
  public int addModel(PermissionModel permissionModel) {
    if (permissionModel == null || permissionModel.getPermissionName().isEmpty()) {
      throw new IllegalArgumentException("There may be empty required fields! Please check again!");
    }
    int id = PermissionDAO.getInstance().insert(mapToEntity(permissionModel));
    permissionList.add(permissionModel);
    return id;
  }

  @Override
  public int updateModel(PermissionModel permissionModel) {
    int updatedRows = PermissionDAO.getInstance().update(permissionModel);
    if (updatedRows > 0) {
      int index = permissionList.indexOf(permissionModel);
      if (index != -1) {
        permissionList.set(index, permissionModel);
      }
    }
    return updatedRows;
  }

  @Override
  public int deleteModel(int id) {
    PermissionModel permissionModel = getModelById(id);
    if (permissionModel == null) {
      throw new IllegalArgumentException(
          "Permission with ID: " + id + " doesn't exist.");
    }
    int deletedRows = PermissionDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      permissionList.remove(permissionModel);
    }
    return deletedRows;
  }

  @Override
  public List<PermissionModel> searchModel(String value, String[] columns) {
    List<PermissionModel> results = new ArrayList<>();
    List<PermissionModel> entities = PermissionDAO.getInstance().search(value, columns);
    for (PermissionModel entity : entities) {
      PermissionModel model = mapToEntity(entity);
      if (checkFilter(model, value, columns)) {
        results.add(model);
      }
    }
    return results;
  }

  public boolean checkForDuplicate(String permissionName) {
    Optional<PermissionModel> optionalPermission = PermissionBUS.getInstance().getAllModels().stream()
        .filter(permission -> permission.getPermissionName().equals(permissionName))
        .findFirst();
    return optionalPermission.isPresent();
  }

  private boolean checkFilter(
      PermissionModel permissionModel,
      String value,
      String[] columns) {
    for (String column : columns) {
      switch (column.toLowerCase()) {
        case "id" -> {
          if (Integer.parseInt(value) == permissionModel.getId()) {
            return true;
          }
        }
        case "name" -> {
          if (value.equalsIgnoreCase(permissionModel.getPermissionName())) {
            return true;
          }
        }
        default -> {
          if (checkAllColumns(permissionModel, value)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private boolean checkAllColumns(PermissionModel permissionModel, String value) {
    return (permissionModel.getId() == Integer.parseInt(value) ||
        permissionModel.getPermissionName().contains(value));
  }

}

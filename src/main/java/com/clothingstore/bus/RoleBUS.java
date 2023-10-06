package com.clothingstore.bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.clothingstore.dao.RoleDAO;
import com.clothingstore.interfaces.IBUS;
import com.clothingstore.models.RoleModel;

public class RoleBUS implements IBUS<RoleModel> {
    private final List<RoleModel> roleList = new ArrayList<>();
    private static RoleBUS instance;

    public static RoleBUS getInstance() {
        if (instance == null) {
            instance = new RoleBUS();
        }
        return instance;
    }

    private RoleBUS() {
        this.roleList.addAll(RoleDAO.getInstance().readDatabase());
    }

    @Override
    public List<RoleModel> getAllModels() {
        return Collections.unmodifiableList(roleList);
    }

    @Override
    public void refreshData() {
        roleList.clear();
        roleList.addAll(RoleDAO.getInstance().readDatabase());
    }

    @Override
    public RoleModel getModelById(int id) {
        refreshData();
        for (RoleModel role : roleList) {
            if (role.getId() == id) {
                return role;
            }
        }
        return null;
    }

    private RoleModel mapToEntity(RoleModel from) {
        RoleModel to = new RoleModel();
        updateEntityFields(from, to);
        return to;
    }

    private void updateEntityFields(RoleModel from, RoleModel to) {
        to.setId(from.getId());
        to.setName(from.getName());
    }

    private boolean checkFilter(
            RoleModel roleModel,
            String value,
            String[] columns) {
        for (String column : columns) {
            switch (column.toLowerCase()) {
                case "id" -> {
                    if (Integer.parseInt(value) == roleModel.getId()) {
                        return true;
                    }
                }
                case "name" -> {
                    if (value.equalsIgnoreCase(roleModel.getName())) {
                        return true;
                    }
                }
                default -> {
                    if (checkAllColumns(roleModel, value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkAllColumns(RoleModel roleModel, String value) {
        return (roleModel.getId() == Integer.parseInt(value) ||
                roleModel.getName().contains(value));
    }

    @Override
    public int addModel(RoleModel roleModel) {
        if (roleModel == null ||
                roleModel.getName().isEmpty() || roleModel.getName() == null) {
            throw new IllegalArgumentException("There may be empty required fields! Please check again!");
        }
        int id = RoleDAO.getInstance().insert(mapToEntity(roleModel));
        roleList.add(roleModel);
        return id;
    }

    @Override
    public int updateModel(RoleModel roleModel) {
        int updatedRows = RoleDAO.getInstance().update(roleModel);
        if (updatedRows > 0) {
            int index = roleList.indexOf(roleModel);
            if (index != -1) {
                roleList.set(index, roleModel);
            }
        }
        return updatedRows;
    }

    @Override
    public int deleteModel(int id) {
        RoleModel roleModel = getModelById(id);
        if (roleModel == null) {
            throw new IllegalArgumentException(
                    "Role with ID: " + id + " doesn't exist.");
        }
        int deletedRows = RoleDAO.getInstance().delete(id);
        if (deletedRows > 0) {
            roleList.remove(roleModel);
        }
        return deletedRows;
    }

    @Override
    public List<RoleModel> searchModel(String value, String[] columns) {
        List<RoleModel> results = new ArrayList<>();
        List<RoleModel> entities = RoleDAO.getInstance().search(value, columns);
        for (RoleModel entity : entities) {
            RoleModel model = mapToEntity(entity);
            if (checkFilter(model, value, columns)) {
                results.add(model);
            }
        }
        return results;
    }

    public boolean checkForDuplicate(String name) {
        Optional<RoleModel> optionalRole = RoleBUS.getInstance().getAllModels().stream()
                .filter(role -> role.getName().equals(name))
                .findFirst();
        return optionalRole.isPresent();
    }

}

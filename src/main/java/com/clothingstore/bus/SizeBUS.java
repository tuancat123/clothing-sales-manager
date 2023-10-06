package com.clothingstore.bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clothingstore.dao.SizeDAO;
import com.clothingstore.interfaces.IBUS;
import com.clothingstore.models.SizeModel;

public class SizeBUS implements IBUS<SizeModel> {
    private final List<SizeModel> sizeList = new ArrayList<>();
    private static SizeBUS instance;

    public static SizeBUS getInstance() {
        if (instance == null) {
            instance = new SizeBUS();
        }
        return instance;
    }

    private SizeBUS() {
        this.sizeList.addAll(SizeDAO.getInstance().readDatabase());
    }

    @Override
    public List<SizeModel> getAllModels() {
        return Collections.unmodifiableList(sizeList);
    }

    @Override
    public void refreshData() {
        sizeList.clear();
        sizeList.addAll(SizeDAO.getInstance().readDatabase());
    }

    @Override
    public SizeModel getModelById(int id) {
        refreshData();
        for (SizeModel sizeModel : sizeList) {
            if (sizeModel.getId() == id) {
                return sizeModel;
            }
        }
        return null;
    }

    @Override
    public int addModel(SizeModel sizeModel) {
        if (sizeModel.getSize() == null || sizeModel.getSize().isEmpty()) {
            throw new IllegalArgumentException(
                "Size name can't be empty! Please check the input and try again.");
        }

        int id = SizeDAO.getInstance().insert(sizeModel);
        sizeModel.setId(id);
        sizeList.add(sizeModel);
        return id;
    }

    @Override
    public int updateModel(SizeModel sizeModel) {
        int updatedRows = SizeDAO.getInstance().update(sizeModel);
        if (updatedRows > 0) {
            int index = sizeList.indexOf(sizeModel);
            if (index != -1) {
                sizeList.set(index, sizeModel);
            }
        }
        return updatedRows;
    }

    @Override
    public int deleteModel(int id) {
        SizeModel sizeModel = getModelById(id);
        if (sizeModel == null) {
            throw new IllegalArgumentException(
                "Size with ID: " + id + " doesn't exist.");
        }
        int deletedRows = SizeDAO.getInstance().delete(id);
        if (deletedRows > 0) {
            sizeList.remove(sizeModel);
        }
        return deletedRows;
    }

    @Override
    public List<SizeModel> searchModel(String value, String[] columns) {
        List<SizeModel> results = new ArrayList<>();
        List<SizeModel> entities = getAllModels();
        for (SizeModel entity : entities) {
            if (checkFilter(entity, value, columns)) {
                results.add(entity);
            }
        }
        return results;
    }

    private boolean checkFilter(
        SizeModel sizeModel,
        String value,
        String[] columns) {
        for (String column : columns) {
            switch (column.toLowerCase()) {
                case "id" -> {
                    if (Integer.parseInt(value) == sizeModel.getId()) {
                        return true;
                    }
                }
                case "size" -> {
                    if (value.contains(sizeModel.getSize().toLowerCase())) {
                        return true;
                    }
                }
                default -> {
                }
            }
        }
        return false;
    }
}

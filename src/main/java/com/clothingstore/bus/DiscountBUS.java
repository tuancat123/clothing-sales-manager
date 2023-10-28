package com.clothingstore.bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clothingstore.dao.DiscountDAO;
import com.clothingstore.interfaces.IBUS;
import com.clothingstore.models.DiscountModel;

public class DiscountBUS implements IBUS<DiscountModel> {
    private final List<DiscountModel> discountList = new ArrayList<>();
    private static DiscountBUS instance;

    public static DiscountBUS getInstance() {
        if (instance == null) {
            instance = new DiscountBUS();
        }
        return instance;
    }

    private DiscountBUS() {
        this.discountList.addAll(DiscountDAO.getInstance().readDatabase());
    }

    @Override
    public List<DiscountModel> getAllModels() {
        return Collections.unmodifiableList(discountList);
    }

    @Override
    public void refreshData() {
        discountList.clear();
        discountList.addAll(DiscountDAO.getInstance().readDatabase());
    }

    @Override
    public DiscountModel getModelById(int id) {
        refreshData();
        for (DiscountModel discountModel : discountList) {
            if (discountModel.getId() == id) {
                return discountModel;
            }
        }
        return null;
    }

    @Override
    public int addModel(DiscountModel discountModel) {
        // Add your validation logic here if needed.
        int id = DiscountDAO.getInstance().insert(discountModel);
        discountModel.setId(id);
        discountList.add(discountModel);
        return id;
    }

    @Override
    public int updateModel(DiscountModel discountModel) {
        int updatedRows = DiscountDAO.getInstance().update(discountModel);
        if (updatedRows > 0) {
            int index = discountList.indexOf(discountModel);
            if (index != -1) {
                discountList.set(index, discountModel);
            }
        }
        return updatedRows;
    }

    @Override
    public int deleteModel(int id) {
        DiscountModel discountModel = getModelById(id);
        if (discountModel == null) {
            throw new IllegalArgumentException(
                    "Discount with ID: " + id + " doesn't exist.");
        }
        int deletedRows = DiscountDAO.getInstance().delete(id);
        if (deletedRows > 0) {
            discountList.remove(discountModel);
        }
        return deletedRows;
    }

    @Override
    public List<DiscountModel> searchModel(String value, String[] columns) {
        List<DiscountModel> results = new ArrayList<>();
        List<DiscountModel> entities = getAllModels();
        for (DiscountModel entity : entities) {
            if (checkFilter(entity, value, columns)) {
                results.add(entity);
            }
        }
        return results;
    }

    private boolean checkFilter(
            DiscountModel discountModel,
            String value,
            String[] columns) {
        for (String column : columns) {
            switch (column.toLowerCase()) {
                case "id" -> {
                    if (Integer.parseInt(value) == discountModel.getId()) {
                        return true;
                    }
                }
                case "name" -> {
                    if (value.contains(discountModel.getDiscountName().toLowerCase())) {
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

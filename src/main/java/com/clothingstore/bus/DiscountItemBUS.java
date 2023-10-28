package com.clothingstore.bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clothingstore.dao.DiscountItemDAO;
import com.clothingstore.interfaces.IBUS;
import com.clothingstore.models.DiscountItemModel;

public class DiscountItemBUS implements IBUS<DiscountItemModel> {
    private final List<DiscountItemModel> discountItemList = new ArrayList<>();
    private static DiscountItemBUS instance;

    public static DiscountItemBUS getInstance() {
        if (instance == null) {
            instance = new DiscountItemBUS();
        }
        return instance;
    }

    private DiscountItemBUS() {
        this.discountItemList.addAll(DiscountItemDAO.getInstance().readDatabase());
    }

    @Override
    public List<DiscountItemModel> getAllModels() {
        return Collections.unmodifiableList(discountItemList);
    }

    @Override
    public void refreshData() {
        discountItemList.clear();
        discountItemList.addAll(DiscountItemDAO.getInstance().readDatabase());
    }

    @Override
    public DiscountItemModel getModelById(int id) {
        refreshData();
        for (DiscountItemModel discountItem : discountItemList) {
            if (discountItem.getId() == id) {
                return discountItem;
            }
        }
        return null;
    }

    @Override
    public int addModel(DiscountItemModel discountItem) {
        int id = DiscountItemDAO.getInstance().insert(discountItem);
        discountItem.setId(id);
        discountItemList.add(discountItem);
        return id;
    }

    @Override
    public int updateModel(DiscountItemModel discountItemModel) {
        int updatedRows = DiscountItemDAO.getInstance().update(discountItemModel);
        if (updatedRows > 0) {
            int index = discountItemList.indexOf(discountItemModel);
            if (index != -1) {
                discountItemList.set(index, discountItemModel);
            }
        }
        return updatedRows;
    }

    @Override
    public int deleteModel(int id) {
        DiscountItemModel discountItemModel = getModelById(id);
        if (discountItemModel == null) {
            throw new IllegalArgumentException(
                    "DiscountItem with ID: " + id + " doesn't exist.");
        }
        int deletedRows = DiscountItemDAO.getInstance().delete(id);
        if (deletedRows > 0) {
            discountItemList.remove(discountItemModel);
        }
        return deletedRows;
    }

    @Override
    public List<DiscountItemModel> searchModel(String value, String[] columns) {
        List<DiscountItemModel> results = new ArrayList<>();
        List<DiscountItemModel> entities = getAllModels();
        for (DiscountItemModel entity : entities) {
            if (checkFilter(entity, value, columns)) {
                results.add(entity);
            }
        }
        return results;
    }

    private boolean checkFilter(
            DiscountItemModel discountItem,
            String value,
            String[] columns) {
        for (String column : columns) {
            switch (column.toLowerCase()) {
                case "id" -> {
                    if (Integer.parseInt(value) == discountItem.getId()) {
                        return true;
                    }
                }
                case "category_id" -> {
                    if (Integer.parseInt(value) == discountItem.getCategoryId()) {
                        return true;
                    }
                }
                case "discount_id" -> {
                    if (Integer.parseInt(value) == discountItem.getDiscountId()) {
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

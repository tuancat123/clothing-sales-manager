package com.clothingstore.bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clothingstore.dao.CategoryDAO;
import com.clothingstore.models.CategoryModel;

public class CategoryBUS {
    private final List<CategoryModel> categoryList = new ArrayList<>();
    private static CategoryBUS instance;

    public static CategoryBUS getInstance() {
        if (instance == null) {
            instance = new CategoryBUS();
        }
        return instance;
    }

    private CategoryBUS() {
        this.categoryList.addAll(CategoryDAO.getInstance().readDatabase());
    }

    public List<CategoryModel> getAllCategories() {
        return Collections.unmodifiableList(categoryList);
    }

    public void refreshData() {
        categoryList.clear();
        categoryList.addAll(CategoryDAO.getInstance().readDatabase());
    }

    public CategoryModel getCategoryById(int id) {
        refreshData();
        for (CategoryModel category : categoryList) {
            if (category.getId() == id) {
                return category;
            }
        }
        return null;
    }

    public int addCategory(CategoryModel category) {
        // Add your validation logic here if needed.
        int id = CategoryDAO.getInstance().insert(category);
        category.setId(id);
        categoryList.add(category);
        return id;
    }

    public int updateCategory(CategoryModel category) {
        int updatedRows = CategoryDAO.getInstance().update(category);
        if (updatedRows > 0) {
            int index = categoryList.indexOf(category);
            if (index != -1) {
                categoryList.set(index, category);
            }
        }
        return updatedRows;
    }

    public int deleteCategory(int id) {
        CategoryModel category = getCategoryById(id);
        if (category == null) {
            throw new IllegalArgumentException(
                    "Category with ID: " + id + " doesn't exist.");
        }
        int deletedRows = CategoryDAO.getInstance().delete(id);
        if (deletedRows > 0) {
            categoryList.remove(category);
        }
        return deletedRows;
    }

    public List<CategoryModel> searchCategory(String value, String[] columns) {
        List<CategoryModel> results = new ArrayList<>();
        List<CategoryModel> categories = getAllCategories();
        for (CategoryModel category : categories) {
            if (checkFilter(category, value, columns)) {
                results.add(category);
            }
        }
        return results;
    }

    private boolean checkFilter(
            CategoryModel category,
            String value,
            String[] columns) {
        for (String column : columns) {
            switch (column.toLowerCase()) {
                case "id" -> {
                    if (Integer.parseInt(value) == category.getId()) {
                        return true;
                    }
                }
                case "name" -> {
                    if (value.contains(category.getCategoryName().toLowerCase())) {
                        return true;
                    }
                }
                default -> {
                }
            }
        }
        return false;
    }

    public int getCategoryIdByName(String categoryName) {
        List<CategoryModel> categories = getAllCategories();
        for (CategoryModel category : categories) {
            if (category.getCategoryName().equals(categoryName)) {
                return category.getId();
            }
        }
        return -1;
    }
}

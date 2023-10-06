package com.clothingstore.bus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.clothingstore.dao.ProductDAO;
import com.clothingstore.interfaces.IBUS;
import com.clothingstore.models.ProductModel;

public class ProductBUS implements IBUS<ProductModel> {
  private final List<ProductModel> productList = new ArrayList<>();
  private static ProductBUS instance;

  public static ProductBUS getInstance() {
    if (instance == null) {
      instance = new ProductBUS();
    }
    return instance;
  }

  private ProductBUS() {
    this.productList.addAll(ProductDAO.getInstance().readDatabase());
  }

  @Override
  public List<ProductModel> getAllModels() {
    return Collections.unmodifiableList(productList);
  }

  @Override
  public void refreshData() {
    productList.clear();
    productList.addAll(ProductDAO.getInstance().readDatabase());
  }

  @Override
  public ProductModel getModelById(int id) {
    refreshData();
    for (ProductModel product : productList) {
      if (product.getId() == id) {
        return product;
      }
    }
    return null;
  }

  private ProductModel mapToEntity(ProductModel from) {
    ProductModel to = new ProductModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(ProductModel from, ProductModel to) {
    to.setId(from.getId());
    to.setName(from.getName());
    to.setCategory(from.getCategory());
    to.setImage(from.getImage());
    to.setGender(from.getGender());
    to.setRating(from.getRating());
  }

  private boolean checkFilter(
      ProductModel productModel,
      String value,
      String[] columns) {
    for (String column : columns) {
      switch (column.toLowerCase()) {
        case "id" -> {
          if (Integer.parseInt(value) == productModel.getId()) {
            return true;
          }
        }
        case "name" -> {
          if (value.equalsIgnoreCase(productModel.getName())) {
            return true;
          }
        }
        case "category" -> {
          if (value.equalsIgnoreCase(productModel.getCategory())) {
            return true;
          }
        }
        case "gender" -> {
          if (value.equalsIgnoreCase(productModel.getGender())) {
            return true;
          }
        }
        case "rating" -> {
          if (Float.parseFloat(value) == productModel.getRating()) {
            return true;
          }
        }
        default -> {
          if (checkAllColumns(productModel, value)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private boolean checkAllColumns(ProductModel productModel, String value) {
    return (productModel.getId() == Integer.parseInt(value) ||
        productModel.getName().contains(value) ||
        productModel.getCategory().contains(value) ||
        productModel.getGender().contains(value) ||
        productModel.getRating() == Float.parseFloat(value));
  }

  @Override
  public int addModel(ProductModel productModel) {
    if (productModel == null ||
        productModel.getName().isEmpty() || productModel.getName() == null ||
        productModel.getCategory().isEmpty() || productModel.getCategory() == null ||
        productModel.getGender().isEmpty() || productModel.getGender() == null ||
        productModel.getImage().isEmpty()) {
      throw new IllegalArgumentException("There may be empty required fields! Please check again!");
    }
    int id = ProductDAO.getInstance().insert(mapToEntity(productModel));
    productList.add(productModel);
    return id;
  }

  @Override
  public int updateModel(ProductModel productModel) {
    int updatedRows = ProductDAO.getInstance().update(productModel);
    if (updatedRows > 0) {
      int index = productList.indexOf(productModel);
      if (index != -1) {
        productList.set(index, productModel);
      }
    }
    return updatedRows;
  }

  @Override
  public int deleteModel(int id) {
    ProductModel productModel = getModelById(id);
    if (productModel == null) {
      throw new IllegalArgumentException(
          "Product with ID: " + id + " doesn't exist.");
    }
    int deletedRows = ProductDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      productList.remove(productModel);
    }
    return deletedRows;
  }

  @Override
  public List<ProductModel> searchModel(String value, String[] columns) {
    List<ProductModel> results = new ArrayList<>();
    List<ProductModel> entities = ProductDAO.getInstance().search(value, columns);
    for (ProductModel entity : entities) {
      ProductModel model = mapToEntity(entity);
      if (checkFilter(model, value, columns)) {
        results.add(model);
      }
    }
    return results;
  }

  public boolean checkForDuplicate(List<String> values, String[] columns) {
    Optional<ProductModel> optionalProduct = ProductBUS.getInstance().getAllModels().stream().filter(product -> {
      for (String value : values) {
        if (Arrays.asList(columns).contains("name") &&
            !value.isEmpty() &&
            product.getName().equals(value)) {
          return true;
        }
        if (Arrays.asList(columns).contains("image") &&
            product.getImage().equals(value)) {
          return true;
        }
      }
      return false;
    })
        .findFirst();
    return optionalProduct.isPresent();
  }

}
package com.clothingstore.bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clothingstore.dao.ImportItemsDAO;
import com.clothingstore.interfaces.IBUS;
import com.clothingstore.models.ImportItemsModel;

public class ImportItemsBUS implements IBUS<ImportItemsModel> {
  private final List<ImportItemsModel> importItemsList = new ArrayList<>();
  private static ImportItemsBUS instance;

  public static ImportItemsBUS getInstance() {
    if (instance == null) {
      instance = new ImportItemsBUS();
    }
    return instance;
  }

  private ImportItemsBUS() {
    this.importItemsList.addAll(ImportItemsDAO.getInstance().readDatabase());
  }

  @Override
  public List<ImportItemsModel> getAllModels() {
    return Collections.unmodifiableList(importItemsList);
  }

  @Override
  public void refreshData() {
    importItemsList.clear();
    importItemsList.addAll(ImportItemsDAO.getInstance().readDatabase());
  }

  @Override
  public ImportItemsModel getModelById(int id) {
    refreshData();
    for (ImportItemsModel importItems : importItemsList) {
      if (importItems.getId() == id) {
        return importItems;
      }
    }
    return null;
  }

  private ImportItemsModel mapToEntity(ImportItemsModel from) {
    ImportItemsModel to = new ImportItemsModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(ImportItemsModel from, ImportItemsModel to) {
    to.setId(from.getId());
    to.setImport_id(from.getImport_id());
    to.setProduct_id(from.getProduct_id());
    to.setSize_id(from.getSize_id());
    to.setQuantity(from.getQuantity());
    to.setPrice(from.getPrice());
  }

  @Override
  public int addModel(ImportItemsModel model) {
    if (model == null ||
        model.getImport_id() <= 0 ||
        model.getProduct_id() <= 0 ||
        model.getSize_id() <= 0 ||
        model.getQuantity() <= 0 ||
        model.getPrice() <= 0) {
      throw new IllegalArgumentException(
          "There may be errors in required fields, please check your input and try again.");
    }
    int id = ImportItemsDAO.getInstance().insert(mapToEntity(model));
    importItemsList.add(model);
    return id;
  }

  @Override
  public int updateModel(ImportItemsModel model) {
    int updatedRows = ImportItemsDAO.getInstance().update(model);
    if (updatedRows > 0) {
      int index = importItemsList.indexOf(model);
      if (index != -1) {
        importItemsList.set(index, model);
      }
    }
    return updatedRows;
  }

  @Override
  public int deleteModel(int id) {
    ImportItemsModel ImportItemsModel = getModelById(id);
    if (ImportItemsModel == null) {
      throw new IllegalArgumentException(
          "ImportItems with ID: " + id + " doesn't exist.");
    }
    int deletedRows = ImportItemsDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      importItemsList.remove(ImportItemsModel);
    }
    return deletedRows;
  }

  private boolean checkFilter(
      ImportItemsModel ImportItemsModel,
      String value,
      String[] columns) {
    for (String column : columns) {
      switch (column.toLowerCase()) {
        case "id" -> {
          if (Integer.parseInt(value) == ImportItemsModel.getId()) {
            return true;
          }
        }
        case "import_id" -> {
          if (Integer.parseInt(value) == ImportItemsModel.getImport_id()) {
            return true;
          }
        }
        case "product_id" -> {
          if (Integer.parseInt(value) == ImportItemsModel.getProduct_id()) {
            return true;
          }
        }
        case "size_id" -> {
          if (Integer.parseInt(value) == ImportItemsModel.getSize_id()) {
            return true;
          }
        }
        case "quantity" -> {
          if (Integer.parseInt(value) == ImportItemsModel.getQuantity()) {
            return true;
          }
        }
        case "price" -> {
          if (Double.parseDouble(value) == ImportItemsModel.getProduct_id()) {
            return true;
          }
        }
        default -> {
          if (checkAllColumns(ImportItemsModel, value)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private boolean checkAllColumns(ImportItemsModel importItemsModel, String value) {
    return (importItemsModel.getId() == Integer.parseInt(value) ||
        importItemsModel.getImport_id() == Integer.parseInt(value) ||
        importItemsModel.getProduct_id() == Integer.parseInt(value) ||
        importItemsModel.getSize_id() == Integer.parseInt(value) ||
        importItemsModel.getQuantity() == Integer.parseInt(value) ||
        importItemsModel.getPrice() == Double.parseDouble(value));
  }

  @Override
  public List<ImportItemsModel> searchModel(String value, String[] columns) {
    List<ImportItemsModel> results = new ArrayList<>();
    List<ImportItemsModel> entities = ImportItemsDAO.getInstance().search(value, columns);
    for (ImportItemsModel entity : entities) {
      ImportItemsModel model = mapToEntity(entity);
      if (checkFilter(model, value, columns)) {
        results.add(model);
      }
    }
    return results;
  }
}

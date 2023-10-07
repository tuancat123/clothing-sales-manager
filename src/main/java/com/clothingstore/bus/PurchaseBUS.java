package com.clothingstore.bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clothingstore.dao.PurchaseDAO;
import com.clothingstore.interfaces.IBUS;
import com.clothingstore.models.PurchaseModel;

public class PurchaseBUS implements IBUS<PurchaseModel> {
  private final List<PurchaseModel> purchaseList = new ArrayList<>();
  private static PurchaseBUS instance;

  public static PurchaseBUS getInstance() {
    if (instance == null) {
      instance = new PurchaseBUS();
    }
    return instance;
  }

  private PurchaseBUS() {
    this.purchaseList.addAll(PurchaseDAO.getInstance().readDatabase());
  }

  @Override
  public List<PurchaseModel> getAllModels() {
    return Collections.unmodifiableList(purchaseList);
  }

  @Override
  public void refreshData() {
    purchaseList.clear();
    purchaseList.addAll(PurchaseDAO.getInstance().readDatabase());
  }

  @Override
  public PurchaseModel getModelById(int id) {
    refreshData();
    for (PurchaseModel purchaseModel : purchaseList) {
      if (purchaseModel.getId() == id) {
        return purchaseModel;
      }
    }
    return null;
  }

  private PurchaseModel mapToEntity(PurchaseModel from) {
    PurchaseModel to = new PurchaseModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(PurchaseModel from, PurchaseModel to) {
    to.setCustomerId(from.getCustomerId());
    to.setPurchaseDate(from.getPurchaseDate());
    to.setTotalAmount(from.getTotalAmount());
  }

  @Override
  public int addModel(PurchaseModel purchaseModel) {
    if (purchaseModel == null || purchaseModel.getCustomerId() <= 0 || purchaseModel.getTotalAmount() <= 0) {
      throw new IllegalArgumentException("Invalid purchase information. Please check and try again.");
    }

    int id = PurchaseDAO.getInstance().insert(mapToEntity(purchaseModel));
    purchaseModel.setId(id);
    purchaseList.add(purchaseModel);
    return id;
  }

  @Override
  public int updateModel(PurchaseModel purchaseModel) {
    int updatedRows = PurchaseDAO.getInstance().update(purchaseModel);
    if (updatedRows > 0) {
      int index = purchaseList.indexOf(purchaseModel);
      if (index != -1) {
        purchaseList.set(index, purchaseModel);
      }
    }
    return updatedRows;
  }

  @Override
  public int deleteModel(int id) {
    PurchaseModel purchaseModel = getModelById(id);
    if (purchaseModel == null) {
      throw new IllegalArgumentException("Purchase with ID: " + id + " doesn't exist.");
    }
    int deletedRows = PurchaseDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      purchaseList.remove(purchaseModel);
    }
    return deletedRows;
  }

  @Override
  public List<PurchaseModel> searchModel(String value, String[] columns) {
    List<PurchaseModel> results = new ArrayList<>();
    List<PurchaseModel> entities = PurchaseDAO.getInstance().search(value, columns);
    for (PurchaseModel entity : entities) {
      PurchaseModel model = mapToEntity(entity);
      results.add(model);
    }
    return results;
  }
}

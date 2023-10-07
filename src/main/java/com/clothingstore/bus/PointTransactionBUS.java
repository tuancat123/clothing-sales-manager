package com.clothingstore.bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clothingstore.dao.PointTransactionDAO;
import com.clothingstore.interfaces.IBUS;
import com.clothingstore.models.PointTransactionModel;

public class PointTransactionBUS implements IBUS<PointTransactionModel> {
  private final List<PointTransactionModel> pointTransactionList = new ArrayList<>();
  private static PointTransactionBUS instance;

  public static PointTransactionBUS getInstance() {
    if (instance == null) {
      instance = new PointTransactionBUS();
    }
    return instance;
  }

  private PointTransactionBUS() {
    this.pointTransactionList.addAll(PointTransactionDAO.getInstance().readDatabase());
  }

  @Override
  public List<PointTransactionModel> getAllModels() {
    return Collections.unmodifiableList(pointTransactionList);
  }

  @Override
  public void refreshData() {
    pointTransactionList.clear();
    pointTransactionList.addAll(PointTransactionDAO.getInstance().readDatabase());
  }

  @Override
  public PointTransactionModel getModelById(int id) {
    refreshData();
    for (PointTransactionModel pointTransaction : pointTransactionList) {
      if (pointTransaction.getId() == id) {
        return pointTransaction;
      }
    }
    return null;
  }

  private PointTransactionModel mapToEntity(PointTransactionModel from) {
    PointTransactionModel to = new PointTransactionModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(PointTransactionModel from, PointTransactionModel to) {
    to.setCustomerId(from.getCustomerId());
    to.setTransactionDate(from.getTransactionDate());
    to.setPointsEarned(from.getPointsEarned());
    to.setPointsUsed(from.getPointsUsed());
  }

  @Override
  public int addModel(PointTransactionModel pointTransactionModel) {
    if (pointTransactionModel == null || pointTransactionModel.getCustomerId() < 1) {
      throw new IllegalArgumentException("Invalid customer ID.");
    }

    int id = PointTransactionDAO.getInstance().insert(mapToEntity(pointTransactionModel));
    pointTransactionModel.setId(id);
    pointTransactionList.add(pointTransactionModel);
    return id;
  }

  @Override
  public int updateModel(PointTransactionModel pointTransactionModel) {
    int updatedRows = PointTransactionDAO.getInstance().update(pointTransactionModel);
    if (updatedRows > 0) {
      int index = pointTransactionList.indexOf(pointTransactionModel);
      if (index != -1) {
        pointTransactionList.set(index, pointTransactionModel);
      }
    }
    return updatedRows;
  }

  @Override
  public int deleteModel(int id) {
    PointTransactionModel pointTransactionModel = getModelById(id);
    if (pointTransactionModel == null) {
      throw new IllegalArgumentException("Point transaction with ID: " + id + " doesn't exist.");
    }
    int deletedRows = PointTransactionDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      pointTransactionList.remove(pointTransactionModel);
    }
    return deletedRows;
  }

  @Override
  public List<PointTransactionModel> searchModel(String value, String[] columns) {
    List<PointTransactionModel> results = new ArrayList<>();
    List<PointTransactionModel> entities = PointTransactionDAO.getInstance().search(value, columns);
    for (PointTransactionModel entity : entities) {
      PointTransactionModel model = mapToEntity(entity);
      results.add(model);
    }
    return results;
  }
}

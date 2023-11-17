package com.clothingstore.bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clothingstore.dao.PointDAO;
import com.clothingstore.interfaces.IBUS;
import com.clothingstore.models.PointModel;

public class PointBUS implements IBUS<PointModel> {
  private final List<PointModel> pointsList = new ArrayList<>();
  private static PointBUS instance;

  public static PointBUS getInstance() {
    if (instance == null) {
      instance = new PointBUS();
    }
    return instance;
  }

  private PointBUS() {
    this.pointsList.addAll(PointDAO.getInstance().readDatabase());
  }

  @Override
  public List<PointModel> getAllModels() {
    return Collections.unmodifiableList(pointsList);
  }

  @Override
  public void refreshData() {
    pointsList.clear();
    pointsList.addAll(PointDAO.getInstance().readDatabase());
  }

  @Override
  public PointModel getModelById(int id) {
    refreshData();
    for (PointModel pointModel : pointsList) {
      if (pointModel.getId() == id) {
        return pointModel;
      }
    }
    return null;
  }

  private PointModel mapToEntity(PointModel from) {
    PointModel to = new PointModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(PointModel from, PointModel to) {
    to.setCustomerId(from.getCustomerId());
    to.setPointsEarned(from.getPointsEarned());
    to.setPointsUsed(from.getPointsUsed());
  }

  @Override
  public int addModel(PointModel pointModel) {
    if (pointModel.getCustomerId() <= 0) {
      throw new IllegalArgumentException("Invalid customer ID.");
    }

    int id = PointDAO.getInstance().insert(mapToEntity(pointModel));
    pointModel.setId(id);
    pointsList.add(pointModel);
    return id;
  }

  @Override
  public int updateModel(PointModel pointModel) {
    int updatedRows = PointDAO.getInstance().update(pointModel);
    if (updatedRows > 0) {
      int index = pointsList.indexOf(pointModel);
      if (index != -1) {
        pointsList.set(index, pointModel);
      }
    }
    return updatedRows;
  }

  @Override
  public int deleteModel(int id) {
    PointModel pointModel = getModelById(id);
    if (pointModel == null) {
      throw new IllegalArgumentException("Points with ID: " + id + " doesn't exist.");
    }
    int deletedRows = PointDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      pointsList.remove(pointModel);
    }
    return deletedRows;
  }

  private boolean checkFilter(
      PointModel pointModel,
      String value,
      String[] columns) {
    for (String column : columns) {
      switch (column.toLowerCase()) {
        case "id" -> {
          if (Integer.parseInt(value) == pointModel.getId()) {
            return true;
          }
        }
        case "customer_id" -> {
          if (Integer.parseInt(value) == pointModel.getCustomerId()) {
            return true;
          }
        }
        case "points_earned" -> {
          if (Integer.parseInt(value) == pointModel.getPointsEarned()) {
            return true;
          }
        }
        case "points_used" -> {
          if (Integer.parseInt(value) == pointModel.getPointsUsed()) {
            return true;
          }
        }
        default -> {
          if (checkAllColumns(pointModel, value)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private boolean checkAllColumns(PointModel pointModel, String value) {
    return (pointModel.getId() == Integer.parseInt(value) ||
        pointModel.getCustomerId() == Integer.parseInt(value) ||
        pointModel.getPointsEarned() == Integer.parseInt(value) ||
        pointModel.getPointsUsed() == Integer.parseInt(value));
  }

  @Override
  public List<PointModel> searchModel(String value, String[] columns) {
    List<PointModel> results = new ArrayList<>();
    List<PointModel> entities = PointDAO.getInstance().search(value, columns);
    for (PointModel entity : entities) {
      PointModel model = mapToEntity(entity);
      if (checkFilter(model, value, columns)) {
        results.add(model);
      }
    }
    return results;
  }
}

package com.clothingstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clothingstore.interfaces.IDAO;
import com.clothingstore.models.PointModel;

public class PointDAO implements IDAO<PointModel> {

  private static PointDAO instance;

  public static PointDAO getInstance() {
    if (instance == null) {
      instance = new PointDAO();
    }
    return instance;
  }

  private static PointModel createPointModelFromResultSet(ResultSet rs) throws SQLException {
    int id = rs.getInt("id");
    int customerId = rs.getInt("customer_id");
    int pointsEarned = rs.getInt("points_earned");
    int pointsUsed = rs.getInt("points_used");
    return new PointModel(id, customerId, pointsEarned, pointsUsed);
  }

  @Override
  public ArrayList<PointModel> readDatabase() {
    ArrayList<PointModel> pointsList = new ArrayList<>();
    try (
        ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM points")) {
      while (rs.next()) {
        PointModel pointModel = createPointModelFromResultSet(rs);
        pointsList.add(pointModel);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return pointsList;
  }

  @Override
  public int insert(PointModel pointModel) {
    String insertSql = "INSERT INTO points (customer_id, points_earned, points_used) VALUES (?, ?, ?)";
    Object[] args = {
        pointModel.getCustomerId(),
        pointModel.getPointsEarned(),
        pointModel.getPointsUsed()
    };
    try {
      return DatabaseConnection.executeUpdate(insertSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int update(PointModel pointModel) {
    String updateSql = "UPDATE points SET customer_id = ?, points_earned = ?, points_used = ? WHERE id = ?";
    Object[] args = {
        pointModel.getCustomerId(),
        pointModel.getPointsEarned(),
        pointModel.getPointsUsed(),
        pointModel.getId()
    };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int delete(int id) {
    String deleteSql = "DELETE FROM points WHERE id = ?";
    Object[] args = { id };
    try {
      return DatabaseConnection.executeUpdate(deleteSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public List<PointModel> search(String condition, String[] columnNames) {
    try {
      if (condition == null || condition.trim().isEmpty()) {
        throw new IllegalArgumentException("Search condition cannot be empty or null");
      }

      String query;
      if (columnNames == null || columnNames.length == 0) {
        // Search all columns
        query = "SELECT * FROM points WHERE CONCAT(id, customer_id, points_earned, points_used) LIKE ?";
      } else {
        // Search specific columns in points table
        query = "SELECT * FROM points WHERE CONCAT(" +
            String.join(", ", columnNames) +
            ") LIKE ?";
      }

      try (
          PreparedStatement pst = DatabaseConnection.getPreparedStatement(
              query,
              "%" + condition + "%")) {
        try (ResultSet rs = pst.executeQuery()) {
          List<PointModel> pointsList = new ArrayList<>();
          while (rs.next()) {
            PointModel pointModel = createPointModelFromResultSet(rs);
            pointsList.add(pointModel);
          }

          if (pointsList.isEmpty()) {
            throw new SQLException("No records found for the given condition: " + condition);
          }

          return pointsList;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

  public PointModel getPointsById(int id) {
    String query = "SELECT * FROM points WHERE id = ?";
    Object[] args = { id };
    try (
        PreparedStatement pst = DatabaseConnection.getPreparedStatement(
            query,
            args);
        ResultSet rs = pst.executeQuery()) {
      if (rs.next()) {
        return createPointModelFromResultSet(rs);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}

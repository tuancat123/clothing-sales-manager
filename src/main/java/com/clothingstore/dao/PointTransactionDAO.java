package com.clothingstore.dao;

import com.clothingstore.interfaces.IDAO;
import com.clothingstore.models.PointTransactionModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.sql.Timestamp;

public class PointTransactionDAO implements IDAO<PointTransactionModel> {
  private static PointTransactionDAO instance;

  public static PointTransactionDAO getInstance() {
    if (instance == null) {
      instance = new PointTransactionDAO();
    }
    return instance;
  }

  private static PointTransactionModel createPointTransactionModelFromResultSet(ResultSet rs) throws SQLException {
    int id = rs.getInt("id");
    int customerId = rs.getInt("customer_id");
    Timestamp transactionDate = rs.getTimestamp("transaction_date");
    int pointsEarned = rs.getInt("points_earned");
    int pointsUsed = rs.getInt("points_used");
    return new PointTransactionModel(id, customerId, transactionDate, pointsEarned, pointsUsed);
  }

  @Override
  public ArrayList<PointTransactionModel> readDatabase() {
    ArrayList<PointTransactionModel> pointTransactionList = new ArrayList<>();
    try (
        ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM point_transactions")) {
      while (rs.next()) {
        PointTransactionModel pointTransactionModel = createPointTransactionModelFromResultSet(rs);
        pointTransactionList.add(pointTransactionModel);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return pointTransactionList;
  }

  @Override
  public int insert(PointTransactionModel pointTransactionModel) {
    String insertSql = "INSERT INTO point_transactions (customer_id, transaction_date, points_earned, points_used) VALUES (?, ?, ?, ?)";
    Object[] args = {
        pointTransactionModel.getCustomerId(),
        pointTransactionModel.getTransactionDate(),
        pointTransactionModel.getPointsEarned(),
        pointTransactionModel.getPointsUsed()
    };
    try {
      return DatabaseConnection.executeUpdate(insertSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int update(PointTransactionModel pointTransactionModel) {
    String updateSql = "UPDATE point_transactions SET customer_id = ?, transaction_date = ?, points_earned = ?, points_used = ? WHERE id = ?";
    Object[] args = {
        pointTransactionModel.getCustomerId(),
        pointTransactionModel.getTransactionDate(),
        pointTransactionModel.getPointsEarned(),
        pointTransactionModel.getPointsUsed(),
        pointTransactionModel.getId()
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
    String deleteSql = "DELETE FROM point_transactions WHERE id = ?";
    Object[] args = { id };
    try {
      return DatabaseConnection.executeUpdate(deleteSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public List<PointTransactionModel> search(String condition, String[] columnNames) {
    try {
      if (condition == null || condition.trim().isEmpty()) {
        throw new IllegalArgumentException(
            "Search condition cannot be empty or null");
      }

      String query;
      if (columnNames == null || columnNames.length == 0) {
        // Search all columns
        query = "SELECT * FROM point_transactions WHERE CONCAT(id, customer_id, transaction_date, points_earned, points_used) LIKE ?";
      } else if (columnNames.length == 1) {
        // Search specific column in point_transactions table
        String column = columnNames[0];
        query = "SELECT * FROM point_transactions WHERE " + column + " LIKE ?";
      } else {
        // Search specific columns in point_transactions table
        query = "SELECT id, customer_id, transaction_date, points_earned, points_used FROM point_transactions WHERE CONCAT("
            +
            String.join(", ", columnNames) +
            ") LIKE ?";
      }

      try (
          PreparedStatement pst = DatabaseConnection.getPreparedStatement(
              query,
              "%" + condition + "%")) {
        try (ResultSet rs = pst.executeQuery()) {
          List<PointTransactionModel> pointTransactionList = new ArrayList<>();
          while (rs.next()) {
            PointTransactionModel pointTransactionModel = createPointTransactionModelFromResultSet(rs);
            pointTransactionList.add(pointTransactionModel);
          }

          if (pointTransactionList.isEmpty()) {
            throw new SQLException(
                "No records found for the given condition: " + condition);
          }

          return pointTransactionList;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }
}

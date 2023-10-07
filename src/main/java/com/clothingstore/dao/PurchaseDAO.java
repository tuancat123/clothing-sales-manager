package com.clothingstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clothingstore.interfaces.IDAO;
import com.clothingstore.models.PurchaseModel;

public class PurchaseDAO implements IDAO<PurchaseModel> {

  private static PurchaseDAO instance;

  public static PurchaseDAO getInstance() {
    if (instance == null) {
      instance = new PurchaseDAO();
    }
    return instance;
  }

  private static PurchaseModel createPurchaseModelFromResultSet(ResultSet rs) throws SQLException {
    int id = rs.getInt("id");
    int customerId = rs.getInt("customer_id");
    Timestamp purchaseDate = rs.getTimestamp("purchase_date");
    double totalAmount = rs.getDouble("total_amount");
    return new PurchaseModel(id, customerId, purchaseDate, totalAmount);
  }

  @Override
  public ArrayList<PurchaseModel> readDatabase() {
    ArrayList<PurchaseModel> purchaseList = new ArrayList<>();
    try (
        ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM purchases")) {
      while (rs.next()) {
        PurchaseModel purchaseModel = createPurchaseModelFromResultSet(rs);
        purchaseList.add(purchaseModel);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return purchaseList;
  }

  @Override
  public int insert(PurchaseModel purchaseModel) {
    String insertSql = "INSERT INTO purchases (customer_id, purchase_date, total_amount) VALUES (?, ?, ?)";
    Object[] args = {
        purchaseModel.getCustomerId(),
        purchaseModel.getPurchaseDate(),
        purchaseModel.getTotalAmount()
    };
    try {
      return DatabaseConnection.executeUpdate(insertSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int update(PurchaseModel purchaseModel) {
    String updateSql = "UPDATE purchases SET customer_id = ?, purchase_date = ?, total_amount = ? WHERE id = ?";
    Object[] args = {
        purchaseModel.getCustomerId(),
        purchaseModel.getPurchaseDate(),
        purchaseModel.getTotalAmount(),
        purchaseModel.getId()
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
    String deleteSql = "DELETE FROM purchases WHERE id = ?";
    Object[] args = { id };
    try {
      return DatabaseConnection.executeUpdate(deleteSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public List<PurchaseModel> search(String condition, String[] columnNames) {
    try {
      if (condition == null || condition.trim().isEmpty()) {
        throw new IllegalArgumentException("Search condition cannot be empty or null");
      }

      String query;
      if (columnNames == null || columnNames.length == 0) {
        // Search all columns
        query = "SELECT * FROM purchases WHERE CONCAT(id, customer_id, purchase_date, total_amount) LIKE ?";
      } else if (columnNames.length == 1) {
        // Search specific column in purchases table
        String column = columnNames[0];
        query = "SELECT * FROM purchases WHERE " + column + " LIKE ?";
      } else {
        // Search specific columns in purchases table
        query = "SELECT id, customer_id, purchase_date, total_amount FROM purchases WHERE CONCAT("
            +
            String.join(", ", columnNames) +
            ") LIKE ?";
      }

      try (
          PreparedStatement pst = DatabaseConnection.getPreparedStatement(
              query,
              "%" + condition + "%")) {
        try (ResultSet rs = pst.executeQuery()) {
          List<PurchaseModel> purchaseList = new ArrayList<>();
          while (rs.next()) {
            PurchaseModel purchaseModel = createPurchaseModelFromResultSet(rs);
            purchaseList.add(purchaseModel);
          }

          if (purchaseList.isEmpty()) {
            throw new SQLException("No records found for the given condition: " + condition);
          }

          return purchaseList;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }
}

package com.clothingstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clothingstore.interfaces.IDAO;
import com.clothingstore.models.ImportItemsModel;

public class ImportItemsDAO implements IDAO<ImportItemsModel> {
  private static ImportItemsDAO instance;

  public static ImportItemsDAO getInstance() {
    if (instance == null) {
      instance = new ImportItemsDAO();
    }
    return instance;
  }

  private static ImportItemsModel createImportItemsModelFromResultSet(ResultSet rs) throws SQLException {
    int id = rs.getInt("id");
    int importId = rs.getInt("import_id");
    int productId = rs.getInt("product_id");
    int quantity = rs.getInt("quantity");
    double price = rs.getDouble("price");
    return new ImportItemsModel(id, importId, productId, quantity, price);
  }

  @Override
  public ArrayList<ImportItemsModel> readDatabase() {
    ArrayList<ImportItemsModel> ImportItemsList = new ArrayList<>();
    try (
        ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM import_items")) {
      while (rs.next()) {
        ImportItemsModel ImportItemsModel = createImportItemsModelFromResultSet(rs);
        ImportItemsList.add(ImportItemsModel);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return ImportItemsList;
  }

  @Override
  public int insert(ImportItemsModel ImportItemsModel) {
    String insertSql = "INSERT INTO import_items (import_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
    Object[] args = {
        ImportItemsModel.getImport_id(),
        ImportItemsModel.getProduct_id(),
        ImportItemsModel.getQuantity(),
        ImportItemsModel.getPrice()
    };
    try {
      return DatabaseConnection.executeUpdate(insertSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int update(ImportItemsModel ImportItemsModel) {
    String updateSql = "UPDATE import_items SET import_id = ?, product_id = ?, quantity = ?, price = ? WHERE id = ?";
    Object[] args = {
        ImportItemsModel.getImport_id(),
        ImportItemsModel.getProduct_id(),
        ImportItemsModel.getQuantity(),
        ImportItemsModel.getPrice(),
        ImportItemsModel.getId()
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
    String deleteSql = "DELETE FROM import_items WHERE id = ?";
    Object[] args = { id };
    try {
      return DatabaseConnection.executeUpdate(deleteSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public List<ImportItemsModel> search(String condition, String[] columnNames) {
    if (condition == null || condition.trim().isEmpty()) {
      throw new IllegalArgumentException("Search condition cannot be empty or null");
    }

    String query;
    if (columnNames == null || columnNames.length == 0) {
      query = "SELECT * FROM import_items WHERE CONCAT(id, import_id, product_id, quantity, price) LIKE ?";
    } else if (columnNames.length == 1) {
      String column = columnNames[0];
      query = "SELECT * FROM import_items WHERE " + column + " LIKE ?";
    } else {
      String columns = String.join(",", columnNames);
      query = "SELECT id, import_id, product_id, quantity, price FROM import_items WHERE CONCAT(" + columns
          + ") LIKE ?";
    }

    try (PreparedStatement pst = DatabaseConnection.getPreparedStatement(query, "%" + condition + "%")) {
      try (ResultSet rs = pst.executeQuery()) {
        List<ImportItemsModel> importItemsList = new ArrayList<>();
        while (rs.next()) {
          ImportItemsModel ImportItemsModel = createImportItemsModelFromResultSet(rs);
          importItemsList.add(ImportItemsModel);
        }
        if (importItemsList.isEmpty()) {
          throw new SQLException("No records found for the given condition: " + condition);
        }
        return importItemsList;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }
}

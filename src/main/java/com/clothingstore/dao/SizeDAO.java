package com.clothingstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clothingstore.interfaces.IDAO;
import com.clothingstore.models.SizeModel;

public class SizeDAO implements IDAO<SizeModel> {

  private static SizeDAO instance;

  public static SizeDAO getInstance() {
    if (instance == null) {
      instance = new SizeDAO();
    }
    return instance;
  }

  private static SizeModel createSizeModelFromResultSet(ResultSet rs) throws SQLException {
    int id = rs.getInt("id");
    String size = rs.getString("name");
    return new SizeModel(id, size);
  }

  @Override
  public ArrayList<SizeModel> readDatabase() {
    ArrayList<SizeModel> sizeList = new ArrayList<>();
    try (
        ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM sizes")) {
      while (rs.next()) {
        SizeModel sizeModel = createSizeModelFromResultSet(rs);
        sizeList.add(sizeModel);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return sizeList;
  }

  @Override
  public int insert(SizeModel sizeModel) {
    String insertSql = "INSERT INTO sizes (name) VALUES (?)";
    Object[] args = {
        sizeModel.getSize()
    };
    try {
      return DatabaseConnection.executeUpdate(insertSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int update(SizeModel sizeModel) {
    String updateSql = "UPDATE sizes SET name = ? WHERE id = ?";
    Object[] args = {
        sizeModel.getSize(),
        sizeModel.getId()
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
    String deleteSql = "DELETE FROM sizes WHERE id = ?";
    Object[] args = { id };
    try {
      return DatabaseConnection.executeUpdate(deleteSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public List<SizeModel> search(String condition, String[] columnNames) {
    try {
      if (condition == null || condition.trim().isEmpty()) {
        throw new IllegalArgumentException(
            "Search condition cannot be empty or null");
      }

      String query;
      if (columnNames == null || columnNames.length == 0) {
        // Search all columns
        query = "SELECT * FROM sizes WHERE CONCAT(id, name) LIKE ?";
      } else if (columnNames.length == 1) {
        // Search specific column in sizes table
        String column = columnNames[0];
        query = "SELECT * FROM sizes WHERE " + column + " LIKE ?";
      } else {
        // Search specific columns in sizes table
        query = "SELECT id, name FROM sizes WHERE CONCAT("
            +
            String.join(", ", columnNames) +
            ") LIKE ?";
      }

      try (
          PreparedStatement pst = DatabaseConnection.getPreparedStatement(
              query,
              "%" + condition + "%")) {
        try (ResultSet rs = pst.executeQuery()) {
          List<SizeModel> sizeList = new ArrayList<>();
          while (rs.next()) {
            SizeModel sizeModel = createSizeModelFromResultSet(rs);
            sizeList.add(sizeModel);
          }

          if (sizeList.isEmpty()) {
            throw new SQLException(
                "No records found for the given condition: " + condition);
          }

          return sizeList;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }
}

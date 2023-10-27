package com.clothingstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clothingstore.interfaces.IDAO;
import com.clothingstore.models.PermissionModel;

public class PermissionDAO implements IDAO<PermissionModel> {

  private static PermissionDAO instance;

  public static PermissionDAO getInstance() {
    if (instance == null) {
      instance = new PermissionDAO();
    }
    return instance;
  }

  private static PermissionModel createPermissionModelFromResultSet(ResultSet rs) throws SQLException {
    int id = rs.getInt("id");
    String permissionName = rs.getString("name");
    return new PermissionModel(id, permissionName);
  }

  @Override
  public ArrayList<PermissionModel> readDatabase() {
    ArrayList<PermissionModel> permissionList = new ArrayList<>();
    try (
        ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM permissions")) {
      while (rs.next()) {
        PermissionModel permissionModel = createPermissionModelFromResultSet(rs);
        permissionList.add(permissionModel);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return permissionList;
  }

  @Override
  public int insert(PermissionModel permission) {
    String insertSql = "INSERT INTO permissions (name) VALUES (?)";
    Object[] args = { permission.getPermissionName() };
    try {
      return DatabaseConnection.executeUpdate(insertSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int update(PermissionModel permission) {
    String updateSql = "UPDATE permissions SET name = ? WHERE id = ?";
    Object[] args = { permission.getPermissionName(), permission.getId() };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int delete(int id) {
    String deleteSql = "DELETE FROM permissions WHERE id = ?";
    Object[] args = { id };
    try {
      return DatabaseConnection.executeUpdate(deleteSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public List<PermissionModel> search(String condition, String[] columnNames) {
    try {
      if (condition == null || condition.trim().isEmpty()) {
        throw new IllegalArgumentException("Search condition cannot be empty or null");
      }

      String query;
      if (columnNames == null || columnNames.length == 0) {
        // Search all columns
        query = "SELECT * FROM permissions WHERE name LIKE ?";
      } else {
        // Search specific columns in permissions table
        query = "SELECT * FROM permissions WHERE CONCAT(" +
            String.join(", ", columnNames) +
            ") LIKE ?";
      }

      try (
          PreparedStatement pst = DatabaseConnection.getPreparedStatement(
              query,
              "%" + condition + "%")) {
        try (ResultSet rs = pst.executeQuery()) {
          List<PermissionModel> permissionList = new ArrayList<>();
          while (rs.next()) {
            PermissionModel permissionModel = createPermissionModelFromResultSet(rs);
            permissionList.add(permissionModel);
          }

          if (permissionList.isEmpty()) {
            throw new SQLException("No records found for the given condition: " + condition);
          }

          return permissionList;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

  public PermissionModel getPermissionById(int id) {
    String query = "SELECT * FROM permissions WHERE id = ?";
    Object[] args = { id };
    try (
        PreparedStatement pst = DatabaseConnection.getPreparedStatement(
            query,
            args);
        ResultSet rs = pst.executeQuery()) {
      if (rs.next()) {
        return createPermissionModelFromResultSet(rs);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}

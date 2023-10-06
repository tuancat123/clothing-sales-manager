package com.clothingstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clothingstore.interfaces.IDAO;
import com.clothingstore.models.RoleModel;

public class RoleDAO implements IDAO<RoleModel> {

  private static RoleDAO instance;

  public static RoleDAO getInstance() {
    if (instance == null) {
      instance = new RoleDAO();
    }
    return instance;
  }

  private static RoleModel createRoleModelFromResultSet(ResultSet rs) throws SQLException {
    int id = rs.getInt("id");
    String name = rs.getString("name");
    return new RoleModel(id, name);
  }

  @Override
  public ArrayList<RoleModel> readDatabase() {
    ArrayList<RoleModel> roleList = new ArrayList<>();
    try (
        ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM roles")) {
      while (rs.next()) {
        RoleModel roleModel = createRoleModelFromResultSet(rs);
        roleList.add(roleModel);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return roleList;
  }

  @Override
  public int insert(RoleModel role) {
    String insertSql = "INSERT INTO roles (name) VALUES (?)";
    Object[] args = { role.getName() };
    try {
      return DatabaseConnection.executeUpdate(insertSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int update(RoleModel role) {
    String updateSql = "UPDATE roles SET name = ? WHERE id = ?";
    Object[] args = { role.getName(), role.getId() };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int delete(int id) {
    String deleteSql = "DELETE FROM roles WHERE id = ?";
    Object[] args = { id };
    try {
      return DatabaseConnection.executeUpdate(deleteSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public List<RoleModel> search(String condition, String[] columnNames) {
    try {
      if (condition == null || condition.trim().isEmpty()) {
        throw new IllegalArgumentException("Search condition cannot be empty or null");
      }

      String query;
      if (columnNames == null || columnNames.length == 0) {
        // Search all columns
        query = "SELECT * FROM roles WHERE name LIKE ?";
      } else {
        // Search specific columns in roles table
        query = "SELECT * FROM roles WHERE CONCAT(" +
            String.join(", ", columnNames) +
            ") LIKE ?";
      }

      try (
          PreparedStatement pst = DatabaseConnection.getPreparedStatement(
              query,
              "%" + condition + "%")) {
        try (ResultSet rs = pst.executeQuery()) {
          List<RoleModel> roleList = new ArrayList<>();
          while (rs.next()) {
            RoleModel roleModel = createRoleModelFromResultSet(rs);
            roleList.add(roleModel);
          }

          if (roleList.isEmpty()) {
            throw new SQLException("No records found for the given condition: " + condition);
          }

          return roleList;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

  public RoleModel getRoleById(int id) {
    String query = "SELECT * FROM roles WHERE id = ?";
    Object[] args = { id };
    try (
        PreparedStatement pst = DatabaseConnection.getPreparedStatement(
            query,
            args);
        ResultSet rs = pst.executeQuery()) {
      if (rs.next()) {
        return createRoleModelFromResultSet(rs);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}

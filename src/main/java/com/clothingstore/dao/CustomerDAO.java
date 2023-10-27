package com.clothingstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clothingstore.interfaces.IDAO;
import com.clothingstore.models.CustomerModel;

public class CustomerDAO implements IDAO<CustomerModel> {

  private static CustomerDAO instance;

  public static CustomerDAO getInstance() {
    if (instance == null) {
      instance = new CustomerDAO();
    }
    return instance;
  }

  private static CustomerModel createCustomerModelFromResultSet(ResultSet rs) throws SQLException {
    int id = rs.getInt("id");
    String customerName = rs.getString("name");
    String phone = rs.getString("phone");
    String email = rs.getString("email");
    return new CustomerModel(id, customerName, phone, email);
  }

  @Override
  public ArrayList<CustomerModel> readDatabase() {
    ArrayList<CustomerModel> customerList = new ArrayList<>();
    try (
        ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM customers")) {
      while (rs.next()) {
        CustomerModel CustomerModel = createCustomerModelFromResultSet(rs);
        customerList.add(CustomerModel);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return customerList;
  }

  @Override
  public int insert(CustomerModel customerModel) {
    String insertSql = "INSERT INTO customers (name, phone, email) VALUES (?, ?, ?)";
    Object[] args = {
        customerModel.getCustomerName(),
        customerModel.getPhone(),
        customerModel.getEmail()
    };
    try {
      return DatabaseConnection.executeUpdate(insertSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int update(CustomerModel customerModel) {
    String updateSql = "UPDATE customers SET name = ?, phone = ?, email = ? WHERE id = ?";
    Object[] args = {
        customerModel.getCustomerName(),
        customerModel.getPhone(),
        customerModel.getEmail(),
        customerModel.getId()
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
    String updateStatusSql = "DELETE FROM customers WHERE id = ?";
    Object[] args = { id };
    try {
      return DatabaseConnection.executeUpdate(updateStatusSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public List<CustomerModel> search(String condition, String[] columnNames) {
    try {
      if (condition == null || condition.trim().isEmpty()) {
        throw new IllegalArgumentException(
            "Search condition cannot be empty or null");
      }

      String query;
      if (columnNames == null || columnNames.length == 0) {
        // Search all columns
        query = "SELECT * FROM customers WHERE CONCAT(id, name, phone, email) LIKE ?";
      } else if (columnNames.length == 1) {
        // Search specific column in customers table
        String column = columnNames[0];
        query = "SELECT * FROM customers WHERE " + column + " LIKE ?";
      } else {
        // Search specific columns in customers table
        query = "SELECT id, name, phone, email FROM customers WHERE CONCAT("
            +
            String.join(", ", columnNames) +
            ") LIKE ?";
      }

      try (
          PreparedStatement pst = DatabaseConnection.getPreparedStatement(
              query,
              "%" + condition + "%")) {
        try (ResultSet rs = pst.executeQuery()) {
          List<CustomerModel> userList = new ArrayList<>();
          while (rs.next()) {
            CustomerModel CustomerModel = createCustomerModelFromResultSet(rs);
            userList.add(CustomerModel);
          }

          if (userList.isEmpty()) {
            throw new SQLException(
                "No records found for the given condition: " + condition);
          }

          return userList;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

}

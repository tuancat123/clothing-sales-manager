package com.clothingstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clothingstore.interfaces.IDAO;
import com.clothingstore.models.OrderItemModel;

public class OrderItemDAO implements IDAO<OrderItemModel> {

  private static OrderItemDAO instance;

  public static OrderItemDAO getInstance() {
    if (instance == null) {
      instance = new OrderItemDAO();
    }
    return instance;
  }

  private static OrderItemModel createOrderItemModelFromResultSet(ResultSet rs)
      throws SQLException {
    int id = rs.getInt("id");
    int orderId = rs.getInt("order_id");
    int productId = rs.getInt("product_id");
    int sizeId = rs.getInt("size_id");
    int quantity = rs.getInt("quantity");
    int price = rs.getInt("price");
    return new OrderItemModel(id, orderId, productId, sizeId, quantity, price);
  }

  @Override
  public ArrayList<OrderItemModel> readDatabase() {
    ArrayList<OrderItemModel> orderItemList = new ArrayList<>();
    try (
        ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM order_items")) {
      while (rs.next()) {
        OrderItemModel orderItemModel = createOrderItemModelFromResultSet(rs);
        orderItemList.add(orderItemModel);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return orderItemList;
  }

  @Override
  public int insert(OrderItemModel orderItem) {
    String insertSql = "INSERT INTO order_items (order_id, product_id, size_id, quantity, price) VALUES (?, ?, ?, ?, ?)";
    Object[] args = {
        orderItem.getOrderId(),
        orderItem.getProductId(),
        orderItem.getSizeId(),
        orderItem.getQuantity(),
        orderItem.getPrice()
    };
    try {
      return DatabaseConnection.executeUpdate(insertSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int update(OrderItemModel orderItem) {
    String updateSql = "UPDATE order_items SET order_id = ?, product_id = ?, size_id = ?, quantity = ?, price = ? WHERE id = ?";
    Object[] args = {
        orderItem.getOrderId(),
        orderItem.getProductId(),
        orderItem.getSizeId(),
        orderItem.getQuantity(),
        orderItem.getPrice(),
        orderItem.getId()
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
    String deleteSql = "DELETE FROM order_items WHERE id = ?";
    Object[] args = { id };
    try {
      return DatabaseConnection.executeUpdate(deleteSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public List<OrderItemModel> search(String condition, String[] columnNames) {
    try {
      if (condition == null || condition.trim().isEmpty()) {
        throw new IllegalArgumentException(
            "Search condition cannot be empty or null");
      }

      String query;
      if (columnNames == null || columnNames.length == 0) {
        // Search all columns
        query = "SELECT * FROM order_items WHERE CONCAT(id, order_id, product_id, quantity, price) LIKE ?";
      } else if (columnNames.length == 1) {
        // Search specific column in order_items table
        String column = columnNames[0];
        query = "SELECT * FROM order_items WHERE " + column + " LIKE ?";
      } else {
        // Search specific columns in order_items table
        query = "SELECT id, order_id, product_id, quantity, price FROM order_items WHERE CONCAT("
            +
            String.join(", ", columnNames) +
            ") LIKE ?";
      }

      try (
          PreparedStatement pst = DatabaseConnection.getPreparedStatement(
              query,
              "%" + condition + "%")) {
        try (ResultSet rs = pst.executeQuery()) {
          List<OrderItemModel> orderItemList = new ArrayList<>();
          while (rs.next()) {
            OrderItemModel orderItemModel = createOrderItemModelFromResultSet(rs);
            orderItemList.add(orderItemModel);
          }

          if (orderItemList.isEmpty()) {
            throw new SQLException(
                "No records found for the given condition: " + condition);
          }

          return orderItemList;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

}

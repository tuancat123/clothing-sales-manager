package com.clothingstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clothingstore.interfaces.IDAO;
import com.clothingstore.models.OrderModel;

public class OrderDAO implements IDAO<OrderModel> {

    private static OrderDAO instance;

    public static OrderDAO getInstance() {
        if (instance == null) {
            instance = new OrderDAO();
        }
        return instance;
    }

    private static OrderModel createOrderModelFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int customerId = rs.getInt("customer_id");
        int userId = rs.getInt("user_id");
        Timestamp orderDate = rs.getTimestamp("order_date");
        int totalAmount = rs.getInt("total_amount");
        return new OrderModel(id, customerId, userId, orderDate, totalAmount);
    }

    @Override
    public ArrayList<OrderModel> readDatabase() {
        ArrayList<OrderModel> orderList = new ArrayList<>();
        try (
                ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM orders")) {
            while (rs.next()) {
                OrderModel orderModel = createOrderModelFromResultSet(rs);
                orderList.add(orderModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public int insert(OrderModel orderModel) {
        String insertSql = "INSERT INTO orders (customer_id, user_id, order_date, total_amount) VALUES (?, ?, ?, ?)";
        Object[] args = {
                orderModel.getCustomerId(),
                orderModel.getUserId(),
                orderModel.getOrderDate(),
                orderModel.getTotalAmount()
        };
        try {
            return DatabaseConnection.executeUpdate(insertSql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int update(OrderModel orderModel) {
        String updateSql = "UPDATE orders SET customer_id = ?, user_id = ?, order_date = ?, total_amount = ? WHERE id = ?";
        Object[] args = {
                orderModel.getCustomerId(),
                orderModel.getUserId(),
                orderModel.getOrderDate(),
                orderModel.getTotalAmount(),
                orderModel.getId()
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
        String deleteSql = "DELETE FROM orders WHERE id = ?";
        Object[] args = { id };
        try {
            return DatabaseConnection.executeUpdate(deleteSql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<OrderModel> search(String condition, String[] columnNames) {
        try {
            if (condition == null || condition.trim().isEmpty()) {
                throw new IllegalArgumentException("Search condition cannot be empty or null");
            }

            String query;
            if (columnNames == null || columnNames.length == 0) {
                // Search all columns
                query = "SELECT * FROM orders WHERE CONCAT(id, customer_id, user_id, order_date, total_amount) LIKE ?";
            } else if (columnNames.length == 1) {
                // Search specific column in orders table
                String column = columnNames[0];
                query = "SELECT * FROM orders WHERE " + column + " LIKE ?";
            } else {
                // Search specific columns in orders table
                query = "SELECT id, customer_id, user_id, order_date, total_amount FROM orders WHERE CONCAT(" +
                        String.join(", ", columnNames) + ") LIKE ?";
            }

            try (
                    PreparedStatement pst = DatabaseConnection.getPreparedStatement(query, "%" + condition + "%")) {
                try (ResultSet rs = pst.executeQuery()) {
                    List<OrderModel> orderList = new ArrayList<>();
                    while (rs.next()) {
                        OrderModel orderModel = createOrderModelFromResultSet(rs);
                        orderList.add(orderModel);
                    }

                    if (orderList.isEmpty()) {
                        throw new SQLException("No records found for the given condition: " + condition);
                    }

                    return orderList;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}

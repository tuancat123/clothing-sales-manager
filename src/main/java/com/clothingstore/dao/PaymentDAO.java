package com.clothingstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.clothingstore.interfaces.IDAO;
import com.clothingstore.models.PaymentModel;

public class PaymentDAO implements IDAO<PaymentModel> {

    private static PaymentDAO instance;

    public static PaymentDAO getInstance() {
        if (instance == null) {
            instance = new PaymentDAO();
        }
        return instance;
    }

    private static PaymentModel createPaymentModelFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int orderId = rs.getInt("order_id");
        int methodId = rs.getInt("method_id");
        double totalPrice = rs.getDouble("total_price");
        Timestamp paymentDate = rs.getTimestamp("payment_date");
        return new PaymentModel(id, orderId, methodId, paymentDate, totalPrice);
    }

    @Override
    public ArrayList<PaymentModel> readDatabase() {
        ArrayList<PaymentModel> paymentList = new ArrayList<>();
        try (
                ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM payments")) {
            while (rs.next()) {
                PaymentModel paymentModel = createPaymentModelFromResultSet(rs);
                paymentList.add(paymentModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paymentList;
    }

    @Override
    public int insert(PaymentModel paymentModel) {
        String insertSql = "INSERT INTO payments (order_id, method_id, total_price) VALUES (?, ?, ?)";
        Object[] args = {
                paymentModel.getOrderId(),
                paymentModel.getPaymentMethodId(),
                paymentModel.getTotalPrice()
        };
        try {
            return DatabaseConnection.executeUpdate(insertSql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int update(PaymentModel paymentModel) {
        String updateSql = "UPDATE payments SET order_id = ?, method_id = ?, total_price = ? WHERE id = ?";
        Object[] args = {
                paymentModel.getOrderId(),
                paymentModel.getPaymentMethodId(),
                paymentModel.getTotalPrice(),
                paymentModel.getId()
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
        String deleteSql = "DELETE FROM payments WHERE id = ?";
        Object[] args = { id };
        try {
            return DatabaseConnection.executeUpdate(deleteSql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<PaymentModel> search(String condition, String[] columnNames) {
        try {
            if (condition == null || condition.trim().isEmpty()) {
                throw new IllegalArgumentException("Search condition cannot be empty or null");
            }

            String query;
            if (columnNames == null || columnNames.length == 0) {
                query = "SELECT * FROM payments WHERE CONCAT(id, order_id, method_id, total_price) LIKE ?";
            } else if (columnNames.length == 1) {
                String column = columnNames[0];
                query = "SELECT * FROM payments WHERE " + column + " LIKE ?";
            } else {
                String columns = String.join(",", columnNames);
                query = "SELECT id, order_id, method_id, total_price FROM payments WHERE CONCAT(" + columns
                        + ") LIKE ?";
            }

            try (
                    PreparedStatement pst = DatabaseConnection.getPreparedStatement(query, "%" + condition + "%")) {
                try (ResultSet rs = pst.executeQuery()) {
                    List<PaymentModel> paymentList = new ArrayList<>();
                    while (rs.next()) {
                        PaymentModel paymentModel = createPaymentModelFromResultSet(rs);
                        paymentList.add(paymentModel);
                    }

                    if (paymentList.isEmpty()) {
                        throw new SQLException("No records found for the given condition: " + condition);
                    }

                    return paymentList;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}

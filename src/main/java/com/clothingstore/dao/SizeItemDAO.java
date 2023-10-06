package com.clothingstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clothingstore.interfaces.IDAO;
import com.clothingstore.models.SizeItemModel;

public class SizeItemDAO implements IDAO<SizeItemModel> {

    private static SizeItemDAO instance;

    public static SizeItemDAO getInstance() {
        if (instance == null) {
            instance = new SizeItemDAO();
        }
        return instance;
    }

    private static SizeItemModel createSizeItemModelFromResultSet(ResultSet rs) throws SQLException {
        int productId = rs.getInt("product_id");
        int sizeId = rs.getInt("size_id");
        int quantity = rs.getInt("quantity");
        return new SizeItemModel(productId, sizeId, quantity);
    }

    @Override
    public ArrayList<SizeItemModel> readDatabase() {
        ArrayList<SizeItemModel> sizeItemList = new ArrayList<>();
        try (
            ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM size_items")) {
            while (rs.next()) {
                SizeItemModel sizeItemModel = createSizeItemModelFromResultSet(rs);
                sizeItemList.add(sizeItemModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sizeItemList;
    }

    @Override
    public int insert(SizeItemModel sizeItemModel) {
        String insertSql = "INSERT INTO size_items (product_id, size_id, quantity) VALUES (?, ?, ?)";
        Object[] args = {
            sizeItemModel.getProductId(),
            sizeItemModel.getSizeId(),
            sizeItemModel.getQuantity()
        };
        try {
            return DatabaseConnection.executeUpdate(insertSql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int update(SizeItemModel sizeItemModel) {
        String updateSql = "UPDATE size_items SET quantity = ? WHERE product_id = ? AND size_id = ?";
        Object[] args = {
            sizeItemModel.getQuantity(),
            sizeItemModel.getProductId(),
            sizeItemModel.getSizeId()
        };
        try {
            return DatabaseConnection.executeUpdate(updateSql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int delete(int productId) {
        String deleteSql = "DELETE FROM size_items WHERE product_id = ?";
        Object[] args = { productId };
        try {
            return DatabaseConnection.executeUpdate(deleteSql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<SizeItemModel> search(String condition, String[] columnNames) {
        try {
            if (condition == null || condition.trim().isEmpty()) {
                throw new IllegalArgumentException(
                    "Search condition cannot be empty or null");
            }

            String query;
            if (columnNames == null || columnNames.length == 0) {
                // Search all columns
                query = "SELECT * FROM size_items WHERE CONCAT(product_id, size_id, quantity) LIKE ?";
            } else if (columnNames.length == 1) {
                // Search specific column in size_items table
                String column = columnNames[0];
                query = "SELECT * FROM size_items WHERE " + column + " LIKE ?";
            } else {
                // Search specific columns in size_items table
                query = "SELECT product_id, size_id, quantity FROM size_items WHERE CONCAT("
                    +
                    String.join(", ", columnNames) +
                    ") LIKE ?";
            }

            try (
                PreparedStatement pst = DatabaseConnection.getPreparedStatement(
                    query,
                    "%" + condition + "%")) {
                try (ResultSet rs = pst.executeQuery()) {
                    List<SizeItemModel> sizeItemList = new ArrayList<>();
                    while (rs.next()) {
                        SizeItemModel sizeItemModel = createSizeItemModelFromResultSet(rs);
                        sizeItemList.add(sizeItemModel);
                    }

                    if (sizeItemList.isEmpty()) {
                        throw new SQLException(
                            "No records found for the given condition: " + condition);
                    }

                    return sizeItemList;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}

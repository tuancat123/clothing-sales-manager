package com.clothingstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clothingstore.models.DiscountItemModel;

public class DiscountItemDAO {

    private static DiscountItemDAO instance;

    public static DiscountItemDAO getInstance() {
        if (instance == null) {
            instance = new DiscountItemDAO();
        }
        return instance;
    }

    private static DiscountItemModel createDiscountItemFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int category_id = rs.getInt("category_id");
        int discount_id = rs.getInt("discount_id");
        return new DiscountItemModel(id, category_id, discount_id);
    }

    public ArrayList<DiscountItemModel> readDatabase() {
        ArrayList<DiscountItemModel> discountItemList = new ArrayList<>();
        try (
                ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM discount_items")) {
            while (rs.next()) {
                DiscountItemModel discountItemModel = createDiscountItemFromResultSet(rs);
                discountItemList.add(discountItemModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discountItemList;
    }

    public int insert(DiscountItemModel discountItem) {
        String insertSql = "INSERT INTO discount_items (category_id, discount_id) VALUES (?, ?)";
        Object[] args = {
                discountItem.getCategoryId(),
                discountItem.getDiscountId()
        };
        try {
            return DatabaseConnection.executeUpdate(insertSql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int update(DiscountItemModel discountItem) {
        String updateSql = "UPDATE discount_items SET category_id = ?, discount_id = ? WHERE id = ?";
        Object[] args = {
                discountItem.getCategoryId(),
                discountItem.getDiscountId(),
                discountItem.getId()
        };
        try {
            return DatabaseConnection.executeUpdate(updateSql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int delete(int id) {
        String deleteSql = "DELETE FROM discount_items WHERE id = ?";
        Object[] args = { id };
        try {
            return DatabaseConnection.executeUpdate(deleteSql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<DiscountItemModel> search(String condition, String[] columnNames) {
        try {
            if (condition == null || condition.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Search condition cannot be empty or null");
            }

            String query;
            if (columnNames == null || columnNames.length == 0) {
                query = "SELECT * FROM discount_items WHERE CONCAT(id, category_id, discount_id) LIKE ?";
            } else if (columnNames.length == 1) {
                String column = columnNames[0];
                query = "SELECT * FROM discount_items WHERE " + column + " LIKE ?";
            } else {
                query = "SELECT id, category_id, discount_id FROM discount_items WHERE CONCAT("
                        +
                        String.join(", ", columnNames) +
                        ") LIKE ?";
            }

            try (
                    PreparedStatement pst = DatabaseConnection.getPreparedStatement(
                            query,
                            "%" + condition + "%")) {
                try (ResultSet rs = pst.executeQuery()) {
                    List<DiscountItemModel> discountItemList = new ArrayList<>();
                    while (rs.next()) {
                        DiscountItemModel discountItemModel = createDiscountItemFromResultSet(rs);
                        discountItemList.add(discountItemModel);
                    }

                    if (discountItemList.isEmpty()) {
                        throw new SQLException(
                                "No records found for the given condition: " + condition);
                    }

                    return discountItemList;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}

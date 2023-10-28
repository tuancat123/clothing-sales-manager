package com.clothingstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clothingstore.interfaces.IDAO;
import com.clothingstore.models.DiscountModel;

public class DiscountDAO implements IDAO<DiscountModel> {

    private static DiscountDAO instance;

    public static DiscountDAO getInstance() {
        if (instance == null) {
            instance = new DiscountDAO();
        }
        return instance;
    }

    private static DiscountModel createDiscountModelFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int discountPercentage = rs.getInt("discount_percentage");
        Timestamp startDay = rs.getTimestamp("start_day");
        Timestamp endDay = rs.getTimestamp("end_day");
        return new DiscountModel(id, name, discountPercentage, startDay, endDay);
    }

    @Override
    public ArrayList<DiscountModel> readDatabase() {
        ArrayList<DiscountModel> discountList = new ArrayList<>();
        try (
                ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM discounts")) {
            while (rs.next()) {
                DiscountModel discountModel = createDiscountModelFromResultSet(rs);
                discountList.add(discountModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discountList;
    }

    @Override
    public int insert(DiscountModel discountModel) {
        String insertSql = "INSERT INTO discounts (name, discount_percentage, start_day, end_day) VALUES (?, ?, ?, ?)";
        Object[] args = {
                discountModel.getDiscountName(),
                discountModel.getDiscountPercentage(),
                discountModel.getStartDay(),
                discountModel.getEndDay()
        };
        try {
            return DatabaseConnection.executeUpdate(insertSql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int update(DiscountModel discountModel) {
        String updateSql = "UPDATE discounts SET name = ?, discount_percentage = ?, start_day = ?, end_day = ? WHERE id = ?";
        Object[] args = {
                discountModel.getDiscountName(),
                discountModel.getDiscountPercentage(),
                discountModel.getStartDay(),
                discountModel.getEndDay(),
                discountModel.getId()
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
        String deleteSql = "DELETE FROM discounts WHERE id = ?";
        Object[] args = { id };
        try {
            return DatabaseConnection.executeUpdate(deleteSql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<DiscountModel> search(String condition, String[] columnNames) {
        try {
            if (condition == null || condition.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Search condition cannot be empty or null");
            }

            String query;
            if (columnNames == null || columnNames.length == 0) {
                query = "SELECT * FROM discounts WHERE CONCAT(id, name, discount_percentage, start_day, end_day) LIKE ?";
            } else if (columnNames.length == 1) {
                String column = columnNames[0];
                query = "SELECT * FROM discounts WHERE " + column + " LIKE ?";
            } else {
                query = "SELECT id, name, discount_percentage, start_day, end_day FROM discounts WHERE CONCAT("
                        +
                        String.join(", ", columnNames) +
                        ") LIKE ?";
            }

            try (
                    PreparedStatement pst = DatabaseConnection.getPreparedStatement(
                            query,
                            "%" + condition + "%")) {
                try (ResultSet rs = pst.executeQuery()) {
                    List<DiscountModel> discountList = new ArrayList<>();
                    while (rs.next()) {
                        DiscountModel discountModel = createDiscountModelFromResultSet(rs);
                        discountList.add(discountModel);
                    }

                    if (discountList.isEmpty()) {
                        throw new SQLException(
                                "No records found for the given condition: " + condition);
                    }

                    return discountList;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}

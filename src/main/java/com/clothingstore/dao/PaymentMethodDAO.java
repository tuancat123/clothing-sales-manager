package com.clothingstore.dao;

import com.clothingstore.interfaces.IDAO;
import com.clothingstore.models.PaymentMethodModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentMethodDAO implements IDAO<PaymentMethodModel> {
  private static PaymentMethodDAO instance;

  public static PaymentMethodDAO getInstance() {
    if (instance == null) {
      instance = new PaymentMethodDAO();
    }
    return instance;
  }

  private static PaymentMethodModel createPaymentMethodModelFromResultSet(ResultSet rs) throws SQLException {
    int id = rs.getInt("id");
    String methodName = rs.getString("method_name");
    return new PaymentMethodModel(id, methodName);
  }

  @Override
  public ArrayList<PaymentMethodModel> readDatabase() {
    ArrayList<PaymentMethodModel> paymentMethodList = new ArrayList<>();
    try (
        ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM payment_methods")) {
      while (rs.next()) {
        PaymentMethodModel PaymentMethodModel = createPaymentMethodModelFromResultSet(rs);
        paymentMethodList.add(PaymentMethodModel);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return paymentMethodList;
  }

  @Override
  public int insert(PaymentMethodModel paymentMethodModel) {
    String insertSql = "INSERT INTO payment_methods(method_name) values (?)";
    Object[] args = {
        paymentMethodModel.getMethodName()
    };
    try {
      return DatabaseConnection.executeUpdate(insertSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int update(PaymentMethodModel paymentMethodModel) {
    String updateSql = "UPDATE payment_methods SET method_name = ? WHERE id = ?";
    Object[] args = {
        paymentMethodModel.getMethodName(),
        paymentMethodModel.getId()
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
    String deleteSql = "DELETE FROM payment_methods WHERE id = ?";
    Object[] args = { id };
    try {
      return DatabaseConnection.executeUpdate(deleteSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public List<PaymentMethodModel> search(String condition, String[] columnNames) throws SQLException {
    throw new UnsupportedOperationException("This method is unsupported");
  }
}

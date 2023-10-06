package com.clothingstore.dao;

import com.clothingstore.models.PaymentMethodModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentMethodDAO {
  private static PaymentMethodDAO instance;

  private String jdbcURL = "jdbc:mysql://localhost:3306/your_database_name"; // Thay thế bằng URL của cơ sở dữ liệu của bạn
  private String jdbcUsername = "your_username"; // Thay thế bằng tên đăng nhập của bạn
  private String jdbcPassword = "your_password"; // Thay thế bằng mật khẩu của bạn

  private Connection connection;

  private static final String SELECT_ALL_PAYMENT_METHODS = "SELECT * FROM payment_methods";
  private static final String INSERT_PAYMENT_METHOD = "INSERT INTO payment_methods (method_name) VALUES (?)";
  private static final String UPDATE_PAYMENT_METHOD = "UPDATE payment_methods SET method_name = ? WHERE id = ?";
  private static final String DELETE_PAYMENT_METHOD = "DELETE FROM payment_methods WHERE id = ?";

  private PaymentMethodDAO() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static PaymentMethodDAO getInstance() {
    if (instance == null) {
      instance = new PaymentMethodDAO();
    }
    return instance;
  }

  public List<PaymentMethodModel> readDatabase() {
    List<PaymentMethodModel> paymentMethods = new ArrayList<>();

    try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PAYMENT_METHODS)) {
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        String methodName = resultSet.getString("method_name");

        PaymentMethodModel paymentMethod = new PaymentMethodModel(id, methodName);
        paymentMethods.add(paymentMethod);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return paymentMethods;
  }

  public int insert(PaymentMethodModel paymentMethod) {
    try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PAYMENT_METHOD, Statement.RETURN_GENERATED_KEYS)) {
      preparedStatement.setString(1, paymentMethod.getMethodName());

      int rowsInserted = preparedStatement.executeUpdate();

      if (rowsInserted > 0) {
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
          int generatedId = generatedKeys.getInt(1);
          paymentMethod.setId(generatedId);
        }
      }

      return rowsInserted;
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  public int update(PaymentMethodModel paymentMethod) {
    try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PAYMENT_METHOD)) {
      preparedStatement.setString(1, paymentMethod.getMethodName());
      preparedStatement.setInt(2, paymentMethod.getId());

      return preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  public int delete(int id) {
    try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PAYMENT_METHOD)) {
      preparedStatement.setInt(1, id);

      return preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }
}

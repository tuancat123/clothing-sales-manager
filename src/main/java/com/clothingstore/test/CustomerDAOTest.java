package com.clothingstore.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.clothingstore.dao.CustomerDAO;
import com.clothingstore.models.CustomerModel;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAOTest {

  private CustomerDAO customerDAO;

  @BeforeEach
  public void setUp() {
    customerDAO = CustomerDAO.getInstance();
  }

  @Test
  public void testReadDatabase() {
    ArrayList<CustomerModel> customerList = customerDAO.readDatabase();
    // Assert that the customerList is not null
    Assertions.assertNotNull(customerList);
    // Assert that the customerList is not empty
    Assertions.assertFalse(customerList.isEmpty());
  }

  @Test
  public void testInsert() {
    CustomerModel customerModel = new CustomerModel(1, "John Doe", "1234567890", "john@example.com");
    int result = customerDAO.insert(customerModel);
    // Assert that the insert operation was successful
    Assertions.assertEquals(1, result);
  }

  @Test
  public void testUpdate() {
    CustomerModel customerModel = new CustomerModel(1, "John Doe", "1234567890", "john@example.com");
    int result = customerDAO.update(customerModel);
    // Assert that the update operation was successful
    Assertions.assertEquals(1, result);
  }

  @Test
  public void testDelete() {
    int id = 1;
    int result = customerDAO.delete(id);
    // Assert that the delete operation was successful
    Assertions.assertEquals(1, result);
  }

  @Test
  public void testSearch() {
    String condition = "John";
    String[] columnNames = { "name" };
    List<CustomerModel> customerList = customerDAO.search(condition, columnNames);
    // Assert that the customerList is not null
    Assertions.assertNotNull(customerList);
  }
}

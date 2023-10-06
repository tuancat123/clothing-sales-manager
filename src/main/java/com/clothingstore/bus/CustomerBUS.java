package com.clothingstore.bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clothingstore.dao.CustomerDAO;
import com.clothingstore.interfaces.IBUS;
import com.clothingstore.models.CustomerModel;
import services.Validation;

public class CustomerBUS implements IBUS<CustomerModel> {
  private final List<CustomerModel> customerList = new ArrayList<>();
  private static CustomerBUS instance;

  public static CustomerBUS getInstance() {
    if (instance == null) {
      instance = new CustomerBUS();
    }
    return instance;
  }

  private CustomerBUS() {
    this.customerList.addAll(CustomerDAO.getInstance().readDatabase());
  }

  @Override
  public List<CustomerModel> getAllModels() {
    return Collections.unmodifiableList(customerList);
  }

  @Override
  public void refreshData() {
    customerList.clear();
    customerList.addAll(CustomerDAO.getInstance().readDatabase());
  }

  @Override
  public CustomerModel getModelById(int id) {
    refreshData();
    for (CustomerModel CustomerModel : customerList) {
      if (CustomerModel.getId() == id) {
        return CustomerModel;
      }
    }
    return null;
  }

  private CustomerModel mapToEntity(CustomerModel from) {
    CustomerModel to = new CustomerModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(CustomerModel from, CustomerModel to) {
    to.setCustomerName(from.getCustomerName());
    to.setEmail(from.getEmail());
    to.setPhone(from.getPhone());
  }

  @Override
  public int addModel(CustomerModel CustomerModel) {
    if (CustomerModel.getCustomerName() == null || CustomerModel.getCustomerName().isEmpty() ||
        CustomerModel.getPhone() == null || CustomerModel.getPhone().isEmpty()) {
      throw new IllegalArgumentException(
          "Customer name or phone can't be empty! Please check the input and try again.");
    }

    boolean hasPhone = CustomerModel.getPhone() != null && !CustomerModel.getPhone().isEmpty();
    boolean hasEmail = CustomerModel.getEmail() != null && !CustomerModel.getEmail().isEmpty();

    if (hasEmail && !Validation.isValidEmail(CustomerModel.getEmail())) {
      throw new IllegalArgumentException("Invalid email address.");
    }

    if (hasPhone && !Validation.isValidPhoneNumber(CustomerModel.getPhone())) {
      throw new IllegalArgumentException("Invalid number format.");
    }
    int id = CustomerDAO.getInstance().insert(mapToEntity(CustomerModel));
    CustomerModel.setId(id);
    customerList.add(CustomerModel);
    return id;
  }

  @Override
  public int updateModel(CustomerModel CustomerModel) {
    int updatedRows = CustomerDAO.getInstance().update(CustomerModel);
    if (updatedRows > 0) {
      int index = customerList.indexOf(CustomerModel);
      if (index != -1) {
        customerList.set(index, CustomerModel);
      }
    }
    return updatedRows;
  }

  @Override
  public int deleteModel(int id) {
    CustomerModel CustomerModel = getModelById(id);
    if (CustomerModel == null) {
      throw new IllegalArgumentException(
          "Customer with ID: " + id + " doesn't exist.");
    }
    int deletedRows = CustomerDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      customerList.remove(CustomerModel);
    }
    return deletedRows;
  }

  private boolean checkFilter(
      CustomerModel CustomerModel,
      String value,
      String[] columns) {
    for (String column : columns) {
      switch (column.toLowerCase()) {
        case "id" -> {
          if (Integer.parseInt(value) == CustomerModel.getId()) {
            return true;
          }
        }
        case "customer_name" -> {
          if (value.contains(CustomerModel.getCustomerName().toLowerCase())) {
            return true;
          }
        }
        case "phone" -> {
          if (value.contains(CustomerModel.getPhone())) {
            return true;
          }
        }
        case "email" -> {
          if (value.contains(CustomerModel.getEmail())) {
            return true;
          }
        }
        default -> {
          if (checkAllColumns(CustomerModel, value)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private boolean checkAllColumns(CustomerModel CustomerModel, String value) {
    return (CustomerModel.getId() == Integer.parseInt(value) ||
        CustomerModel.getCustomerName().contains(value) ||
        CustomerModel.getPhone().contains(value) ||
        CustomerModel.getEmail().contains(value));
  }

  @Override
  public List<CustomerModel> searchModel(String value, String[] columns) {
    List<CustomerModel> results = new ArrayList<>();
    List<CustomerModel> entities = CustomerBUS.getInstance().searchModel(value, columns);
    for (CustomerModel entity : entities) {
      CustomerModel model = mapToEntity(entity);
      if (checkFilter(model, value, columns)) {
        results.add(model);
      }
    }
    return results;
  }

}

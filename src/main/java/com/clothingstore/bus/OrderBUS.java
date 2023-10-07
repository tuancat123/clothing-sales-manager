package com.clothingstore.bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clothingstore.dao.OrderDAO;
import com.clothingstore.interfaces.IBUS;
import com.clothingstore.models.OrderModel;

public class OrderBUS implements IBUS<OrderModel> {
  private final List<OrderModel> orderList = new ArrayList<>();
  private static OrderBUS instance;

  public static OrderBUS getInstance() {
    if (instance == null) {
      instance = new OrderBUS();
    }
    return instance;
  }

  private OrderBUS() {
    this.orderList.addAll(OrderDAO.getInstance().readDatabase());
  }

  @Override
  public List<OrderModel> getAllModels() {
    return Collections.unmodifiableList(orderList);
  }

  @Override
  public void refreshData() {
    orderList.clear();
    orderList.addAll(OrderDAO.getInstance().readDatabase());
  }

  @Override
  public OrderModel getModelById(int id) {
    refreshData();
    for (OrderModel orderModel : orderList) {
      if (orderModel.getId() == id) {
        return orderModel;
      }
    }
    return null;
  }

  private OrderModel mapToEntity(OrderModel from) {
    OrderModel to = new OrderModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(OrderModel from, OrderModel to) {
    to.setCustomerId(from.getCustomerId());
    to.setUserId(from.getUserId());
    to.setOrderDate(from.getOrderDate());
    to.setTotalAmount(from.getTotalAmount());
  }

  @Override
  public int addModel(OrderModel orderModel) {
    if (orderModel.getCustomerId() <= 0 || orderModel.getUserId() <= 0 || orderModel.getOrderDate() == null
        || orderModel.getTotalAmount() <= 0) {
      throw new IllegalArgumentException("Invalid order information. Please check the input and try again.");
    }

    int id = OrderDAO.getInstance().insert(mapToEntity(orderModel));
    orderModel.setId(id);
    orderList.add(orderModel);
    return id;
  }

  @Override
  public int updateModel(OrderModel orderModel) {
    int updatedRows = OrderDAO.getInstance().update(orderModel);
    if (updatedRows > 0) {
      int index = orderList.indexOf(orderModel);
      if (index != -1) {
        orderList.set(index, orderModel);
      }
    }
    return updatedRows;
  }

  @Override
  public int deleteModel(int id) {
    OrderModel orderModel = getModelById(id);
    if (orderModel == null) {
      throw new IllegalArgumentException("Order with ID: " + id + " doesn't exist.");
    }
    int deletedRows = OrderDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      orderList.remove(orderModel);
    }
    return deletedRows;
  }

  private boolean checkFilter(
      OrderModel orderModel,
      String value,
      String[] columns) {
    for (String column : columns) {
      switch (column.toLowerCase()) {
        case "id" -> {
          if (Integer.parseInt(value) == orderModel.getId()) {
            return true;
          }
        }
        case "customer_id" -> {
          if (Integer.parseInt(value) == orderModel.getCustomerId()) {
            return true;
          }
        }
        case "user_id" -> {
          if (Integer.parseInt(value) == orderModel.getUserId()) {
            return true;
          }
        }
        case "order_date" -> {
          if (orderModel.getOrderDate().toString().contains(value)) {
            return true;
          }
        }
        case "total_amount" -> {
          if (Integer.parseInt(value) == orderModel.getTotalAmount()) {
            return true;
          }
        }
        default -> {
          if (checkAllColumns(orderModel, value)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private boolean checkAllColumns(OrderModel orderModel, String value) {
    return (orderModel.getId() == Integer.parseInt(value) ||
        orderModel.getCustomerId() == Integer.parseInt(value) ||
        orderModel.getUserId() == Integer.parseInt(value) ||
        orderModel.getOrderDate().toString().contains(value) ||
        orderModel.getTotalAmount() == Integer.parseInt(value));
  }

  @Override
  public List<OrderModel> searchModel(String value, String[] columns) {
    List<OrderModel> results = new ArrayList<>();
    List<OrderModel> entities = OrderBUS.getInstance().searchModel(value, columns);
    for (OrderModel entity : entities) {
      OrderModel model = mapToEntity(entity);
      if (checkFilter(model, value, columns)) {
        results.add(model);
      }
    }
    return results;
  }
}

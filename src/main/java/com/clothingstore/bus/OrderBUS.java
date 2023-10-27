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
    for (OrderModel order : orderList) {
      if (order.getId() == id) {
        return order;
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
    to.setId(from.getId());
    to.setCustomerId(from.getCustomerId());
    to.setUserId(from.getUserId());
    to.setOrderDate(from.getOrderDate());
    to.setTotalPrice(from.getTotalPrice());
  }

  @Override
  public int addModel(OrderModel model) {
    if (model == null || model.getCustomerId() <= 0 || model.getUserId() <= 0 || model.getTotalPrice() <= 0) {
      throw new IllegalArgumentException(
          "There may be errors in required fields, please check your input and try again.");
    }
    int id = OrderDAO.getInstance().insert(mapToEntity(model));
    orderList.add(model);
    return id;
  }

  @Override
  public int updateModel(OrderModel model) {
    int updatedRows = OrderDAO.getInstance().update(model);
    if (updatedRows > 0) {
      int index = orderList.indexOf(model);
      if (index != -1) {
        orderList.set(index, model);
      }
    }
    return updatedRows;
  }

  @Override
  public int deleteModel(int id) {
    OrderModel order = getModelById(id);
    if (order == null) {
      throw new IllegalArgumentException(
          "Order with ID: " + id + " doesn't exist.");
    }
    int deletedRows = OrderDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      orderList.remove(order);
    }
    return deletedRows;
  }

  private boolean checkFilter(
        OrderModel order,
        String value,
        String[] columns) {
        for (String column : columns) {
            switch (column.toLowerCase()) {
                case "id" -> {
                    if (Integer.parseInt(value) == order.getId()) {
                        return true;
                    }
                }
                case "customer_id" -> {
                    if (Integer.parseInt(value) == order.getCustomerId()) {
                        return true;
                    }
                }
                case "user_id" -> {
                    if (Integer.parseInt(value) == order.getUserId()) {
                        return true;
                    }
                }
                case "total_price" -> {
                    if (Double.valueOf(order.getTotalPrice()).equals(Double.valueOf(value))) {
                        return true;
                    }
                }
                default -> {
                    if (checkAllColumns(order, value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

  private boolean checkAllColumns(OrderModel order, String value) {
    return (order.getId() == Integer.parseInt(value) ||
        order.getCustomerId() == Integer.parseInt(value) ||
        order.getUserId() == Integer.parseInt(value) ||
        Double.valueOf(order.getTotalPrice()).equals(Double.valueOf(value)));
  }

  @Override
  public List<OrderModel> searchModel(String value, String[] columns) {
    List<OrderModel> results = new ArrayList<>();
    List<OrderModel> entities = OrderDAO.getInstance().search(value, columns);
    for (OrderModel entity : entities) {
      OrderModel model = mapToEntity(entity);
      if (checkFilter(model, value, columns)) {
        results.add(model);
      }
    }
    return results;
  }
}

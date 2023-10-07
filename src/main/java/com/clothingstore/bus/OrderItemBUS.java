package com.clothingstore.bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clothingstore.dao.OrderItemDAO;
import com.clothingstore.interfaces.IBUS;
import com.clothingstore.models.OrderItemModel;

public class OrderItemBUS implements IBUS<OrderItemModel> {
    private final List<OrderItemModel> orderItemList = new ArrayList<>();
    private static OrderItemBUS instance;

    public static OrderItemBUS getInstance() {
        if (instance == null) {
            instance = new OrderItemBUS();
        }
        return instance;
    }

    private OrderItemBUS() {
        this.orderItemList.addAll(OrderItemDAO.getInstance().readDatabase());
    }

    @Override
    public List<OrderItemModel> getAllModels() {
        return Collections.unmodifiableList(orderItemList);
    }

    @Override
    public void refreshData() {
        orderItemList.clear();
        orderItemList.addAll(OrderItemDAO.getInstance().readDatabase());
    }

    @Override
    public OrderItemModel getModelById(int id) {
        refreshData();
        for (OrderItemModel orderItem : orderItemList) {
            if (orderItem.getId() == id) {
                return orderItem;
            }
        }
        return null;
    }

    private OrderItemModel mapToEntity(OrderItemModel from) {
        OrderItemModel to = new OrderItemModel();
        updateEntityFields(from, to);
        return to;
    }

    private void updateEntityFields(OrderItemModel from, OrderItemModel to) {
        to.setOrderId(from.getOrderId());
        to.setProductId(from.getProductId());
        to.setQuantity(from.getQuantity());
        to.setPrice(from.getPrice());
    }

    @Override
    public int addModel(OrderItemModel orderItem) {
        if (orderItem.getOrderId() <= 0 || orderItem.getProductId() <= 0 || orderItem.getQuantity() <= 0
                || orderItem.getPrice() <= 0) {
            throw new IllegalArgumentException(
                    "Order ID, Product ID, Quantity, and Price must be greater than 0. Please check the input and try again.");
        }

        int id = OrderItemDAO.getInstance().insert(mapToEntity(orderItem));
        orderItemList.add(orderItem);
        return id;
    }

    @Override
    public int updateModel(OrderItemModel orderItem) {
        int updatedRows = OrderItemDAO.getInstance().update(orderItem);
        if (updatedRows > 0) {
            int index = orderItemList.indexOf(orderItem);
            if (index != -1) {
                orderItemList.set(index, orderItem);
            }
        }
        return updatedRows;
    }

    @Override
    public int deleteModel(int id) {
        OrderItemModel orderItem = getModelById(id);
        if (orderItem == null) {
            throw new IllegalArgumentException(
                    "Order Item with ID: " + id + " doesn't exist.");
        }
        int deletedRows = OrderItemDAO.getInstance().delete(id);
        if (deletedRows > 0) {
            orderItemList.remove(orderItem);
        }
        return deletedRows;
    }

    private boolean checkFilter(
            OrderItemModel orderItem,
            String value,
            String[] columns) {
        for (String column : columns) {
            switch (column.toLowerCase()) {
                case "id" -> {
                    if (Integer.parseInt(value) == orderItem.getId()) {
                        return true;
                    }
                }
                case "order_id" -> {
                    if (Integer.parseInt(value) == orderItem.getOrderId()) {
                        return true;
                    }
                }
                case "product_id" -> {
                    if (Integer.parseInt(value) == orderItem.getProductId()) {
                        return true;
                    }
                }
                case "quantity" -> {
                    if (Integer.parseInt(value) == orderItem.getQuantity()) {
                        return true;
                    }
                }
                case "price" -> {
                    if (Integer.parseInt(value) == orderItem.getPrice()) {
                        return true;
                    }
                }
                default -> {
                    if (checkAllColumns(orderItem, value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkAllColumns(OrderItemModel orderItem, String value) {
        return (orderItem.getId() == Integer.parseInt(value) ||
                orderItem.getOrderId() == Integer.parseInt(value) ||
                orderItem.getProductId() == Integer.parseInt(value) ||
                orderItem.getQuantity() == Integer.parseInt(value) ||
                orderItem.getPrice() == Integer.parseInt(value));
    }

    @Override
    public List<OrderItemModel> searchModel(String value, String[] columns) {
        List<OrderItemModel> results = new ArrayList<>();
        List<OrderItemModel> entities = OrderItemBUS.getInstance().getAllModels();
        for (OrderItemModel entity : entities) {
            OrderItemModel model = mapToEntity(entity);
            if (checkFilter(model, value, columns)) {
                results.add(model);
            }
        }
        return results;
    }
}

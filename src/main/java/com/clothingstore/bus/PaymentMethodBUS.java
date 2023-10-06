package com.clothingstore.bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clothingstore.dao.PaymentMethodDAO;
import com.clothingstore.interfaces.IBUS;
import com.clothingstore.models.PaymentMethodModel;

public class PaymentMethodBUS implements IBUS<PaymentMethodModel> {
    private final List<PaymentMethodModel> paymentMethodList = new ArrayList<>();
    private static PaymentMethodBUS instance;

    public static PaymentMethodBUS getInstance() {
        if (instance == null) {
            instance = new PaymentMethodBUS();
        }
        return instance;
    }

    private PaymentMethodBUS() {
        this.paymentMethodList.addAll(PaymentMethodDAO.getInstance().readDatabase());
    }

    @Override
    public List<PaymentMethodModel> getAllModels() {
        return Collections.unmodifiableList(paymentMethodList);
    }

    @Override
    public void refreshData() {
        paymentMethodList.clear();
        paymentMethodList.addAll(PaymentMethodDAO.getInstance().readDatabase());
    }

    @Override
    public PaymentMethodModel getModelById(int id) {
        refreshData();
        for (PaymentMethodModel paymentMethod : paymentMethodList) {
            if (paymentMethod.getId() == id) {
                return paymentMethod;
            }
        }
        return null;
    }

    @Override
    public int addModel(PaymentMethodModel paymentMethod) {
        if (paymentMethod.getMethodName() == null || paymentMethod.getMethodName().isEmpty()) {
            throw new IllegalArgumentException(
                    "Method name can't be empty! Please check the input and try again.");
        }

        int id = PaymentMethodDAO.getInstance().insert(paymentMethod);
        paymentMethod.setId(id);
        paymentMethodList.add(paymentMethod);
        return id;
    }

    @Override
    public int updateModel(PaymentMethodModel paymentMethod) {
        int updatedRows = PaymentMethodDAO.getInstance().update(paymentMethod);
        if (updatedRows > 0) {
            int index = paymentMethodList.indexOf(paymentMethod);
            if (index != -1) {
                paymentMethodList.set(index, paymentMethod);
            }
        }
        return updatedRows;
    }

    @Override
    public int deleteModel(int id) {
        PaymentMethodModel paymentMethod = getModelById(id);
        if (paymentMethod == null) {
            throw new IllegalArgumentException(
                    "Payment method with ID: " + id + " doesn't exist.");
        }
        int deletedRows = PaymentMethodDAO.getInstance().delete(id);
        if (deletedRows > 0) {
            paymentMethodList.remove(paymentMethod);
        }
        return deletedRows;
    }

    @Override
    public List<PaymentMethodModel> searchModel(String value, String[] columns) {
        List<PaymentMethodModel> results = new ArrayList<>();
        List<PaymentMethodModel> entities = getAllModels();
        for (PaymentMethodModel entity : entities) {
            if (checkFilter(entity, value, columns)) {
                results.add(entity);
            }
        }
        return results;
    }

    private boolean checkFilter(
            PaymentMethodModel paymentMethod,
            String value,
            String[] columns) {
        for (String column : columns) {
            switch (column.toLowerCase()) {
                case "id" -> {
                    if (Integer.parseInt(value) == paymentMethod.getId()) {
                        return true;
                    }
                }
                case "method_name" -> {
                    if (value.contains(paymentMethod.getMethodName().toLowerCase())) {
                        return true;
                    }
                }
                default -> {
                }
            }
        }
        return false;
    }
}

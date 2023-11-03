
package com.clothingstore.bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clothingstore.dao.PaymentDAO;
import com.clothingstore.interfaces.IBUS;
import com.clothingstore.models.PaymentModel;

public class PaymentBUS implements IBUS<PaymentModel> {
    private final List<PaymentModel> paymentList = new ArrayList<>();
    private static PaymentBUS instance;

    public static PaymentBUS getInstance() {
        if (instance == null) {
            instance = new PaymentBUS();
        }
        return instance;
    }

    private PaymentBUS() {
        this.paymentList.addAll(PaymentDAO.getInstance().readDatabase());
    }

    @Override
    public List<PaymentModel> getAllModels() {
        return Collections.unmodifiableList(paymentList);
    }

    @Override
    public void refreshData() {
        paymentList.clear();
        paymentList.addAll(PaymentDAO.getInstance().readDatabase());
    }

    @Override
    public PaymentModel getModelById(int id) {
        refreshData();
        for (PaymentModel payment : paymentList) {
            if (payment.getId() == id) {
                return payment;
            }
        }
        return null;
    }

    private PaymentModel mapToEntity(PaymentModel from) {
        PaymentModel to = new PaymentModel();
        updateEntityFields(from, to);
        return to;
    }

    private void updateEntityFields(PaymentModel from, PaymentModel to) {
        to.setId(from.getId());
        to.setOrderId(from.getOrderId());
        to.setPaymentMethodId(from.getPaymentMethodId());
        to.setTotalPrice(from.getTotalPrice());
        to.setPaymentDate(from.getPaymentDate());
    }

    @Override
    public int addModel(PaymentModel model) {
        if (model == null ||
                model.getTotalPrice() <= 0) {
            throw new IllegalArgumentException(
                    "There may be errors in required fields, please check your input and try again.");
        }
        int id = PaymentDAO.getInstance().insert(mapToEntity(model));
        paymentList.add(model);
        return id;
    }

    @Override
    public int updateModel(PaymentModel model) {
        int updatedRows = PaymentDAO.getInstance().update(model);
        if (updatedRows > 0) {
            int index = paymentList.indexOf(model);
            if (index != -1) {
                paymentList.set(index, model);
            }
        }
        return updatedRows;
    }

    @Override
    public int deleteModel(int id) {
        PaymentModel paymentModel = getModelById(id);
        if (paymentModel == null) {
            throw new IllegalArgumentException(
                    "Payment with ID: " + id + " doesn't exist.");
        }
        int deletedRows = PaymentDAO.getInstance().delete(id);
        if (deletedRows > 0) {
            paymentList.remove(paymentModel);
        }
        return deletedRows;
    }

    private boolean checkFilter(
            PaymentModel paymentModel,
            String value,
            String[] columns) {
        for (String column : columns) {
            switch (column.toLowerCase()) {
                case "id" -> {
                    if (Integer.parseInt(value) == paymentModel.getId()) {
                        return true;
                    }
                }
                case "order_id" -> {
                    if (Integer.parseInt(value) == paymentModel.getOrderId()) {
                        return true;
                    }
                }
                case "method_id" -> {
                    if (Integer.parseInt(value) == paymentModel.getPaymentMethodId()) {
                        return true;
                    }
                }
                case "total_price" -> {
                    if (Double.valueOf(paymentModel.getTotalPrice()).equals(Double.valueOf(value))) {
                        return true;
                    }
                }
                case "payment_date" -> {
                    if (paymentModel.getPaymentDate().toString().equals(value)) {
                        return true;
                    }
                }
                default -> {
                    if (checkAllColumns(paymentModel, value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkAllColumns(PaymentModel paymentModel, String value) {
        return (paymentModel.getId() == Integer.parseInt(value) ||
                paymentModel.getOrderId() == Integer.parseInt(value) ||
                paymentModel.getPaymentMethodId() == Integer.parseInt(value) ||
                Double.valueOf(paymentModel.getTotalPrice()).equals(Double.valueOf(value)) ||
                paymentModel.getPaymentDate().toString().equals(value));
    }

    @Override
    public List<PaymentModel> searchModel(String value, String[] columns) {
        List<PaymentModel> results = new ArrayList<>();
        List<PaymentModel> entities = PaymentDAO.getInstance().search(value, columns);
        for (PaymentModel entity : entities) {
            PaymentModel model = mapToEntity(entity);
            if (checkFilter(model, value, columns)) {
                results.add(model);
            }
        }
        return results;
    }
}

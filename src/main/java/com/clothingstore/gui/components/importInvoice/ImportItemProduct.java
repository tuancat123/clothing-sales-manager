package com.clothingstore.gui.components.importInvoice;

import javax.swing.*;
import javax.swing.text.*;

import com.clothingstore.models.ImportItemsModel;
import com.clothingstore.models.ProductModel;
import com.clothingstore.models.SizeItemModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class ImportItemProduct extends JPanel {
    private JButton deleteButton;
    private JPanel groupSizePanel;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JTextField priceTextField;
    private JSpinner sizeLQuantitySpinner;
    private JRadioButton sizeLRadioButton;
    private JSpinner sizeMQuantitySpinner;
    private JRadioButton sizeMRadioButton;
    private JSpinner sizeSQuantitySpinner;
    private JRadioButton sizeSRadioButton;
    private JSpinner sizeXLQuantitySpinner;
    private JRadioButton sizeXLRadioButton;
    private JSpinner sizeXXLQuantitySpinner;
    private JRadioButton sizeXXLRadioButton;
    private JLabel sttLabel;
    private JLabel totalPriceTextField;
    private int quantity = 0;

    private ProductModel productModel;

    public ImportItemProduct(ProductModel productModel, int i) {
        this.productModel = productModel;
        initComponents(productModel, i);
        // updateData();
        handleEvent();
    }

    private void handleEvent() {
        sizeSQuantitySpinner.setEnabled(false);
        sizeMQuantitySpinner.setEnabled(false);
        sizeLQuantitySpinner.setEnabled(false);
        sizeXLQuantitySpinner.setEnabled(false);
        sizeXXLQuantitySpinner.setEnabled(false);
        sizeSQuantitySpinner.setValue(1);
        sizeMQuantitySpinner.setValue(1);
        sizeLQuantitySpinner.setValue(1);
        sizeXLQuantitySpinner.setValue(1);
        sizeXXLQuantitySpinner.setValue(1);

        sizeSRadioButton.addActionListener(e -> updateQuantity());
        sizeMRadioButton.addActionListener(e -> updateQuantity());
        sizeLRadioButton.addActionListener(e -> updateQuantity());
        sizeXLRadioButton.addActionListener(e -> updateQuantity());
        sizeXXLRadioButton.addActionListener(e -> updateQuantity());

        sizeSQuantitySpinner.addChangeListener(e -> {
            int value = (int) sizeSQuantitySpinner.getValue();
            if (value < 1) {
                sizeSQuantitySpinner.setValue(1);
            }
            updateData();
        });

        sizeMQuantitySpinner.addChangeListener(e -> {
            int value = (int) sizeMQuantitySpinner.getValue();
            if (value < 1) {
                sizeMQuantitySpinner.setValue(1);
            }
            updateData();
        });

        sizeLQuantitySpinner.addChangeListener(e -> {
            int value = (int) sizeLQuantitySpinner.getValue();
            if (value < 1) {
                sizeLQuantitySpinner.setValue(1);
            }
            updateData();
        });

        sizeXLQuantitySpinner.addChangeListener(e -> {
            int value = (int) sizeXLQuantitySpinner.getValue();
            if (value < 1) {
                sizeXLQuantitySpinner.setValue(1);
            }
            updateData();
        });

        sizeXXLQuantitySpinner.addChangeListener(e -> {
            int value = (int) sizeXXLQuantitySpinner.getValue();
            if (value < 1) {
                sizeXXLQuantitySpinner.setValue(1);
            }
            updateData();
        });

        priceTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                updateData();
            }
        });

    }

    private void updateQuantity() {
        updateData();
        sizeSQuantitySpinner.setEnabled(sizeSRadioButton.isSelected());
        sizeMQuantitySpinner.setEnabled(sizeMRadioButton.isSelected());
        sizeLQuantitySpinner.setEnabled(sizeLRadioButton.isSelected());
        sizeXLQuantitySpinner.setEnabled(sizeXLRadioButton.isSelected());
        sizeXXLQuantitySpinner.setEnabled(sizeXXLRadioButton.isSelected());
    }

    private void updateData() {
        quantity = 0;
        java.util.List<SizeItemModel> sizeItemList = AddNewImport.getSizeItemList();
        java.util.List<ImportItemsModel> importItemList = AddNewImport.getImportItemList();

        if (sizeSRadioButton.isSelected()) {
            int quantityS = (int) sizeSQuantitySpinner.getValue();
            quantity += quantityS;
            boolean checkExist = false;
            for (SizeItemModel sizeItemModel : sizeItemList) {
                if (sizeItemModel.getProductId() == productModel.getId() && sizeItemModel.getSizeId() == 1) {
                    sizeItemModel.setQuantity(quantityS);
                    checkExist = true;
                }
            }
            for (ImportItemsModel importItemsModel : importItemList) {
                if (importItemsModel.getProduct_id() == productModel.getId() && importItemsModel.getSize_id() == 1) {
                    importItemsModel.setQuantity(quantityS);
                    importItemsModel.setPrice(Integer.parseInt(priceTextField.getText()));
                    checkExist = true;
                }
            }
            if (!checkExist) {
                SizeItemModel sizeItemModel = new SizeItemModel();
                sizeItemModel.setProductId(productModel.getId());
                sizeItemModel.setQuantity(quantityS);
                sizeItemModel.setSizeId(1);
                sizeItemList.add(sizeItemModel);

                ImportItemsModel importItemsModel = new ImportItemsModel();
                importItemsModel.setProduct_id(productModel.getId());
                importItemsModel.setSize_id(1);
                sizeItemModel.setQuantity(quantityS);
                importItemsModel.setPrice(Integer.parseInt(priceTextField.getText()));
                importItemList.add(importItemsModel);
            }

            AddNewImport.setSizeItemList(sizeItemList);
            AddNewImport.setImportItemList(importItemList);
        }
        if (sizeMRadioButton.isSelected()) {
            int quantityM = (int) sizeMQuantitySpinner.getValue();
            quantity += quantityM;
            boolean checkExist = false;
            for (SizeItemModel sizeItemModel : sizeItemList) {
                if (sizeItemModel.getProductId() == productModel.getId() && sizeItemModel.getSizeId() == 2) {
                    sizeItemModel.setQuantity(quantityM);
                    checkExist = true;
                }
            }
            for (ImportItemsModel importItemsModel : importItemList) {
                if (importItemsModel.getProduct_id() == productModel.getId() && importItemsModel.getSize_id() == 2) {
                    importItemsModel.setQuantity(quantityM);
                    importItemsModel.setPrice(Integer.parseInt(priceTextField.getText()));
                    checkExist = true;
                }
            }
            if (!checkExist) {
                SizeItemModel sizeItemModel = new SizeItemModel();
                sizeItemModel.setProductId(productModel.getId());
                sizeItemModel.setQuantity(quantityM);
                sizeItemModel.setSizeId(2);
                sizeItemList.add(sizeItemModel);

                ImportItemsModel importItemsModel = new ImportItemsModel();
                importItemsModel.setProduct_id(productModel.getId());
                importItemsModel.setSize_id(2);
                sizeItemModel.setQuantity(quantityM);
                importItemsModel.setPrice(Integer.parseInt(priceTextField.getText()));
                importItemList.add(importItemsModel);
            }

            AddNewImport.setSizeItemList(sizeItemList);
            AddNewImport.setImportItemList(importItemList);
        }
        if (sizeLRadioButton.isSelected()) {
            int quantityL = (int) sizeLQuantitySpinner.getValue();
            quantity += quantityL;
            boolean checkExist = false;
            for (SizeItemModel sizeItemModel : sizeItemList) {
                if (sizeItemModel.getProductId() == productModel.getId() && sizeItemModel.getSizeId() == 3) {
                    sizeItemModel.setQuantity(quantityL);
                    checkExist = true;
                }
            }
            for (ImportItemsModel importItemsModel : importItemList) {
                if (importItemsModel.getProduct_id() == productModel.getId() && importItemsModel.getSize_id() == 3) {
                    importItemsModel.setQuantity(quantityL);
                    importItemsModel.setPrice(Integer.parseInt(priceTextField.getText()));
                    checkExist = true;
                }
            }
            if (!checkExist) {
                SizeItemModel sizeItemModel = new SizeItemModel();
                sizeItemModel.setProductId(productModel.getId());
                sizeItemModel.setQuantity(quantityL);
                sizeItemModel.setSizeId(3);
                sizeItemList.add(sizeItemModel);

                ImportItemsModel importItemsModel = new ImportItemsModel();
                importItemsModel.setProduct_id(productModel.getId());
                importItemsModel.setSize_id(3);
                sizeItemModel.setQuantity(quantityL);
                importItemsModel.setPrice(Integer.parseInt(priceTextField.getText()));
                importItemList.add(importItemsModel);
            }

            AddNewImport.setSizeItemList(sizeItemList);
            AddNewImport.setImportItemList(importItemList);
        }
        if (sizeXLRadioButton.isSelected()) {
            int quantityXL = (int) sizeXLQuantitySpinner.getValue();
            quantity += quantityXL;
            boolean checkExist = false;
            for (SizeItemModel sizeItemModel : sizeItemList) {
                if (sizeItemModel.getProductId() == productModel.getId() && sizeItemModel.getSizeId() == 4) {
                    sizeItemModel.setQuantity(quantityXL);
                    checkExist = true;
                }
            }
            for (ImportItemsModel importItemsModel : importItemList) {
                if (importItemsModel.getProduct_id() == productModel.getId() && importItemsModel.getSize_id() == 4) {
                    importItemsModel.setQuantity(quantityXL);
                    importItemsModel.setPrice(Integer.parseInt(priceTextField.getText()));
                    checkExist = true;
                }
            }
            if (!checkExist) {
                SizeItemModel sizeItemModel = new SizeItemModel();
                sizeItemModel.setProductId(productModel.getId());
                sizeItemModel.setQuantity(quantityXL);
                sizeItemModel.setSizeId(4);
                sizeItemList.add(sizeItemModel);

                ImportItemsModel importItemsModel = new ImportItemsModel();
                importItemsModel.setProduct_id(productModel.getId());
                importItemsModel.setSize_id(4);
                sizeItemModel.setQuantity(quantityXL);
                importItemsModel.setPrice(Integer.parseInt(priceTextField.getText()));
                importItemList.add(importItemsModel);
            }

            AddNewImport.setSizeItemList(sizeItemList);
            AddNewImport.setImportItemList(importItemList);
        }
        if (sizeXXLRadioButton.isSelected()) {
            int quantityXXL = (int) sizeXXLQuantitySpinner.getValue();
            quantity += quantityXXL;
            boolean checkExist = false;
            for (SizeItemModel sizeItemModel : sizeItemList) {
                if (sizeItemModel.getProductId() == productModel.getId() && sizeItemModel.getSizeId() == 5) {
                    sizeItemModel.setQuantity(quantityXXL);
                    checkExist = true;
                }
            }
            for (ImportItemsModel importItemsModel : importItemList) {
                if (importItemsModel.getProduct_id() == productModel.getId() && importItemsModel.getSize_id() == 5) {
                    importItemsModel.setQuantity(quantityXXL);
                    importItemsModel.setPrice(Integer.parseInt(priceTextField.getText()));
                    checkExist = true;
                }
            }
            if (!checkExist) {
                SizeItemModel sizeItemModel = new SizeItemModel();
                sizeItemModel.setProductId(productModel.getId());
                sizeItemModel.setQuantity(quantityXXL);
                sizeItemModel.setSizeId(5);
                sizeItemList.add(sizeItemModel);

                ImportItemsModel importItemsModel = new ImportItemsModel();
                importItemsModel.setProduct_id(productModel.getId());
                importItemsModel.setSize_id(5);
                sizeItemModel.setQuantity(quantityXXL);
                importItemsModel.setPrice(Integer.parseInt(priceTextField.getText()));
                importItemList.add(importItemsModel);
            }

            AddNewImport.setSizeItemList(sizeItemList);
            AddNewImport.setImportItemList(importItemList);
        }

        try {
            double price = Double.parseDouble(priceTextField.getText());
            double totalPrice = quantity * price;
            totalPriceTextField.setText(String.format("%.2f", totalPrice) + " đ");
        } catch (NumberFormatException e) {
            totalPriceTextField.setText("1");
        }
    }

    private void initComponents(ProductModel productModel, int i) {
        sttLabel = new JLabel();
        idLabel = new JLabel();
        nameLabel = new JLabel();
        groupSizePanel = new JPanel();
        sizeSRadioButton = new JRadioButton();
        sizeSQuantitySpinner = new JSpinner();
        sizeMRadioButton = new JRadioButton();
        sizeMQuantitySpinner = new JSpinner();
        sizeLRadioButton = new JRadioButton();
        sizeLQuantitySpinner = new JSpinner();
        sizeXLRadioButton = new JRadioButton();
        sizeXLQuantitySpinner = new JSpinner();
        sizeXXLRadioButton = new JRadioButton();
        sizeXXLQuantitySpinner = new JSpinner();
        priceTextField = new JTextField(6);
        priceTextField.setText("1");
        totalPriceTextField = new JLabel("1");
        deleteButton = new JButton();

        ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/resources/icons/delete_icon.png"));
        deleteButton.setIcon(deleteIcon);
        deleteButton.setPreferredSize(new Dimension(20, 20));

        setLayout(new FlowLayout(FlowLayout.CENTER, 35, 5));

        sttLabel.setText("" + i);
        add(sttLabel);

        idLabel.setText("" + productModel.getId());
        add(idLabel);

        nameLabel.setText(productModel.getName());
        nameLabel.setPreferredSize(new Dimension(100, 40));
        nameLabel.setMaximumSize(new Dimension(100, 40));
        add(nameLabel);

        groupSizePanel.setLayout(new GridLayout(5, 2));
        groupSizePanel.setPreferredSize(new Dimension(100, 100));

        sizeSRadioButton.setText("S");
        groupSizePanel.add(sizeSRadioButton);
        groupSizePanel.add(sizeSQuantitySpinner);

        sizeMRadioButton.setText("M");
        groupSizePanel.add(sizeMRadioButton);
        groupSizePanel.add(sizeMQuantitySpinner);

        sizeLRadioButton.setText("L");
        groupSizePanel.add(sizeLRadioButton);
        groupSizePanel.add(sizeLQuantitySpinner);

        sizeXLRadioButton.setText("XL");
        groupSizePanel.add(sizeXLRadioButton);
        groupSizePanel.add(sizeXLQuantitySpinner);

        sizeXXLRadioButton.setText("XXL");
        groupSizePanel.add(sizeXXLRadioButton);
        groupSizePanel.add(sizeXXLQuantitySpinner);

        add(groupSizePanel);

        ((PlainDocument) priceTextField.getDocument()).setDocumentFilter(new MyDocumentFilter());

        add(priceTextField);
        add(totalPriceTextField);
        add(deleteButton);
    }

    private class MyDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            if (isNumeric(string)) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            if (isNumeric(text)) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        private boolean isNumeric(String text) {
            return Pattern.matches("[0-9]\\d*", text);
        }

    }
}

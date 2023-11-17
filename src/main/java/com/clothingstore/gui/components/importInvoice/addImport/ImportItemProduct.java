package com.clothingstore.gui.components.importInvoice.addImport;

import javax.swing.*;
import javax.swing.text.*;

import com.clothingstore.models.ImportItemsModel;
import com.clothingstore.models.ProductModel;
import com.clothingstore.models.SizeItemModel;

import java.awt.*;
import java.util.regex.Pattern;

import java.util.Iterator;

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
    private JRadioButton[] sizeRadioButtons;

    private ProductModel productModel;
    private java.util.List<SizeItemModel> sizeItemList = AddNewImport.getSizeItemList();
    private java.util.List<ImportItemsModel> importItemList = AddNewImport.getImportItemList();
    private AddNewImport addNewImportPanel;

    public ImportItemProduct(ProductModel productModel, int i, AddNewImport addNewImportPanel) {
        this.productModel = productModel;
        this.addNewImportPanel = addNewImportPanel;
        initComponents(productModel, i);
        setBackground();
        handleEvent();
        updateDataForSize(1, 1);
        sizeSRadioButton.setSelected(true);
        sizeSQuantitySpinner.setEnabled(true);
    }

    private void setBackground() {
        setBackground(Color.WHITE);
        groupSizePanel.setBackground(Color.WHITE);
    }

    private void handleEvent() {
        initializeSpinners();
        addActionListeners();
        addChangeListeners();
        deleteButton.addActionListener(e -> hanleDelete());
        priceTextField.addActionListener(e -> updateData());
    }

    private void hanleDelete() {
        Iterator<ImportItemsModel> importIterator = importItemList.iterator();
        while (importIterator.hasNext()) {
            ImportItemsModel importItemsModel = importIterator.next();
            if (importItemsModel.getProduct_id() == productModel.getId()) {
                importIterator.remove();
            }
        }
    
        Iterator<SizeItemModel> sizeIterator = sizeItemList.iterator();
        while (sizeIterator.hasNext()) {
            SizeItemModel sizeItemModel = sizeIterator.next();
            if (sizeItemModel.getProductId() == productModel.getId()) {
                sizeIterator.remove();
            }
        }
    
        AddNewImport.setImportItemList(importItemList);
        AddNewImport.setSizeItemList(sizeItemList);
        addNewImportPanel.removeImportItem(this);
    
        int removedIndex = addNewImportPanel.getListImportItemProducts().indexOf(this);
        addNewImportPanel.updateSequenceNumbers(removedIndex);
    }
    
    private void initializeSpinners() {
        JSpinner[] sizeQuantitySpinners = { sizeSQuantitySpinner, sizeMQuantitySpinner, sizeLQuantitySpinner,
                sizeXLQuantitySpinner, sizeXXLQuantitySpinner };

        for (JSpinner spinner : sizeQuantitySpinners) {
            spinner.setEnabled(false);
            spinner.setValue(1);
        }
    }

    private void addActionListeners() {
        sizeRadioButtons = new JRadioButton[] { sizeSRadioButton, sizeMRadioButton, sizeLRadioButton,
                sizeXLRadioButton, sizeXXLRadioButton };

        for (JRadioButton radioButton : sizeRadioButtons) {
            radioButton.addActionListener(e -> updateQuantity());
        }
    }

    private void addChangeListeners() {
        JSpinner[] sizeQuantitySpinners = { sizeSQuantitySpinner, sizeMQuantitySpinner, sizeLQuantitySpinner,
                sizeXLQuantitySpinner, sizeXXLQuantitySpinner };

        for (JSpinner spinner : sizeQuantitySpinners) {
            spinner.addChangeListener(e -> {
                int value = (int) spinner.getValue();
                if (value < 1) {
                    spinner.setValue(1);
                }
                updateData();
            });
        }
    }

    private void updateQuantity() {
        updateData();
        JSpinner[] sizeQuantitySpinners = { sizeSQuantitySpinner, sizeMQuantitySpinner, sizeLQuantitySpinner,
                sizeXLQuantitySpinner, sizeXXLQuantitySpinner };

        for (int i = 0; i < sizeQuantitySpinners.length; i++) {
            sizeQuantitySpinners[i].setEnabled(sizeRadioButtons[i].isSelected());
        }
    }

    private void updateDataForSize(int sizeId, int quantitySize) {
        quantity += quantitySize;

        boolean checkExist = false;
        Iterator<SizeItemModel> sizeIterator = sizeItemList.iterator();
        Iterator<ImportItemsModel> importIterator = importItemList.iterator();

        while (sizeIterator.hasNext() && importIterator.hasNext()) {
            SizeItemModel sizeItemModel = sizeIterator.next();
            ImportItemsModel importItemsModel = importIterator.next();

            if (sizeItemModel.getProductId() == productModel.getId() && sizeItemModel.getSizeId() == sizeId) {
                sizeItemModel.setQuantity(quantitySize);
                checkExist = true;
            }

            if (importItemsModel.getProduct_id() == productModel.getId() && importItemsModel.getSize_id() == sizeId) {
                importItemsModel.setQuantity(quantitySize);
                importItemsModel.setPrice(Integer.parseInt(priceTextField.getText()));
                checkExist = true;
            }
        }

        if (!checkExist) {
            SizeItemModel sizeItemModel = new SizeItemModel();
            sizeItemModel.setProductId(productModel.getId());
            sizeItemModel.setQuantity(quantitySize);
            sizeItemModel.setSizeId(sizeId);
            sizeItemList.add(sizeItemModel);

            ImportItemsModel importItemsModel = new ImportItemsModel();
            importItemsModel.setProduct_id(productModel.getId());
            importItemsModel.setSize_id(sizeId);
            sizeItemModel.setQuantity(quantitySize);
            importItemsModel.setQuantity(quantitySize);
            importItemsModel.setPrice(Integer.parseInt(priceTextField.getText()));
            importItemList.add(importItemsModel);
        }
        AddNewImport.setImportItemList(importItemList);
        AddNewImport.setSizeItemList(sizeItemList);
    }

    private void updateData() {
        quantity = 0;

        int[] sizeIds = { 1, 2, 3, 4, 5 };
        JSpinner[] sizeQuantitySpinners = { sizeSQuantitySpinner, sizeMQuantitySpinner, sizeLQuantitySpinner,
                sizeXLQuantitySpinner, sizeXXLQuantitySpinner };

        for (int i = 0; i < sizeIds.length; i++) {
            if (sizeRadioButtons[i].isSelected()) {
                int quantitySize = (int) sizeQuantitySpinners[i].getValue();
                updateDataForSize(sizeIds[i], quantitySize);
            } else {
                Iterator<ImportItemsModel> iterator = importItemList.iterator();
                Iterator<SizeItemModel> iteratorSize = sizeItemList.iterator();

                while (iterator.hasNext() && iteratorSize.hasNext()) {
                    ImportItemsModel importItemsModel = iterator.next();
                    SizeItemModel sizeItemModel = iteratorSize.next();

                    if (importItemsModel.getProduct_id() == productModel.getId()
                            && importItemsModel.getSize_id() == sizeIds[i]) {
                        iterator.remove();
                    }

                    if (sizeItemModel.getProductId() == productModel.getId()
                            && sizeItemModel.getSizeId() == sizeIds[i]) {
                        iteratorSize.remove();
                    }
                }
            }
        }

        AddNewImport.setSizeItemList(sizeItemList);
        AddNewImport.setImportItemList(importItemList);

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

    public void updateSequenceNumber(int newNumber) {
        sttLabel.setText("" + newNumber);
    }
}
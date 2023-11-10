package com.clothingstore.gui.components.importInvoice;

import javax.swing.*;

import com.clothingstore.bus.ImportBUS;
import com.clothingstore.bus.ImportItemsBUS;
import com.clothingstore.bus.ProductBUS;
import com.clothingstore.dao.ProductDAO;
import com.clothingstore.models.ImportItemsModel;
import com.clothingstore.models.ImportModel;
import com.clothingstore.models.ProductModel;
import com.clothingstore.models.SizeItemModel;
import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class AddNewImport extends JPanel {

    private static AddNewImport instance;

    private JButton addNewProductButton;
    private JButton cancelImportButton;
    private JPanel contentPanel;
    private JPanel footerBottomPanel;
    private JPanel footerPanel;
    private JPanel footerTopPanel;
    private JPanel headerPanel;
    private JLabel idEmpLabel;
    private JTextField idEmpTextField;
    private JLabel idLabel;
    private JLabel idProductLabel;
    private JTextField idProductTextField;
    private JTextField idTextField;
    private JLabel importDateLabel;
    private JDateChooser importDateChooser;

    private JButton newImportButton;
    private JScrollPane listImportItemScrollPane;
    private JLabel titleLabel;
    private JPanel listImportItemPanel;
    private JButton saveButton;
    private JPanel groupButton;

    private int quantityImportItem = 1;
    private static java.util.List<ImportItemsModel> importItemList = new ArrayList<>();
    private static java.util.List<SizeItemModel> sizeItemList = new ArrayList<>();
    private ImportModel importModel = new ImportModel();

    public static AddNewImport getInstance() {
        if (instance == null) {
            instance = new AddNewImport();
        }
        return instance;
    }

    public AddNewImport() {
        initComponents();
        handleEvent();
    }

    private void handleEvent() {
        idProductTextField.addActionListener(e -> addNewProduct());
        saveButton.addActionListener(e -> addNewImport());
    }

    private void addNewImport() {
        int totalPrice = 0;
        for (ImportItemsModel importItemModel : importItemList) {
            totalPrice += importItemModel.getPrice() * importItemModel.getQuantity();
        }

        Date selectedDate = importDateChooser.getDate();

        LocalDateTime localDateTime = LocalDateTime.ofInstant(selectedDate.toInstant(), ZoneId.systemDefault());

        importModel = new ImportModel(0, Integer.parseInt(idEmpTextField.getText()), localDateTime, totalPrice);
        // importModel.setUserId(Integer.parseInt(idEmpTextField.getText()));
        // importModel.setTotalPrice(totalPrice);
        // importModel.setImportDate(localDateTime);

        int importId = ImportBUS.getInstance().addModel(importModel);
        ImportBUS.getInstance().refreshData();
        System.out.println(importId);
        java.util.List<ImportModel> importList = ImportBUS.getInstance().getAllModels();
        ImportModel importModel = importList.get(importList.size()-1);
        System.out.println(importModel.getId());
    }

    private void addNewProduct() {
        try {
            int idProduct = Integer.parseInt(idProductTextField.getText());
            ProductModel productModel = ProductBUS.getInstance().getModelById(idProduct);

            if (productModel == null) {
                SwingUtilities.invokeLater(
                        () -> JOptionPane.showMessageDialog(idProductTextField, "No model found for id: " + idProduct));
            } else {
                Boolean check = true;
                for (ImportItemsModel importItemModel : importItemList) {
                    if (importItemModel.getProduct_id() == idProduct) {
                        JOptionPane.showMessageDialog(idProductTextField, "Product is already imported");
                        check = false;
                        break;
                    }
                }
                if (check) {
                    SwingUtilities.invokeLater(() -> {
                        listImportItemPanel.add(new ImportItemProduct(productModel, quantityImportItem));
                        listImportItemScrollPane.setViewportView(listImportItemPanel);
                        quantityImportItem++;
                        idProductTextField.setText("");
                        revalidate();
                        repaint();
                    });
                }
            }
        } catch (NumberFormatException e) {
            SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(idProductTextField,
                    "Invalid idProduct. Please enter a valid integer."));
        }
    }

    private void initComponents() {

        headerPanel = new JPanel();
        titleLabel = new JLabel();
        newImportButton = new JButton();
        cancelImportButton = new JButton();
        contentPanel = new JPanel();
        idLabel = new JLabel();
        idTextField = new JTextField();
        idEmpLabel = new JLabel();
        idEmpTextField = new JTextField();
        importDateLabel = new JLabel();
        importDateChooser = new JDateChooser();
        footerPanel = new JPanel();
        footerTopPanel = new JPanel();
        idProductLabel = new JLabel();
        idProductTextField = new JTextField();
        idProductTextField.setPreferredSize(new Dimension(100, 20));
        addNewProductButton = new JButton();
        footerBottomPanel = new JPanel();
        listImportItemScrollPane = new JScrollPane();
        saveButton = new JButton("Save");
        groupButton = new JPanel();
        groupButton.setLayout(new FlowLayout());
        listImportItemPanel = new JPanel();
        listImportItemPanel.setLayout(new BoxLayout(listImportItemPanel, BoxLayout.Y_AXIS));

        setLayout(new BorderLayout());

        titleLabel.setText("Add Import");
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 20));
        headerPanel.add(titleLabel);

        newImportButton.setText("New Import");
        headerPanel.add(newImportButton);

        cancelImportButton.setText("Cancel ");
        // headerPanel.add(cancelImportButton);

        add(headerPanel, BorderLayout.PAGE_START);

        contentPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.EAST;

        idLabel.setText("Id import  ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(idLabel, gbc);

        idTextField.setColumns(10);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(idTextField, gbc);

        idEmpLabel.setText("Id Employee");
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPanel.add(idEmpLabel, gbc);

        idEmpTextField.setColumns(10);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(idEmpTextField, gbc);

        importDateLabel.setText("Import date");
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(importDateLabel, gbc);

        importDateChooser = new JDateChooser();
        importDateChooser.setPreferredSize(new Dimension(125, 20));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(importDateChooser, gbc);

        add(contentPanel, BorderLayout.CENTER);

        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.Y_AXIS));

        idProductLabel.setText("Id product");
        footerTopPanel.add(idProductLabel);
        footerTopPanel.add(idProductTextField);

        addNewProductButton.setText("Add new product");
        footerTopPanel.add(addNewProductButton);

        footerPanel.add(footerTopPanel);

        footerBottomPanel.setLayout(new BoxLayout(footerBottomPanel, BoxLayout.Y_AXIS));
        footerBottomPanel.add(new ImportItemAddHeader());

        listImportItemScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        listImportItemScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        footerPanel.add(footerBottomPanel);
        listImportItemScrollPane.setViewportView(listImportItemPanel);
        footerBottomPanel.add(listImportItemScrollPane);

        listImportItemScrollPane.setPreferredSize(new Dimension(1000, 200));

        listImportItemScrollPane.setMaximumSize(new Dimension(1000, 200));

        footerPanel.add(footerBottomPanel);
        footerPanel.add(listImportItemScrollPane);
        groupButton.add(cancelImportButton);
        groupButton.add(saveButton);
        footerPanel.add(groupButton);

        add(footerPanel, BorderLayout.PAGE_END);
    }

    public static java.util.List<ImportItemsModel> getImportItemList() {
        return importItemList;
    }

    public static void setImportItemList(java.util.List<ImportItemsModel> importItemList) {
        AddNewImport.importItemList = importItemList;
    }

    public static java.util.List<SizeItemModel> getSizeItemList() {
        return sizeItemList;
    }

    public static void setSizeItemList(java.util.List<SizeItemModel> sizeItemList) {
        AddNewImport.sizeItemList = sizeItemList;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clothing Store Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        AddNewImport addNewImportPanel = new AddNewImport();
        frame.add(addNewImportPanel);

        frame.setVisible(true);
    }

}

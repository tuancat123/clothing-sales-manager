package com.clothingstore.gui.components.importInvoice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ImportAdd extends JPanel {

    private static ImportAdd instance;

    private JLabel userIdLabel;
    private JTextField userIdField;
    private JLabel importDateLabel;
    private JTextField importDateField;
    private JLabel totalPriceLabel;
    private JTextField totalPriceField;

    private JButton saveButton;

    public static ImportAdd getInstance() {
        if (instance == null) {
            instance = new ImportAdd();
        }
        return instance;
    }

    private ImportAdd() {
        initComponents();
        initData();
    }

    private void initData() {
    }

    private void initComponents() {
        setLayout(new GridLayout(4, 2));

        userIdLabel = new JLabel("User ID:");
        userIdField = new JTextField();

        importDateLabel = new JLabel("Import Date (YYYY-MM-DD HH:MM:SS):");
        importDateField = new JTextField();

        totalPriceLabel = new JLabel("Total Price:");
        totalPriceField = new JTextField();

        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        add(userIdLabel);
        add(userIdField);
        add(importDateLabel);
        add(importDateField);
        add(totalPriceLabel);
        add(totalPriceField);
        add(new JPanel());
        add(saveButton);
    }

}

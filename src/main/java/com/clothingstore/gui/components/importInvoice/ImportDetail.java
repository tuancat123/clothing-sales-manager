package com.clothingstore.gui.components.importInvoice;

import javax.swing.*;

import com.clothingstore.gui.components.invoiceDetail.HeaderInvoice;
import com.clothingstore.gui.components.invoiceDetail.Product;

import java.awt.*;
import java.util.ArrayList;


public class ImportDetail extends JPanel {

    private String name;
    private String value;

    private static ImportDetail instance;

    public static ImportDetail getInstance() {
        if (instance == null) {
            instance = new ImportDetail();
        }
        return instance;
    }

    public ImportDetail(){
        initComponents();
    }
    
    public ImportDetail(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static ArrayList<ImportDetail> getData() {
        ArrayList<ImportDetail> data = new ArrayList<ImportDetail>() {{
            add(new ImportDetail("Id Invoice", "0936622"));
            add(new ImportDetail("Date", "23/8/2023"));
            add(new ImportDetail("Products", "6"));
            add(new ImportDetail("Total", "300.450.444"));
        }};
        return data;
    }

    public void initComponents(){
        NamePanel = new JLabel();
        Info = new JPanel();
        Products = new JPanel();
        Scroll = new JScrollPane();
        Product = new JPanel();
        HeaderProducts = new JPanel();

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        setLayout(new BorderLayout());
        setBackground(new Color(153, 194, 255));

        Scroll.setViewportView(mainPanel);

        Info.setLayout(new GridLayout(0, 1));

        NamePanel.setText("-- Detail --");
        NamePanel.setHorizontalAlignment(SwingConstants.CENTER);
        NamePanel.setVerticalAlignment(SwingConstants.CENTER);
        NamePanel.setFont(new Font("Segoe UI", 1, 17));
        NamePanel.setPreferredSize(new Dimension(150,70));

        add(NamePanel,BorderLayout.NORTH);

        for(ImportDetail ImportDetail : getData()){
            JPanel panel = new JPanel();
            panel.setBackground(Color.WHITE);
            panel.setPreferredSize(new Dimension(60, 60));
            panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
            panel.setLayout(new BorderLayout());

            JLabel Name = new JLabel(ImportDetail.name);
            Name.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
            Name.setFont(new Font("Segoe UI", 1, 14));
            panel.add(Name, BorderLayout.WEST);

            JLabel Value = new JLabel(ImportDetail.value);
            Value.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 19));
            Value.setFont(new Font("Segoe UI", 0, 14));
            panel.add(Value, BorderLayout.EAST);

            Info.add(panel);
        }
        Products.setLayout(new BorderLayout());

        HeaderProducts.setLayout(new BorderLayout());
        HeaderProducts.setPreferredSize(new Dimension(100,40));
        HeaderProducts.add(HeaderInvoice.getInstance(), BorderLayout.CENTER);

        Product.setLayout(new GridLayout(5,1));
        for(int i = 0; i< 5;i++){
            Product product = new Product();
            Product.add(product);
        }
        Products.add(HeaderProducts, BorderLayout.NORTH);
        Products.add(Product, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 10);

        mainPanel.add(Info, BorderLayout.CENTER);
        mainPanel.add(Products, BorderLayout.SOUTH);
        add(Scroll, BorderLayout.CENTER);
    }

    private JPanel mainPanel;
    private JLabel NamePanel;
    private JPanel Info;
    private JPanel Products;
    private JScrollPane Scroll;
    private JPanel Product;
    private JPanel HeaderProducts;
}

package com.clothingstore.gui.components.customerList;

import javax.swing.*;

import com.clothingstore.bus.OrderBUS;
import com.clothingstore.bus.PointBUS;
import com.clothingstore.models.CustomerModel;
import com.clothingstore.models.PointModel;

import java.awt.*;
import java.util.ArrayList;


public class CustomerDetail extends JPanel {

    private String name;
    private String value;
    CustomerModel customerModel;
    PointModel pointModel;

    public CustomerDetail(CustomerModel customerModel){
        this.customerModel= customerModel;
        // lỗi logic data base
        // this.pointModel = PointBUS.getInstance().searchModel(String.valueOf(customerModel.getId()), new String[]{"customer_id"}).get(0);
        initComponents();
    }
    
    public CustomerDetail(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public  ArrayList<CustomerDetail> getData() {
        ArrayList<CustomerDetail> data = new ArrayList<CustomerDetail>() {{
            add(new CustomerDetail("Invoice Id",String.valueOf(customerModel.getId())));
            add(new CustomerDetail("Customer Name", customerModel.getCustomerName()));
            add(new CustomerDetail("Phone", customerModel.getPhone()));
            add(new CustomerDetail("Email",customerModel.getEmail() ));
            add(new CustomerDetail("Point Earned", String.valueOf(4)));
            add(new CustomerDetail("Point Used", String.valueOf(5)));
            add(new CustomerDetail("Invoice Quantity", String.valueOf(OrderBUS.getInstance().searchModel(String.valueOf(customerModel.getId()), new String[]{"customer_id"}).size())));
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

        for(CustomerDetail CustomerDetail : getData()){
            JPanel panel = new JPanel();
            panel.setBackground(Color.WHITE);
            panel.setPreferredSize(new Dimension(60, 60));
            panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
            panel.setLayout(new BorderLayout());

            JLabel Name = new JLabel(CustomerDetail.name);
            Name.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
            Name.setFont(new Font("Segoe UI", 1, 14));
            panel.add(Name, BorderLayout.WEST);

            JLabel Value = new JLabel(CustomerDetail.value);
            Value.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 19));
            Value.setFont(new Font("Segoe UI", 0, 14));
            panel.add(Value, BorderLayout.EAST);

            Info.add(panel);
        }
        Products.setLayout(new BorderLayout());

        HeaderProducts.setLayout(new BorderLayout());
        HeaderProducts.setPreferredSize(new Dimension(100,40));
        Invoice headerInvoice = new Invoice();
        HeaderProducts.add(headerInvoice, BorderLayout.CENTER);

        Product.setLayout(new GridLayout(5,1));
        for(int i = 0; i< 5;i++){
            Invoice invoice = new Invoice("001", "30/4/2003", "4", "3.000.000 đ");
            Product.add(invoice);
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

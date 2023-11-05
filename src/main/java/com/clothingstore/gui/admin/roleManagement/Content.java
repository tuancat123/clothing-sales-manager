package com.clothingstore.gui.admin.roleManagement;

import java.awt.*;

import javax.swing.*;

public class Content extends JPanel {

    private static Content instance;

    public static Content getInstance() {
        if (instance == null) {
            instance = new Content();
        }
        return instance;
    }


    public Content() {
        initComponents();
    }

    private void initComponents() {

        MainPanel = new JPanel();
        Cards = new JPanel();
        Employee = new JPanel();
        AdminPanel = new JPanel();
        AdminTitle = new JLabel("Admin");
        Invoices = new JPanel();
        Invoices1 = new JPanel();
        Invoices3 = new JPanel();
        InvoiceList = new JPanel();
        InvoiceList1 = new JPanel();
        InvoiceList2 = new JPanel();
        Scroll = new JScrollPane();
        Scroll2 = new JScrollPane();
        Scroll3 = new JScrollPane();
        EmployeeList = new JPanel();
        ManagerPanel = new JPanel();
        ManagerTitle = new JLabel();
        ManagerTitle.setText("Manager");
        EmployeePanel = new JPanel();
        EmployeeTitle = new JLabel();
        EmployeeTitle.setText("Employee");

        
        Color backgroundColor = new Color(0, 38, 77);

        MainPanel.setLayout(new BorderLayout());
        Cards.setBackground(backgroundColor);
        Cards.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 8));

    //admin
        AdminPanel.setLayout(new BorderLayout());
        AdminPanel.setBackground(backgroundColor);
        AdminPanel.setPreferredSize(new Dimension(300,50));
        AdminTitle.setFont(new Font("Segoe UI", 1, 16)); 
        AdminTitle.setForeground(Color.WHITE);
        AdminTitle.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 1));
        AdminPanel.add(AdminTitle, BorderLayout.NORTH);
        
        Invoices.setLayout(new BorderLayout());

        InvoiceList.setLayout(new GridLayout(0,1));
        for(int i = 0; i< 11; i++){
            Invoice invoice = new Invoice();
            invoice.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 1));
            InvoiceList.add(invoice);
        }
        setLayout(new GridLayout(0, 1, 0, 0));
        
        Scroll.setViewportView(InvoiceList);
        Invoices.add(Scroll, BorderLayout.CENTER);

        Invoice invoiceHeader = new Invoice("Permisions");
        invoiceHeader.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 12));
        Invoices.add(invoiceHeader, BorderLayout.NORTH);
        
        AdminPanel.add(Invoices, BorderLayout.CENTER);
        MainPanel.add(AdminPanel, BorderLayout.WEST);

        add(MainPanel, BorderLayout.WEST);
        
//manager
        ManagerPanel.setLayout(new BorderLayout());
        ManagerPanel.setBackground(backgroundColor);
        ManagerPanel.setPreferredSize(new Dimension(376,50));
        ManagerTitle.setFont(new Font("Segoe UI", 1, 16)); 
        ManagerTitle.setForeground(Color.WHITE);
        ManagerTitle.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 1));
        ManagerPanel.add(ManagerTitle, BorderLayout.NORTH);
        
        Invoices1.setLayout(new BorderLayout());

        InvoiceList1.setLayout(new GridLayout(0,1));
        for(int i = 0; i< 11; i++){
            Invoice invoice = new Invoice();
            invoice.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 1));
            InvoiceList1.add(invoice);
        }
        
        Scroll2.setViewportView(InvoiceList1);
        Invoices1.add(Scroll2, BorderLayout.CENTER);

        Invoice invoiceHeader1 = new Invoice("Permisions");
        invoiceHeader1.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 12));
        Invoices1.add(invoiceHeader1, BorderLayout.NORTH);
        
        ManagerPanel.add(Invoices1, BorderLayout.CENTER);
        MainPanel.add(ManagerPanel, BorderLayout.CENTER);

        add(MainPanel, BorderLayout.CENTER);
        
      //employee
        EmployeePanel.setLayout(new BorderLayout());
        EmployeePanel.setBackground(backgroundColor);
        EmployeePanel.setPreferredSize(new Dimension(300,50));
        EmployeeTitle.setFont(new Font("Segoe UI", 1, 16)); 
        EmployeeTitle.setForeground(Color.WHITE);
        EmployeeTitle.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 1));
        EmployeePanel.add(EmployeeTitle, BorderLayout.NORTH);
        
        Invoices3.setLayout(new BorderLayout());

        InvoiceList2.setLayout(new GridLayout(0,1));
        for(int i = 0; i< 11; i++){
            Invoice invoice = new Invoice();
            invoice.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 1));
            InvoiceList2.add(invoice);
        }
        
        Scroll3.setViewportView(InvoiceList2);
        Invoices3.add(Scroll3, BorderLayout.CENTER);

        Invoice invoiceHeader2 = new Invoice("Permisions");
        invoiceHeader2.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 12));
        Invoices3.add(invoiceHeader2, BorderLayout.NORTH);
        
        EmployeePanel.add(Invoices3, BorderLayout.CENTER);
        MainPanel.add(EmployeePanel, BorderLayout.EAST);

        add(MainPanel, BorderLayout.EAST);
        
        
       


    }

    private JPanel Cards;
    private JPanel MainPanel;
    private JPanel AdminPanel;
    private JLabel AdminTitle;
    private JPanel Invoices;
    private JPanel Invoices1;
    private JPanel InvoiceList;
    private JPanel InvoiceList1;
    private JPanel InvoiceList2;
    private JScrollPane Scroll;
    private JPanel Employee;
    private JPanel EmployeeList;
    private JScrollPane Scroll2;
    private JPanel ManagerPanel;
    private JPanel EmployeePanel;
    private JLabel ManagerTitle;
    private JLabel EmployeeTitle;
    private JPanel Invoices3;
    private JScrollPane Scroll3;

}

package com.clothingstore.gui.components.importInvoice;

import javax.swing.*;

import com.clothingstore.bus.OrderBUS;
import com.clothingstore.bus.OrderItemBUS;
import com.clothingstore.gui.components.invoiceDetail.HeaderInvoice;
import com.clothingstore.gui.components.invoiceDetail.Product;
import com.google.protobuf.Value;

import java.awt.*;
import java.awt.event.*;
import java.util.jar.Attributes.Name;

public class AddImport extends JFrame {

    String[] title = {"Employee Name", "Employee Id"};

    public AddImport() {
        initComponents();
        setVisible(true);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        Content = new JPanel();
        Header = new JPanel();
        EmployeeInfo = new JPanel();
        NameFrame = new JLabel("Add Import");
        EmployeeNameLabel = new JLabel("Employee Name");
        EmployeeName = new JTextField();
        EmployeeIdLabel = new JLabel("Employee Id");
        EmployeeId = new JTextField();
        ValueSearch = new JTextField();
        ButtonSearch = new JButton();
        NameProduct = new JLabel();
        IdProduct = new JLabel();
        Size = new JComboBox<>();
        Quantity = new JTextField();
        ButtonSave = new JButton("Save");
        Scroll = new JScrollPane();
        Products = new JPanel();

        setLayout(new BorderLayout());
        setSize(800, 500);
        setPreferredSize(new Dimension(800, 500));


        // employee info panel
        EmployeeInfo.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.EAST;
        c.gridwidth = 2;
        c.ipady = 30;
        c.gridx = 0;
        EmployeeInfo.add(NameFrame, c);

        c.gridy = 1;
        c.ipady= 3;
        c.gridwidth = 1;
        c.ipadx=15;
        EmployeeInfo.add(EmployeeNameLabel, c);
        
        c.gridx = 1;
        EmployeeName.setPreferredSize(new Dimension(130,25));
        EmployeeInfo.add(EmployeeName, c);

        c.gridy = 2;
        c.gridx = 0;
        EmployeeInfo.add(EmployeeIdLabel, c);
        
        c.gridx = 1;
        EmployeeId.setPreferredSize(new Dimension(130,25));
        EmployeeInfo.add(EmployeeId, c);

        Header.setLayout(new BorderLayout());
        Header.add(EmployeeInfo, BorderLayout.NORTH);
        Content.setLayout(new BorderLayout());
        Content.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // search products panel
        JPanel Panel = new JPanel();
        Panel.setLayout(new GridBagLayout());

        ValueSearch.setPreferredSize(new Dimension(130,25));
        Panel.add(ValueSearch);

        ButtonSearch.setIcon(new ImageIcon(getClass().getResource("/config/icon/search.png")));
        ButtonSearch.setPreferredSize(new Dimension(25,25));
        Panel.add(ButtonSearch);

        Header.add(Panel, BorderLayout.CENTER);

        // info new product

        JPanel Panel2 = new JPanel();
        Panel2.setLayout(new GridBagLayout());

        Panel2.add(NameProduct);
        Panel2.add(IdProduct);
        Panel2.add(Size);
        Panel2.add(Quantity);
        Panel2.add(ButtonSave);
        Header.add(Panel2,BorderLayout.SOUTH);

        // products
        Products.setLayout(new GridLayout(0,1));
        HeaderInvoice EmployeeInfoInvoice = new HeaderInvoice();
        EmployeeInfoInvoice.setPreferredSize(new Dimension(30,60));
        Products.add(EmployeeInfoInvoice);
        for(int i =0; i<9;i++){
            Product product = new Product(OrderBUS.getInstance().getModelById(1), OrderItemBUS.getInstance().getModelById(1));
            product.setPreferredSize(new Dimension(30,60));
            Products.add(product);
        }
        Scroll.setViewportView(Products);
        Content.add(Scroll, BorderLayout.CENTER);

        add(Header, BorderLayout.NORTH);
        add(Content, BorderLayout.CENTER);
        
        
    }
    private JPanel Content;
    private JPanel Header;
    private JPanel EmployeeInfo;
    private JLabel NameFrame;
    private JLabel EmployeeNameLabel;
    private JTextField EmployeeName;
    private JLabel EmployeeIdLabel;
    private JTextField EmployeeId;
    private JTextField ValueSearch;
    private JButton ButtonSearch;
    private JLabel NameProduct;
    private JLabel IdProduct;
    private JComboBox<String> Size;
    private JTextField Quantity;
    private JButton ButtonSave;
    private JScrollPane Scroll;
    private JPanel Products;

    public static void main(String[] args) {
        AddImport addImport = new AddImport();
        addImport.setVisible(true);
    }
}

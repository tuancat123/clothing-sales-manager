package com.clothingstore.gui.customer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.netbeans.lib.awtextra.*;


public class InvoiceDetail extends JFrame {

    public InvoiceDetail() {
        initComponents();
        CustomerInfo.setVisible(false);
        CustomerPanel.add(Maxim, new AbsoluteConstraints(10, 40, 500, 120));
    }

    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        Header = new JPanel();
        NameFrame = new JLabel();
        Id = new JLabel();
        Content = new JPanel();
        GoodsHeader = new JPanel();
        Scroll = new JScrollPane();
        Goods = new JPanel();
        Footer = new JPanel();
        CustomerPanel = new JPanel();
        RegularCus = new JCheckBox();
        WalkInCus = new JCheckBox();
        CustomerInfo = new JPanel();
        NameText = new JLabel();
        Point = new JCheckBox();
        PhoneText = new JLabel();
        Name = new JTextField();
        Phone = new JTextField();
        UsePoint = new JTextField();
        Buttons = new JPanel();
        ButtonExit = new JButton();
        ButtonPay = new JButton();
        PayPanel = new JPanel();
        TotalText = new JLabel();
        QuantityText = new JLabel();
        ProductsText = new JLabel();
        PointText = new JLabel();
        Total = new JTextField();
        Quantity = new JTextField();
        TotalInvoice = new JTextField();
        Discount = new JTextField();
        Maxim = new JLabel("Khách Hàng là Ông Nội");

        setPreferredSize(new Dimension(800, 550));

        Header.setLayout(new AbsoluteLayout());

        NameFrame.setFont(new Font("Segoe UI", 1, 24)); 
        NameFrame.setText("Invoice details");
        Header.add(NameFrame, new AbsoluteConstraints(10, 0, 410, 30));

        Id.setFont(new Font("Segoe UI", 0, 14)); 
        Id.setForeground(new Color(0, 51, 255));
        Id.setText("id: 347389922");
        Header.add(Id, new AbsoluteConstraints(10, 30, 160, 20));

        getContentPane().add(Header, BorderLayout.PAGE_START);

        Content.setLayout(new BorderLayout());

        GoodsHeader.setBorder(BorderFactory.createEtchedBorder());
        GoodsHeader.setPreferredSize(new Dimension(800, 40));
        GoodsHeader.setLayout(new BorderLayout());
        InvDetailHeader invDetailHeader = new InvDetailHeader();
        GoodsHeader.add(invDetailHeader, BorderLayout.CENTER);
        Content.add(GoodsHeader, BorderLayout.PAGE_START);

        Goods.setBackground(new Color(255, 255, 255));
        Goods.setLayout(new GridLayout(1, 0));
        Scroll.setViewportView(Goods);

        Content.add(Scroll, BorderLayout.CENTER);

        getContentPane().add(Content, BorderLayout.CENTER);

        Footer.setLayout(new BorderLayout());

        CustomerPanel.setLayout(new AbsoluteLayout());

        RegularCus.setFont(new Font("Segoe UI", 1, 15)); 
        RegularCus.setForeground(new Color(0, 102, 102));
        RegularCus.setText("Regular customers");
        RegularCus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(RegularCus.isSelected()){
                    WalkInCus.setSelected(false);
                    CustomerInfo.setVisible(true);
                    Maxim.setVisible(false);
                }            
            }
        });
        CustomerPanel.add(RegularCus, new AbsoluteConstraints(210, 10, 170, -1));

        WalkInCus.setFont(new Font("Segoe UI", 1, 15)); 
        WalkInCus.setSelected(true);
        WalkInCus.setText("Walk-in customers");
        WalkInCus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(WalkInCus.isSelected()){
                    RegularCus.setSelected(false);
                    CustomerInfo.setVisible(false);
                    Maxim.setVisible(true);
                }
            }
        });
        CustomerPanel.add(WalkInCus, new AbsoluteConstraints(30, 10, 170, -1));

        CustomerInfo.setLayout(new AbsoluteLayout());

        NameText.setFont(new Font("Segoe UI", 3, 14)); 
        NameText.setText("Name:");
        CustomerInfo.add(NameText, new AbsoluteConstraints(240, 20, -1, -1));

        Point.setFont(new Font("Segoe UI", 0, 14)); 
        Point.setForeground(new Color(255, 102, 0));
        Point.setText("3000 Point");
        CustomerInfo.add(Point, new AbsoluteConstraints(70, 50, -1, 20));

        PhoneText.setFont(new Font("Segoe UI", 3, 14)); 
        PhoneText.setText("Phone: ");
        CustomerInfo.add(PhoneText, new AbsoluteConstraints(20, 20, -1, -1));

        Name.setFont(new Font("Segoe UI", 0, 14)); 
        Name.setHorizontalAlignment(JTextField.RIGHT);
        CustomerInfo.add(Name, new AbsoluteConstraints(300, 20, 170, 20));

        Phone.setFont(new Font("Segoe UI", 0, 14)); 
        Phone.setHorizontalAlignment(JTextField.RIGHT);
        CustomerInfo.add(Phone, new AbsoluteConstraints(80, 20, 140, 20));

        UsePoint.setFont(new Font("Segoe UI", 0, 14)); 
        UsePoint.setHorizontalAlignment(JTextField.RIGHT);
        CustomerInfo.add(UsePoint, new AbsoluteConstraints(190, 50, 150, 20));

        CustomerPanel.add(CustomerInfo, new AbsoluteConstraints(10, 40, 500, 120));

        Footer.add(CustomerPanel, BorderLayout.CENTER);

        Buttons.setBorder(BorderFactory.createEtchedBorder());
        Buttons.setLayout(new GridBagLayout());

        ButtonExit.setBackground(new Color(153, 255, 255));
        ButtonExit.setFont(new Font("Segoe UI", 1, 14)); 
        ButtonExit.setText("Exit");
        ButtonExit.setPreferredSize(new Dimension(80, 30));
        ButtonExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            }
        });
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(10, 10, 10, 40);
        Buttons.add(ButtonExit, gridBagConstraints);

        ButtonPay.setBackground(new Color(153, 255, 255));
        ButtonPay.setFont(new Font("Segoe UI", 1, 14)); 
        ButtonPay.setText("Pay");
        ButtonPay.setPreferredSize(new Dimension(80, 30));
        Buttons.add(ButtonPay, new GridBagConstraints());

        Footer.add(Buttons, BorderLayout.PAGE_END);

        PayPanel.setPreferredSize(new Dimension(280, 170));
        PayPanel.setLayout(new AbsoluteLayout());

        TotalText.setFont(new Font("Segoe UI", 3, 14)); 
        TotalText.setForeground(new Color(255, 0, 51));
        TotalText.setHorizontalAlignment(SwingConstants.RIGHT);
        TotalText.setText("Total:");
        PayPanel.add(TotalText, new AbsoluteConstraints(10, 130, 130, 30));

        QuantityText.setFont(new Font("Segoe UI", 3, 14)); 
        QuantityText.setForeground(new Color(51, 51, 255));
        QuantityText.setHorizontalAlignment(SwingConstants.RIGHT);
        QuantityText.setText("QuantityProduct: ");
        PayPanel.add(QuantityText, new AbsoluteConstraints(10, 10, 130, 30));

        ProductsText.setFont(new Font("Segoe UI", 3, 14)); 
        ProductsText.setForeground(new Color(51, 51, 255));
        ProductsText.setHorizontalAlignment(SwingConstants.RIGHT);
        ProductsText.setText("Total Invoice:");
        PayPanel.add(ProductsText, new AbsoluteConstraints(10, 50, 130, 30));

        PointText.setFont(new Font("Segoe UI", 3, 14)); 
        PointText.setForeground(new Color(51, 51, 255));
        PointText.setHorizontalAlignment(SwingConstants.RIGHT);
        PointText.setText("Point:");
        PayPanel.add(PointText, new AbsoluteConstraints(10, 90, 130, 30));

        Total.setFont(new Font("Segoe UI", 0, 15)); 
        Total.setForeground(new Color(255, 51, 51));
        Total.setText("30.000.000");
        PayPanel.add(Total, new AbsoluteConstraints(160, 130, 110, 30));

        Quantity.setFont(new Font("Segoe UI", 0, 14)); 
        Quantity.setForeground(new Color(255, 51, 255));
        Quantity.setText("4344");
        PayPanel.add(Quantity, new AbsoluteConstraints(160, 10, 110, 30));

        TotalInvoice.setFont(new Font("Segoe UI", 0, 14)); 
        TotalInvoice.setText("4");
        PayPanel.add(TotalInvoice, new AbsoluteConstraints(160, 50, 110, 30));

        Discount.setFont(new Font("Segoe UI", 0, 14)); 
        Discount.setForeground(new Color(153, 0, 51));
        Discount.setText("3000");
        PayPanel.add(Discount, new AbsoluteConstraints(160, 90, 110, 30));

        Footer.add(PayPanel, BorderLayout.EAST);

        getContentPane().add(Footer, BorderLayout.PAGE_END);

        pack();
    }

    private JButton ButtonExit;
    private JButton ButtonPay;
    private JPanel Buttons;
    private JPanel Content;
    private JPanel CustomerInfo;
    private JPanel CustomerPanel;
    private JTextField Discount;
    private JPanel Footer;
    private JPanel Goods;
    private JPanel GoodsHeader;
    private JPanel Header;
    private JLabel Id;
    private JTextField Name;
    private JLabel NameFrame;
    private JLabel NameText;
    private JPanel PayPanel;
    private JTextField Phone;
    private JLabel PhoneText;
    private JCheckBox Point;
    private JLabel PointText;
    private JLabel ProductsText;
    private JTextField Quantity;
    private JLabel QuantityText;
    private JCheckBox RegularCus;
    private JScrollPane Scroll;
    private JTextField Total;
    private JTextField TotalInvoice;
    private JLabel TotalText;
    private JTextField UsePoint;
    private JCheckBox WalkInCus;
    private JLabel Maxim;
}

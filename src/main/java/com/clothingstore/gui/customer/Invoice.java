package com.clothingstore.gui.customer;

import java.awt.*;
import javax.swing.*;
import org.netbeans.lib.awtextra.*;

public class Invoice extends JPanel {

    public Invoice() {
        initComponents();
    }

    private void initComponents() {

        Header = new JPanel();
        Index = new JLabel();
        None = new JLabel();
        NameShop = new JTextField();
        Content = new JPanel();
        Scroll = new JScrollPane();
        Invoices = new JPanel();
        Footer = new JPanel();
        TextSum = new JLabel();
        Value = new JLabel();
        ButtonCancel = new JButton();
        ButtonPay = new JButton();
        ButtonPrint = new JLabel();

        setPreferredSize(new Dimension(350, 370));
        setLayout(new BorderLayout());

        Header.setBackground(Color.BLACK);
        Header.setLayout(new BorderLayout());

        Index.setFont(new Font("Segoe UI", 0, 14));
        Index.setHorizontalAlignment(SwingConstants.CENTER);
        Index.setText("45");
        Index.setPreferredSize(new Dimension(30, 45));

        None.setPreferredSize(new Dimension(30, 45));

        NameShop.setEditable(false);
        NameShop.setFont(new Font("Segoe UI", 1, 18));
        NameShop.setHorizontalAlignment(JTextField.CENTER);
        NameShop.setText("The Shop VIP");

        Content.setLayout(new BorderLayout());

        Invoices.setBackground(new Color(255, 255, 255));
        Invoices.setLayout(new GridLayout(10, 1));

        Goods goods = new Goods();
        Invoices.add(goods);
        Goods goods2 = new Goods();
        Invoices.add(goods2);

        Scroll.setViewportView(Invoices);

        Footer.setPreferredSize(new Dimension(301, 120));
        Footer.setLayout(new AbsoluteLayout());

        TextSum.setFont(new Font("Segoe UI", 1, 13));
        TextSum.setHorizontalAlignment(SwingConstants.CENTER);
        TextSum.setText("Tổng Thanh Toán");

        Value.setFont(new Font("Segoe UI", 0, 18));
        Value.setForeground(new Color(255, 51, 51));
        Value.setHorizontalAlignment(SwingConstants.CENTER);
        Value.setText("3.000.000");

        ButtonCancel.setText("Hủy");
        ButtonCancel.setPreferredSize(new Dimension(72, 20));

        ButtonPay.setFont(new Font("Segoe UI", 0, 10));
        ButtonPay.setText("Thanh Toán");
        ButtonPay.setPreferredSize(new Dimension(72, 20));

        ButtonPrint.setIcon(new ImageIcon(getClass().getResource("/config/icon/printer.png")));

        Header.add(Index, BorderLayout.LINE_START);
        Header.add(None, BorderLayout.LINE_END);
        Header.add(NameShop, BorderLayout.CENTER);
        add(Header, BorderLayout.PAGE_START);

        Content.add(Scroll, BorderLayout.CENTER);
        add(Content, BorderLayout.CENTER);

        Footer.add(TextSum, new AbsoluteConstraints(0, 20, 120, 30));
        Footer.add(Value, new AbsoluteConstraints(0, 40, 120, 30));
        Footer.add(ButtonCancel, new AbsoluteConstraints(30, 80, -1, 30));
        Footer.add(ButtonPay, new AbsoluteConstraints(140, 80, 90, 30));
        Footer.add(ButtonPrint, new AbsoluteConstraints(190, 30, -1, -1));
        add(Footer, BorderLayout.SOUTH);

    }

    private JButton ButtonCancel;
    private JButton ButtonPay;
    private JLabel ButtonPrint;
    private JPanel Content;
    private JPanel Footer;
    private JPanel Header;
    private JLabel Index;
    private JPanel Invoices;
    private JTextField NameShop;
    private JLabel None;
    private JScrollPane Scroll;
    private JLabel TextSum;
    private JLabel Value;
}

package com.clothingstore.gui.components.customerList;

import java.awt.*;
import javax.swing.*;
import services.Authentication;

public class CustomerList extends JPanel {

    private static CustomerList instance;

    public static CustomerList getInstance() {
        if (instance == null) {
            instance = new CustomerList();
        }
        return instance;
    }

    public CustomerList() {
        initComponents();
    }

    private void initComponents() {

        Header = new JPanel();
        NameHeader = new JPanel();
        NamePanel = new JLabel();
        ButtonBack = new JButton();
        Panel = new JPanel();
        ButtonSearch = new JButton();
        SearchValue = new JTextField();
        Customers = new JPanel();
        Scroll = new JScrollPane();

        Color color = new Color(179, 209, 255);

        setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 4));
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(322, 170));
        setBackground(color);

        Header.setLayout(new BorderLayout());
        Header.setPreferredSize(new Dimension(250, 65));
        Header.setBackground(color);

        NameHeader.setLayout(new BorderLayout());

        NamePanel.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
        NamePanel.setHorizontalAlignment(SwingConstants.CENTER);
        NamePanel.setText("Customer List");
        NameHeader.setBackground(color);
        NameHeader.add(NamePanel, BorderLayout.CENTER);

        ButtonBack.setBackground(new Color(242, 242, 242));
        ButtonBack.setIcon(new ImageIcon(getClass().getResource("/config/icon/back.png"))); // NOI18N
        ButtonBack.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 1));
        NameHeader.add(ButtonBack, BorderLayout.LINE_START);

        Header.add(NameHeader, BorderLayout.NORTH);

        Panel.setBorder(BorderFactory.createEmptyBorder(4, 4, 7, 1));
        Panel.setLayout(new BorderLayout());
        Panel.setBackground(color);

        ButtonSearch.setIcon(new ImageIcon(getClass().getResource("/config/icon/search.png"))); // NOI18N
        ButtonSearch.setBorder(null);
        ButtonSearch.setBackground(Color.WHITE);
        Panel.add(ButtonSearch, BorderLayout.WEST);

        SearchValue.setBackground(new Color(242, 242, 242));
        SearchValue.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        SearchValue.setText("Tìm theo mã khách hàng");
        SearchValue.setBackground(Color.white);
        SearchValue.setBorder(BorderFactory.createEmptyBorder(1, 6, 1, 1));
        Panel.add(SearchValue, BorderLayout.CENTER);

        Header.add(Panel, BorderLayout.SOUTH);

        add(Header, BorderLayout.PAGE_START);

        Customers.setLayout(new GridLayout(0, 1));
        Customers.setBackground(color);
        for (int i = 0; i < 10; i++) {
            Customer customer = new Customer();
            Customers.add(customer);
        }

        Scroll.setViewportView(Customers);

        add(Scroll, BorderLayout.CENTER);
    }

    private JButton ButtonBack;
    private JButton ButtonSearch;
    private JPanel Header;
    private JPanel Customers;
    private JPanel NameHeader;
    private JLabel NamePanel;
    private JTextField SearchValue;
    private JPanel Panel;
    private JScrollPane Scroll;
}

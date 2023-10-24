package com.clothingstore.gui.components.invoicesHistory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;

public class HistoryList extends javax.swing.JPanel {

    private static HistoryList instance;

    public static HistoryList getInstance() {
        if (instance == null) {
            instance = new HistoryList();
        }
        return instance;
    }

    public HistoryList() {
        initComponents();
    }

    private void initComponents() {

        Header = new javax.swing.JPanel();
        NameHeader = new javax.swing.JPanel();
        NamePanel = new javax.swing.JLabel();
        ButtonBack = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        ButtonSearch = new javax.swing.JButton();
        SearchValue = new javax.swing.JTextField();
        Invoices = new javax.swing.JPanel();

        Color color = new Color(179, 209, 255);

        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 4));
        setLayout(new java.awt.BorderLayout());
        setPreferredSize(new Dimension(250, 150));
        setBackground(color);

        Header.setLayout(new java.awt.BorderLayout());
        Header.setPreferredSize(new Dimension(250, 65));
        Header.setBackground(color);

        NameHeader.setLayout(new java.awt.BorderLayout());

        NamePanel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        NamePanel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NamePanel.setText("Hoạt Động");
        NameHeader.setBackground(color);
        NameHeader.add(NamePanel, java.awt.BorderLayout.CENTER);

        ButtonBack.setBackground(new java.awt.Color(242, 242, 242));
        ButtonBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/config/icon/back.png"))); // NOI18N
        ButtonBack.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        NameHeader.add(ButtonBack, java.awt.BorderLayout.LINE_START);

        Header.add(NameHeader, java.awt.BorderLayout.NORTH);

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 1, 1));
        jPanel4.setLayout(new java.awt.BorderLayout());
        jPanel4.setBackground(color);

        ButtonSearch.setBackground(new java.awt.Color(242, 242, 242));
        ButtonSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/config/icon/search.png"))); // NOI18N
        ButtonSearch.setBorder(null);
        ButtonSearch.setBackground(color);
        jPanel4.add(ButtonSearch, java.awt.BorderLayout.WEST);

        SearchValue.setBackground(new java.awt.Color(242, 242, 242));
        SearchValue.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        SearchValue.setText("Tìm theo mã hóa đơn");
        SearchValue.setBackground(Color.white);
        SearchValue.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 6, 1, 1));
        jPanel4.add(SearchValue, java.awt.BorderLayout.CENTER);

        Header.add(jPanel4, java.awt.BorderLayout.SOUTH);

        add(Header, java.awt.BorderLayout.PAGE_START);

        Invoices.setLayout(new java.awt.GridLayout());
        Invoices.setBackground(color);

        add(Invoices, java.awt.BorderLayout.CENTER);
    }
    private javax.swing.JButton ButtonBack;
    private javax.swing.JButton ButtonSearch;
    private javax.swing.JPanel Header;
    private javax.swing.JPanel Invoices;
    private javax.swing.JPanel NameHeader;
    private javax.swing.JLabel NamePanel;
    private javax.swing.JTextField SearchValue;
    private javax.swing.JPanel jPanel4;
}

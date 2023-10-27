package com.clothingstore.gui.components.invoicesHistory;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.clothingstore.bus.UserBUS;
import com.clothingstore.models.UserModel;

import services.Authentication;

public class HistoryList extends JPanel {

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

        Header = new JPanel();
        NameHeader = new JPanel();
        NamePanel = new JLabel();
        ButtonBack = new JButton();
        jPanel4 = new JPanel();
        ButtonSearch = new JButton();
        SearchValue = new JTextField();
        Invoices = new JPanel();
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
        NamePanel.setText("Hoạt Động");
        NameHeader.setBackground(color);
        NameHeader.add(NamePanel, BorderLayout.CENTER);

        ButtonBack.setBackground(new Color(242, 242, 242));
        ButtonBack.setIcon(new ImageIcon(getClass().getResource("/config/icon/back.png"))); // NOI18N
        ButtonBack.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 1));
        NameHeader.add(ButtonBack, BorderLayout.LINE_START);
        ButtonBack.addActionListener(closeHistoryList);

        Header.add(NameHeader, BorderLayout.NORTH);

        jPanel4.setBorder(BorderFactory.createEmptyBorder(4, 4, 7, 1));
        jPanel4.setLayout(new BorderLayout());
        jPanel4.setBackground(color);

        ButtonSearch.setIcon(new ImageIcon(getClass().getResource("/config/icon/search.png"))); // NOI18N
        ButtonSearch.setBorder(null);
        ButtonSearch.setBackground(Color.WHITE);
        jPanel4.add(ButtonSearch, BorderLayout.WEST);

        SearchValue.setBackground(new Color(242, 242, 242));
        SearchValue.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        SearchValue.setText("Tìm theo mã hóa đơn");
        SearchValue.setBackground(Color.white);
        SearchValue.setBorder(BorderFactory.createEmptyBorder(1, 6, 1, 1));
        jPanel4.add(SearchValue, BorderLayout.CENTER);

        Header.add(jPanel4, BorderLayout.SOUTH);

        add(Header, BorderLayout.PAGE_START);

        Invoices.setLayout(new GridLayout(0, 1));
        Invoices.setBackground(color);
        for (int i = 0; i < 10; i++) {
            Invoice invoice = new Invoice();
            Invoices.add(invoice);
        }

        Scroll.setViewportView(Invoices);

        add(Scroll, BorderLayout.CENTER);
    }

    private ActionListener closeHistoryList = e -> {
        setVisible(false);
    };

    private JButton ButtonBack;
    private JButton ButtonSearch;
    private JPanel Header;
    private JPanel Invoices;
    private JPanel NameHeader;
    private JLabel NamePanel;
    private JTextField SearchValue;
    private JPanel jPanel4;
    private JScrollPane Scroll;
}

package com.clothingstore.gui.components.importInvoice;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import com.clothingstore.bus.ImportBUS;
import com.clothingstore.models.ImportModel;

public class ImportList extends JPanel {

    private static ImportList instance;

    public static ImportList getInstance() {
        if (instance == null) {
            instance = new ImportList();
        }
        return instance;
    }

    public ImportList() {
        initComponents();
        initData();
    }

    private void initComponents() {

        Header = new JPanel();
        NameHeader = new JPanel();
        NamePanel = new JLabel();
        ButtonBack = new JButton();
        Panel = new JPanel();
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

        NamePanel.setFont(new Font("Segoe UI", 1, 18));
        NamePanel.setHorizontalAlignment(SwingConstants.CENTER);
        NamePanel.setText("Import Invoice");
        NameHeader.setBackground(color);
        NameHeader.add(NamePanel, BorderLayout.CENTER);

        ButtonBack.setBackground(new Color(242, 242, 242));
        ButtonBack.setIcon(new ImageIcon(getClass().getResource("/config/icon/back.png")));
        ButtonBack.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 1));
        NameHeader.add(ButtonBack, BorderLayout.LINE_START);

        Header.add(NameHeader, BorderLayout.NORTH);

        Panel.setBorder(BorderFactory.createEmptyBorder(4, 4, 7, 1));
        Panel.setLayout(new BorderLayout());
        Panel.setBackground(color);

        ButtonSearch.setIcon(new ImageIcon(getClass().getResource("/config/icon/search.png")));
        ButtonSearch.setBorder(null);
        ButtonSearch.setBackground(Color.WHITE);
        Panel.add(ButtonSearch, BorderLayout.WEST);

        SearchValue.setBackground(new Color(242, 242, 242));
        SearchValue.setFont(new Font("Segoe UI", 0, 14));
        SearchValue.setText("Tìm theo mã hóa đơn");
        SearchValue.setBackground(Color.white);
        SearchValue.setBorder(BorderFactory.createEmptyBorder(1, 6, 1, 1));
        Panel.add(SearchValue, BorderLayout.CENTER);

        Header.add(Panel, BorderLayout.SOUTH);

        add(Header, BorderLayout.PAGE_START);

        Invoices.setLayout(new GridLayout(0, 1));
        Invoices.setBackground(color);
        for (int i = 0; i < 10; i++) {
            ImportInvoice importInvoice = new ImportInvoice();
            Invoices.add(importInvoice);
        }

        Scroll.setViewportView(Invoices);

        add(Scroll, BorderLayout.CENTER);
    }

    private void initData() {
        ArrayList<ImportModel> importList = new ArrayList<ImportModel>();
        importList.addAll(ImportBUS.getInstance().getAllModels());
        
    }

    private JButton ButtonBack;
    private JButton ButtonSearch;
    private JPanel Header;
    private JPanel Invoices;
    private JPanel NameHeader;
    private JLabel NamePanel;
    private JTextField SearchValue;
    private JPanel Panel;
    private JScrollPane Scroll;
}

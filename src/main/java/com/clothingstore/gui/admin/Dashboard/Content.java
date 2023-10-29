package com.clothingstore.gui.admin.Dashboard;

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
        Invoices = new JPanel();
        jPanel4 = new JPanel();

        setLayout(new BorderLayout());

        MainPanel.setLayout(new BorderLayout());

        Cards.setLayout(new BorderLayout());
        MainPanel.add(Cards, BorderLayout.NORTH);

        Invoices.setLayout(new BorderLayout());
        MainPanel.add(Invoices, BorderLayout.CENTER);

        add(MainPanel, BorderLayout.CENTER);

        jPanel4.setLayout(new BorderLayout());
        add(jPanel4, BorderLayout.EAST);
    }

    private JPanel Cards;
    private JPanel Invoices;
    private JPanel MainPanel;
    private JPanel jPanel4;
}

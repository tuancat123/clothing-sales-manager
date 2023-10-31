package com.clothingstore.gui.components.statistical;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

import com.clothingstore.gui.admin.dashboard.Card;
import com.clothingstore.gui.components.customerList.CustomerDetail;

public class Revenue extends JPanel {
    private static Revenue instance;

    public static Revenue getInstance() {
        if (instance == null) {
            instance = new Revenue();
        }
        return instance;
    }

    public Revenue(){
        initComponents();
    }

    private void initComponents(){
        ChartPanel = new JPanel();
        Content = new JPanel();
        CardPanel = new JPanel();
        setLayout(new BorderLayout());
        setBackground(Color.gray);

        ChartPanel.setPreferredSize(new Dimension(700,400));
        ChartPanel.setLayout(new BorderLayout());
        ChartPanel.setBorder(BorderFactory.createEmptyBorder(0,0,1,15));
        Chart chart = new Chart();
        ChartPanel.add(chart, BorderLayout.CENTER);

        CardPanel.setLayout(new GridLayout(1,4,25,25));
        CardPanel.setBorder(BorderFactory.createEmptyBorder(15,5,15,5));
        CardPanel.setPreferredSize(new Dimension(90,150));

        Card card1 = new Card("cart.png", "Total Order","34", new Color(0, 230, 230), 30);
        CardPanel.add(card1);

        Card card2 = new Card("coin.png", "Total Revenue","340.000"+"$", new Color(255, 77, 77), 34);
        CardPanel.add(card2);

        Card card3 = new Card("clothing.png", "Products Sold","33", new Color(255, 128, 0), 33);
        CardPanel.add(card3);

        Card card4 = new Card("coin.png", "Points Earned","3000", new Color(153, 51, 255), 77);
        CardPanel.add(card4);

        add(CardPanel, BorderLayout.NORTH);
        add(ChartPanel, BorderLayout.WEST);
        add(Content, BorderLayout.CENTER);
        
    }
    private JPanel CardPanel;
    private JPanel ChartPanel;
    private JPanel Content;
}

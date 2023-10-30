package com.clothingstore.gui.components.statistical;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

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
        setLayout(new BorderLayout());

        ChartPanel.setPreferredSize(new Dimension(400,400));
        ChartPanel.setLayout(new BorderLayout());
        Chart chart = new Chart();
        ChartPanel.add(chart, BorderLayout.CENTER);

        add(ChartPanel, BorderLayout.NORTH);
        add(Content, BorderLayout.CENTER);
        
    }
    private JPanel ChartPanel;
    private JPanel Content;
}

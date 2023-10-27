package com.clothingstore.gui.admin.Dashboard;

import java.awt.*;
import javax.swing.*;

public class Dashboard extends JFrame {

    private static Dashboard instance;

    public static Dashboard getInstance() {
        if (instance == null) {
            instance = new Dashboard();
        }
        return instance;
    }

    public Dashboard(){
        initComponents();
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    public void initComponents(){
        HeaderPanel = new JPanel();

        setLayout(new BorderLayout());
        setSize(new Dimension(1130, 628));
        setLayout(new BorderLayout());

        HeaderPanel.setLayout(new BorderLayout());
        HeaderPanel.setPreferredSize(new Dimension(70,75));
        HeaderPanel.add(Header.getInstance(), BorderLayout.CENTER);
        add(HeaderPanel, BorderLayout.NORTH);
    }

    private JPanel HeaderPanel;
}

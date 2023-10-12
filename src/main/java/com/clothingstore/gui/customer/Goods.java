package com.clothingstore.gui.customer;

import java.awt.*;
import javax.swing.*;
import org.netbeans.lib.awtextra.*;

public class Goods extends JPanel {

    public Goods() {
        initComponents();
    }

    private void initComponents() {

        IndexText = new JLabel();
        Name = new JLabel();
        Size = new JLabel();
        Prince = new JLabel();

        setBorder(BorderFactory.createEtchedBorder());
        setMinimumSize(new Dimension(220, 40));
        setPreferredSize(new Dimension(220, 50));
        setLayout(new AbsoluteLayout());

        IndexText.setFont(new Font("Segoe UI", 0, 14));
        IndexText.setText("01");
        add(IndexText, new AbsoluteConstraints(10, 10, -1, 30));

        Name.setFont(new Font("Segoe UI Black", 1, 14));
        Name.setText("BlackPolo");

        add(Name, new AbsoluteConstraints(30, 10, 100, 30));

        Size.setFont(new Font("Segoe UI", 1, 14));
        Size.setText("M");
        add(Size, new AbsoluteConstraints(140, 10, 20, 30));

        Prince.setFont(new Font("Segoe UI", 0, 14));
        Prince.setForeground(new Color(255, 0, 0));
        Prince.setText("340.333");
        add(Prince, new AbsoluteConstraints(160, 10, 60, 30));
    }

    private JLabel IndexText;
    private JLabel Name;
    private JLabel Prince;
    private JLabel Size;
}

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
        Quantity = new JLabel();
        ButtonDel = new JButton();

        setBorder(BorderFactory.createEtchedBorder());
        setMinimumSize(new Dimension(220, 40));
        setPreferredSize(new Dimension(220, 50));
        setLayout(new AbsoluteLayout());

        IndexText.setFont(new Font("Segoe UI", 0, 14));
        IndexText.setText("01");
        add(IndexText, new AbsoluteConstraints(8, 10, -1, 30));

        Name.setFont(new Font("Segoe UI", 3, 14));
        Name.setText("BlackPolo Bla");

        add(Name, new AbsoluteConstraints(28, 10, 100, 30));

        Size.setFont(new Font("Segoe UI", 1, 14));
        Size.setForeground(Color.BLUE);
        Size.setText("M");
        add(Size, new AbsoluteConstraints(150, 10, 20, 30));
        
        Quantity.setFont(new Font("Segoe UI", 2, 13));
        Quantity.setText("x3");
        add(Quantity, new AbsoluteConstraints(130, 10, 20, 30));

        Prince.setFont(new Font("Segoe UI", 0, 15));
        Prince.setForeground(new Color(255, 0, 0));
        Prince.setText("340.333");
        add(Prince, new AbsoluteConstraints(176, 10, 60, 28));

        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/config/icon/delete.png"));
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(17, 17, java.awt.Image.SCALE_REPLICATE);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        ButtonDel.setIcon(scaledIcon);
        add(ButtonDel, new AbsoluteConstraints(239, 13 , 25, 25));
    }

    private JLabel IndexText;
    private JLabel Name;
    private JLabel Prince;
    private JLabel Size;
    private JLabel Quantity;
    private JButton ButtonDel;
}

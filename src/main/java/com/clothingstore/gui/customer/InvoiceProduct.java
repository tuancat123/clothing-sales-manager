package com.clothingstore.gui.customer;

import java.awt.*;
import javax.swing.*;
import org.netbeans.lib.awtextra.*;

import com.clothingstore.gui.components.Control;

public class InvoiceProduct extends JPanel {

    public InvoiceProduct() {
        initComponents();
    }

    private void initComponents() {

        IndexText = new JLabel();
        Name = new JLabel();
        Size = new JLabel();
        Prince = new JLabel();
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

        add(Name, new AbsoluteConstraints(28, 10, 85, 30));

        Size.setFont(new Font("Segoe UI", 1, 14));
        Size.setForeground(Color.BLUE);
        Size.setText("M");
        add(Size, new AbsoluteConstraints(160, 10, 20, 30));
        
        Control control = new Control();
        add(control, new AbsoluteConstraints(118, 15, 42, 20));

        Prince.setFont(new Font("Segoe UI", 0, 15));
        Prince.setForeground(new Color(255, 0, 0));
        Prince.setText("340.333");
        add(Prince, new AbsoluteConstraints(178, 10, 90, 28));

        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/config/icon/delete.png"));
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(17, 17, java.awt.Image.SCALE_REPLICATE);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        ButtonDel.setIcon(scaledIcon);
        add(ButtonDel, new AbsoluteConstraints(250, 13 , 25, 25));
    }

    private JLabel IndexText;
    private JLabel Name;
    private JLabel Prince;
    private JLabel Size;
    private JButton ButtonDel;
}

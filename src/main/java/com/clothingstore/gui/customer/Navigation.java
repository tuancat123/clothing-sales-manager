
package com.clothingstore.gui.customer;

import com.clothingstore.gui.models.NavData;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class Navigation extends javax.swing.JPanel {

    ArrayList<NavData> data = NavData.getData();

    
    public Navigation() {
        initComponents();
    }

   
    private void initComponents() {
        setPreferredSize(new java.awt.Dimension(662, 50));
        setLayout(new java.awt.GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 10);

        for (NavData navData : data) {
            JButton Button = new JButton();
            Button.setPreferredSize(new Dimension(120, 40));
            JLabel Text = new JLabel(navData.getName());

            Button.add(Text);
            add(Button, gbc);
        }

    }

}

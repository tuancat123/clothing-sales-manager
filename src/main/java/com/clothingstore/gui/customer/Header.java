package com.clothingstore.gui.customer;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import org.netbeans.lib.awtextra.*;

public class Header extends  JPanel {

    public Header() {
        initComponents();
    }

    private void initComponents() {
        setBackground(new Color(0xAACDEF));

        Search = new  JPanel();
        Value = new  JTextField();
        Button = new  JLabel();

        setPreferredSize(new Dimension(340, 40));
        setLayout(new BorderLayout());

        Search.setPreferredSize(new Dimension(355, 20));      
        Search.setBackground(new Color(0xAACDEF));
        Search.setLayout(new AbsoluteLayout());

        Value.setHorizontalAlignment( JTextField.RIGHT);
        Value.setBorder(new  SoftBevelBorder( BevelBorder.RAISED, new Color(0, 0, 0), new Color(0, 0, 0), null, null));

        Button.setIcon(new ImageIcon(getClass().getResource("/config/icon/search.png")));

        Search.add(Value, new AbsoluteConstraints(0, 10, 300, 22));
        Search.add(Button, new AbsoluteConstraints(310, 10, 40, 22));

        add(Search, BorderLayout.EAST);

    }
    private  JLabel Button;
    private  JPanel Search;
    private  JTextField Value;
}

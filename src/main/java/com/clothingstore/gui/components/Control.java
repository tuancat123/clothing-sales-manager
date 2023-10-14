package com.clothingstore.gui.components;

import java.awt.*;
import javax.swing.*;

public class Control extends JPanel {

   
    public Control() {
        initComponents();
    }

    private void initComponents() {

        IncrButton = new JButton();
        ReduceButton = new JButton();
        Value = new JLabel();

        setLayout(new BorderLayout());

        IncrButton.setText("+");
        add(IncrButton, BorderLayout.EAST);

        ReduceButton.setText("-");
        
        add(ReduceButton, BorderLayout.WEST);

        Value.setHorizontalAlignment(SwingConstants.CENTER);
        Value.setText("6");
        Value.setFont(new Font("Segoe UI", 0, 14)); 
        
        add(Value, BorderLayout.CENTER);
    }

    private JButton IncrButton;
    private JButton ReduceButton;
    private JLabel Value;
}

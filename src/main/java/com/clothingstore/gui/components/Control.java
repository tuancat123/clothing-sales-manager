package com.clothingstore.gui.components;

import java.awt.*;

import javax.print.DocFlavor.READER;
import javax.swing.*;

public class Control extends JPanel {

   
    public Control() {
        initComponents();
    }

    private void initComponents() {
        
        IncrButton = new JLabel();
        ReduceButton = new JLabel();
        Value = new JLabel();

        setLayout(new BorderLayout());

        IncrButton.setText(" +");       
        IncrButton.setPreferredSize(new Dimension(15, 10));
        setBorder(BorderFactory.createEtchedBorder());
        IncrButton.setBackground(new Color(255, 51, 51));
        add(IncrButton, BorderLayout.EAST);

        ReduceButton.setText(" -");
        ReduceButton.setPreferredSize(new Dimension(15, 10));
        ReduceButton.setBackground(new Color(255, 51, 51));
        add(ReduceButton, BorderLayout.WEST);

        Value.setHorizontalAlignment(SwingConstants.CENTER);
        Value.setText("6");
        Value.setFont(new Font("Segoe UI", 0, 14)); 
        
        add(Value, BorderLayout.CENTER);
    }

    private JLabel IncrButton;
    private JLabel ReduceButton;
    private JLabel Value;
}

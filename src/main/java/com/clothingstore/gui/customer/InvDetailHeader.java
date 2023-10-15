package com.clothingstore.gui.customer;

import java.awt.*;
import javax.swing.*;

public class InvDetailHeader extends JPanel {

   
    public InvDetailHeader() {
        initComponents();
    }

    private void initComponents() {

        Panel1 = new JPanel();
        QuantityText = new JLabel();
        Panel2 = new JPanel();
        SizeText = new JLabel();
        Panel3 = new JPanel();
        PriceText = new JLabel();
        Panel4 = new JPanel();
        AmountText = new JLabel();

        setLayout(new GridLayout(1, 0, 5, 0));

        Panel1.setPreferredSize(new Dimension(150, 150));
        Panel1.setRequestFocusEnabled(false);
        Panel1.setLayout(new BorderLayout());

        QuantityText.setFont(new Font("Segoe UI", 3, 14)); 
        QuantityText.setHorizontalAlignment(SwingConstants.CENTER);
        QuantityText.setText("Quantity");
        Panel1.add(QuantityText, BorderLayout.CENTER);

        add(Panel1);

        Panel2.setPreferredSize(new Dimension(150, 150));
        Panel2.setRequestFocusEnabled(false);
        Panel2.setLayout(new BorderLayout());

        SizeText.setFont(new Font("Segoe UI", 3, 14)); 
        SizeText.setHorizontalAlignment(SwingConstants.CENTER);
        SizeText.setText("Size");
        Panel2.add(SizeText, BorderLayout.CENTER);

        add(Panel2);

        Panel3.setPreferredSize(new Dimension(150, 150));
        Panel3.setRequestFocusEnabled(false);
        Panel3.setLayout(new BorderLayout());

        PriceText.setFont(new Font("Segoe UI", 3, 14)); 
        PriceText.setHorizontalAlignment(SwingConstants.CENTER);
        PriceText.setText("Price");
        Panel3.add(PriceText, BorderLayout.CENTER);

        add(Panel3);

        Panel4.setPreferredSize(new Dimension(150, 150));
        Panel4.setRequestFocusEnabled(false);
        Panel4.setLayout(new BorderLayout());

        AmountText.setFont(new Font("Segoe UI", 3, 14)); 
        AmountText.setHorizontalAlignment(SwingConstants.CENTER);
        AmountText.setText("Amount");
        Panel4.add(AmountText, BorderLayout.CENTER);

        add(Panel4);
    }

    private JLabel AmountText;
    private JPanel Panel1;
    private JPanel Panel2;
    private JPanel Panel3;
    private JPanel Panel4;
    private JLabel PriceText;
    private JLabel QuantityText;
    private JLabel SizeText;
}

package com.clothingstore.gui.customer;

import javax.swing.*;

import com.clothingstore.gui.components.Menu;
import com.clothingstore.gui.components.Products;
import java.awt.*;

public class HomePage extends JFrame{

    private static HomePage instance;



    public static HomePage getInstance() {
        if (instance == null) {
          instance = new HomePage();
        }
        return instance;
      }

    public HomePage(){
        initComponent();
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    public void initComponent(){
        setSize(new Dimension(1130, 628));
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        add(Products.getInstance(),BorderLayout.CENTER);

        Invoice invoice = new Invoice();
        add(invoice, BorderLayout.EAST);

        Navigation navigation = new Navigation();
        add(navigation, BorderLayout.SOUTH);

        add(Menu.getInstance(), BorderLayout.WEST);

    }
}

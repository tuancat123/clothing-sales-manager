package com.clothingstore.gui.components;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

public class Product extends JPanel {

    private static Product instance;

    private JPanel Header; 
    

    public static Product getInstance() {
        if (instance == null) {
          instance = new Product();
        }
        return instance;
      }

    public Product(){
        initComponent();
    }

    public void initComponent(){


    }
}

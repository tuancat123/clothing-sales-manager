package com.clothingstore.gui.customer;

import com.clothingstore.gui.customer.HomePage;

import javax.swing.*;

public class Main extends JFrame{
    private static Main instance;

    public static Main getInstance() {
        if (instance == null) {
          instance = new Main();
        }
        return instance;
      }

    public Main(){
        initComponent();
    }

    public void initComponent(){
    }
}

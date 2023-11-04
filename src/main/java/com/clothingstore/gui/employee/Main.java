package com.clothingstore.gui.employee;

import javax.swing.*;

import com.clothingstore.gui.components.HomePage;

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

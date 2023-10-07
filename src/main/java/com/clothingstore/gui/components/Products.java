package com.clothingstore.gui.components;

import java.awt.*;
import javax.swing.*;

public class Products extends JPanel {

    private static Products instance;

    private JPanel Header; 
    private JPanel Content;
    

    public static Products getInstance() {
        if (instance == null) {
          instance = new Products();
        }
        return instance;
      }

    public Products(){
        initComponent();
    }

    public void initComponent(){
        Header = new JPanel();
        Content = new JPanel();

        setBackground(new Color(0xAACDEF));
        setLayout(new BorderLayout());

        Header.setBackground(Color.red);
        add(Header, BorderLayout.NORTH);

        Content.setLayout(new GridLayout(10,10));
        Content.setBackground(new Color(0xAACDEF));
        Content.add(Product.getInstance());
        add(Content, BorderLayout.CENTER);
    }
}

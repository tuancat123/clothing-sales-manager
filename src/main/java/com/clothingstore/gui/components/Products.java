
package com.clothingstore.gui.components;

import java.awt.*;

import javax.swing.*;

import com.clothingstore.gui.employee.Header;
import com.clothingstore.gui.employee.HomePage;

public class Products extends JPanel {

    private static Products instance;
    Boolean Visible = false;

    public static Products getInstance() {
        if (instance == null) {
            instance = new Products();
        }
        return instance;
    }

    public Products() {
        initComponents();
        MenuOn(Visible);
    }

    public void MenuOn(Boolean Visible){
        if(!Visible)
            Products.setLayout(new GridLayout(3, 4));
        else
            Products.setLayout(new GridLayout(4, 3));
    }

    private void initComponents() {

        Scroll = new JScrollPane();
        Products = new JPanel();

        setLayout(new BorderLayout());
        Products.setBackground(new Color(170, 205, 239));

        for( int i = 0; i<10; i++){
            Product product = new Product();
            product.setBackground(new Color(170, 205, 239));
            Products.add(product);
        }
        Scroll.setViewportView(Products);

        Header header = new Header();
        add(header, BorderLayout.NORTH);

        add(Scroll, BorderLayout.CENTER);
    }

    private JPanel Products;
    private JScrollPane Scroll;
}

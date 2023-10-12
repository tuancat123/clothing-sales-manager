
package com.clothingstore.gui.components;

import java.awt.*;

import javax.swing.*;

import com.clothingstore.gui.customer.Header;
import com.clothingstore.gui.customer.HomePage;

public class Products extends JPanel {

    private static Products instance;

    public static Products getInstance() {
        if (instance == null) {
            instance = new Products();
        }
        return instance;
    }

    public Products() {
        initComponents();
    }

    private void initComponents() {

        Scroll = new JScrollPane();
        Products = new JPanel();

        setLayout(new BorderLayout());
        Products.setBackground(new Color(170, 205, 239));

        Products.setLayout(new GridLayout(5, 5));
        for (int i = 0; i < 22; i++) {
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

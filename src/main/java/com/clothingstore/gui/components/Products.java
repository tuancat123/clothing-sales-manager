
package com.clothingstore.gui.components;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import com.clothingstore.bus.ProductBUS;
import com.clothingstore.gui.employee.Header;
import com.clothingstore.models.ProductModel;

public class Products extends JPanel {

    List<ProductModel> productList = ProductBUS.getInstance().getAllModels();
    
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
            Products.setLayout(new GridLayout(0, 4));
        else
            Products.setLayout(new GridLayout(0, 3));
    }

    private void initComponents() {

        Scroll = new JScrollPane();
        Products = new JPanel();

        setLayout(new BorderLayout());
        Products.setBackground(new Color(170, 205, 239));
        
        for(ProductModel products : productList){
            Product product = new Product(products);
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

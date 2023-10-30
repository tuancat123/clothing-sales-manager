package com.clothingstore.gui.components.customerList;

import java.awt.*;
import javax.swing.*;

public class Customers extends JPanel {
    private static Customers instance;

    public static Customers getInstance() {
        if (instance == null) {
            instance = new Customers();
        }
        return instance;
    }

    public Customers() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        add(CustomerList.getInstance(), BorderLayout.WEST);
        add(CustomerDetail.getInstance(), BorderLayout.CENTER);
    }
    
}

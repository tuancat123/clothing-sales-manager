package com.clothingstore.gui.components.importInvoice.addImport;

import javax.swing.*;
import java.awt.*;

public class NoProduct extends JPanel {

    public NoProduct() {
        setLayout(new BorderLayout());
        JLabel noProductLabel = new JLabel("No products available.");
        noProductLabel.setHorizontalAlignment(JLabel.CENTER);
        Font labelFont = new Font("Arial", Font.BOLD, 16);
        noProductLabel.setFont(labelFont);
        add(noProductLabel, BorderLayout.CENTER);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(100, 20));
    }

}

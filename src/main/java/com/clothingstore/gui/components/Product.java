package com.clothingstore.gui.components;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Product extends JPanel {

    public Product() {
        initComponents();
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                ProductDetail productDetail = new ProductDetail();
                productDetail.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {}

            @Override
            public void mouseExited(MouseEvent arg0) {}

            @Override
            public void mousePressed(MouseEvent arg0) {}

            @Override
            public void mouseReleased(MouseEvent arg0) {}
            
        });
    }

    private void initComponents() {

        Header = new JPanel();
        Image = new JLabel();
        Footer = new JPanel();
        Name = new JLabel();
        Price = new JLabel();

        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setPreferredSize(new Dimension(199, 280));
        setLayout(new BorderLayout());

        Header.setBackground(new Color(255, 255, 255));
        Header.setLayout(new GridBagLayout());

        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/config/image/polo3.png"));
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(180, 180, java.awt.Image.SCALE_REPLICATE);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        Image.setIcon(scaledIcon); 
        Image.setPreferredSize(new Dimension(180, 178));
        Header.add(Image, new GridBagConstraints());

        add(Header, BorderLayout.CENTER);

        Footer.setBackground(new Color(255, 255, 255));
        Footer.setPreferredSize(new Dimension(193, 65));
        Footer.setLayout(new GridLayout(2, 1));

        Name.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
        Name.setHorizontalAlignment(SwingConstants.CENTER);
        Name.setText("Black Polo");
        Footer.add(Name);

        Price.setFont(new Font("Segoe UI", 2, 16)); // NOI18N
        Price.setForeground(new Color(240, 18, 18));
        Price.setHorizontalAlignment(SwingConstants.CENTER);
        Price.setText("350.000");
        Footer.add(Price);

        add(Footer, BorderLayout.SOUTH);
    }
    private JPanel Footer;
    private JPanel Header;
    private JLabel Image;
    private JLabel Name;
    private JLabel Price;
}

package com.clothingstore.gui.customer;

import javax.swing.*;

import com.clothingstore.gui.components.Menu;
import com.clothingstore.gui.components.Products;
import java.awt.*;

public class HomePage extends JFrame{

    private static HomePage instance;



    public static HomePage getInstance() {
        if (instance == null) {
          instance = new HomePage();
        }
        return instance;
      }

    public HomePage(){
        initComponent();
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    public void initComponent(){
        setSize(new Dimension(1130, 628));
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        add(Products.getInstance(),BorderLayout.CENTER);

        add(Invoice.getInstance(), BorderLayout.EAST);

        Navigation navigation = new Navigation();
        add(navigation, BorderLayout.SOUTH);

        add(Menu.getInstance(), BorderLayout.WEST);
    }
    public void Remove(){
        Container contentPane = HomePage.getInstance().getContentPane();
        Component centerComponent = ((BorderLayout) contentPane.getLayout()).getLayoutComponent(BorderLayout.CENTER);
        Component eastComponent = ((BorderLayout) contentPane.getLayout()).getLayoutComponent(BorderLayout.EAST);
        if (centerComponent != null) {
            contentPane.remove(centerComponent);
            contentPane.revalidate();
            contentPane.repaint();
        }
        if (eastComponent != null) {
            contentPane.remove(eastComponent);
            contentPane.revalidate();
            contentPane.repaint();
        }
    }
    
    public void Add(JPanel panel){
      add(panel, BorderLayout.CENTER);
      revalidate();
      repaint();
    }
    public void Add(JPanel panel, JPanel panel2){
      add(panel, BorderLayout.CENTER);
      add(panel2, BorderLayout.EAST);
      revalidate();
      repaint();
    }
}

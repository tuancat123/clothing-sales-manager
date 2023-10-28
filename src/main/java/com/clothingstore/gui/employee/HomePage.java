package com.clothingstore.gui.employee;

import javax.swing.*;

import com.clothingstore.gui.components.Menu;
import com.clothingstore.gui.components.Products;
import com.clothingstore.gui.models.MenuData;
import com.clothingstore.models.UserModel;

import java.awt.*;
import java.util.ArrayList;

public class HomePage extends JFrame {

  private static HomePage instance;

  public static HomePage getInstance(UserModel user) {
    if (instance == null) {
      instance = new HomePage(user);
    }
    return instance;
  }

  public HomePage(UserModel user) {
    initComponent(user);
    setLocationRelativeTo(null);
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  }

  public void initComponent(UserModel user) {
    setSize(new Dimension(1130, 628));
    setLayout(new BorderLayout());
    setBackground(Color.BLACK);

    add(Products.getInstance(), BorderLayout.CENTER);

    add(Invoice.getInstance(), BorderLayout.EAST);

    Navigation navigation = new Navigation();
    add(navigation, BorderLayout.SOUTH);

    ArrayList<MenuData> data = MenuData.getDataMenu();

    add(Menu.getInstance(data), BorderLayout.WEST);
  }

  public void Remove(UserModel user) {
    Container contentPane = HomePage.getInstance(user).getContentPane();
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

  public void Add(JPanel panel) {
    add(panel, BorderLayout.CENTER);
    revalidate();
    repaint();
  }

  public void Add(JPanel panel, JPanel panel2) {
    add(panel, BorderLayout.CENTER);
    add(panel2, BorderLayout.EAST);
    revalidate();
    repaint();
  }
}

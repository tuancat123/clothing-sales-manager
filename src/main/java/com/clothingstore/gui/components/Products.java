
package com.clothingstore.gui.components;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import com.clothingstore.bus.ProductBUS;
import com.clothingstore.models.ProductModel;
import com.clothingstore.models.UserModel;

import services.Authentication;

public class Products extends JPanel {

  List<ProductModel> productList = ProductBUS.getInstance().getAllModels();
  static UserModel currentUser = Authentication.getCurrentUser();

  Boolean Visible = false;
  private static Products instance;

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

  public void MenuOn(Boolean Visible) {
    if (!Visible)
      Products.setLayout(new GridLayout(0, 4));
    else
      Products.setLayout(new GridLayout(0, 3));
  }

  private void initComponents() {

    Scroll = new JScrollPane();
    Products = new JPanel();

    setLayout(new BorderLayout());
    Products.setBackground(new Color(170, 205, 239));

    for (ProductModel products : productList) {
      if (products.getStatus() == 1) {
        Product product = new Product(products);
        product.setBackground(new Color(170, 205, 239));
        Products.add(product);
      }
    }
    Scroll.setViewportView(Products);
    add(ProductsHeader.getInstance(), BorderLayout.NORTH);
    add(Scroll, BorderLayout.CENTER);
  }

  private JPanel Products;
  private JScrollPane Scroll;
}

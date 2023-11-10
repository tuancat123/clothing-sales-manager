package com.clothingstore.gui.components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.netbeans.lib.awtextra.*;

import com.clothingstore.gui.models.NavData;
import com.clothingstore.models.UserModel;

import services.Authentication;

public class ProductsHeader extends JPanel {

  private static ProductsHeader instance;

  static UserModel currentUser = Authentication.getCurrentUser();

  public static ProductsHeader getInstance() {
    if (instance == null) {
      instance = new ProductsHeader();
    }
    return instance;
  }

  public ProductsHeader() {
    initComponents();
  }

  private void initComponents() {

    Panel = new JPanel();
    Value = new JTextField();
    ButtonSearch = new JButton();
    ButtonAdd = new JButton();
    ButtonMenu = new JButton();
    Color color = new Color(153, 194, 255);

    setBorder(BorderFactory.createEmptyBorder(1, 10, 1, 10));
    setPreferredSize(new Dimension(511, 50));
    setLayout(new BorderLayout());
    setBackground(color);

    Panel.setLayout(new AbsoluteLayout());
    Panel.setBackground(color);

    Value.setFont(new Font("Segoe UI", 0, 14));
    Value.setText("Tìm kiếm sản phẩm");
    Value.addFocusListener(new FocusListener() {
      @Override
      public void focusGained(FocusEvent e) {
        if (Value.getText().equals("Tìm kiếm sản phẩm")) {
          Value.setText("");
        }
      }

      @Override
      public void focusLost(FocusEvent e) {
        if (Value.getText().isEmpty()) {
          Value.setText("Tìm kiếm sản phẩm");
        }
      }
    });
    Panel.add(Value, new AbsoluteConstraints(210, 10, 170, 30));

    ButtonSearch.setIcon(new ImageIcon(getClass().getResource("/config/icon/search.png")));
    Panel.add(ButtonSearch, new AbsoluteConstraints(380, 10, 30, 30));

    ButtonAdd.setText("Add");
    ButtonAdd.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        AddProduct addProduct = new AddProduct();
        addProduct.setVisible(true);
      }
      
    });

    ButtonMenu.setIcon(new ImageIcon(getClass().getResource("/config/icon/menu.png")));
    ButtonMenu.setBackground(color);
    ButtonMenu.setBorder(null);
    ButtonMenu.addActionListener(NavData.MenuAction());

    if (currentUser.getRoleId() != 3) {
      add(ButtonMenu, BorderLayout.WEST);
      Panel.add(ButtonAdd, new AbsoluteConstraints(440, 10, 80, 30));
    }

    add(Panel, BorderLayout.LINE_END);
  }

  private JButton ButtonAdd;
  private JButton ButtonSearch;
  private JButton ButtonMenu;
  private JPanel Panel;
  private JTextField Value;
}

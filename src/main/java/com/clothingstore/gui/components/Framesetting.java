package com.clothingstore.gui.components;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import org.netbeans.lib.awtextra.*;

import com.clothingstore.bus.CategoryBUS;
import com.clothingstore.bus.ImportItemsBUS;
import com.clothingstore.bus.ProductBUS;
import com.clothingstore.bus.SizeBUS;
import com.clothingstore.bus.SizeItemBUS;
import com.clothingstore.gui.employee.Invoice;

import com.clothingstore.models.ImportItemsModel;
import com.clothingstore.models.ProductModel;
import com.clothingstore.models.SizeItemModel;
import com.clothingstore.models.SizeModel;
import com.clothingstore.models.UserModel;
import services.Authentication;

public class Framesetting extends JFrame {
private JPanel jPanel;

  public Framesetting() {
    setAlwaysOnTop(true);
    setSize(new Dimension(685, 390));
    setPreferredSize(new Dimension(685, 325));
    setResizable(false);
    getContentPane().setLayout(new AbsoluteLayout());
    setLocationRelativeTo(null);

    initComponents();
  }

  private void initComponents() {
   
  }
  public static void main(String[] args) {
    Framesetting  framesetting = new Framesetting();
    framesetting.setVisible(true);
  }

}
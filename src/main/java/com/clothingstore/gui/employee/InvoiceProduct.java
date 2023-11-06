package com.clothingstore.gui.employee;

import java.awt.*;
import javax.swing.*;
import org.netbeans.lib.awtextra.*;

import com.clothingstore.models.OrderItemModel;

public class InvoiceProduct extends JPanel {

  public InvoiceProduct(OrderItemModel orderItemModel) {
    initComponents(orderItemModel);
  }

  private void initComponents(OrderItemModel orderItemModel) {
    IndexText = new JLabel();
    Name = new JLabel();
    Size = new JLabel();
    Prince = new JLabel();
    ButtonDel = new JButton();

    setBorder(BorderFactory.createEtchedBorder());
    setMinimumSize(new Dimension(220, 40));
    setPreferredSize(new Dimension(220, 65));
    setLayout(new AbsoluteLayout());
    setBackground(new Color(179, 209, 255));

    IndexText.setFont(new Font("Segoe UI", 0, 14));
    IndexText.setText("01");
    add(IndexText, new AbsoluteConstraints(8, 20, -1, 30));

    Name.setFont(new Font("Segoe UI", 3, 14));
    Name.setText("ch co");

    add(Name, new AbsoluteConstraints(32, 6, 150, 30));

    Size.setFont(new Font("Segoe UI", 1, 14));
    Size.setForeground(Color.BLUE);
    int size = orderItemModel.getSizeId();
    System.out.println("size " + size);
    if (size == 1) {
      Size.setText("( S )");
    } else if (size == 2) {
      Size.setText("( M )");
    } else if (size == 3) {
      Size.setText("( L )");
    } else if (size == 4) {
      Size.setText("( XL )");
    } else if (size == 5) {
      Size.setText("( XXL )");
    }
    add(Size, new AbsoluteConstraints(45, 28, 45, 30));

    JSpinner spinner = new JSpinner(new SpinnerNumberModel(orderItemModel.getQuantity(), 1, null, 1));
    spinner.setBackground(new Color(255, 204, 204));
    add(spinner, new AbsoluteConstraints(90, 34, 55, 20));

    Prince.setFont(new Font("Segoe UI", 0, 15));
    Prince.setForeground(new Color(255, 0, 0));
    Prince.setText("340.333");
    add(Prince, new AbsoluteConstraints(160, 28, 90, 28));

    ImageIcon originalIcon = new ImageIcon(getClass().getResource("/config/icon/delete.png"));
    Image originalImage = originalIcon.getImage();
    Image scaledImage = originalImage.getScaledInstance(17, 17, java.awt.Image.SCALE_REPLICATE);
    ImageIcon scaledIcon = new ImageIcon(scaledImage);

    ButtonDel.setIcon(scaledIcon);
    add(ButtonDel, new AbsoluteConstraints(240, 22, 25, 25));
  }

  private JLabel IndexText;
  private JLabel Name;
  private JLabel Prince;
  private JLabel Size;
  private JButton ButtonDel;
}

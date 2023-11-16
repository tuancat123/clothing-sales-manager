package com.clothingstore.gui.employee;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import org.netbeans.lib.awtextra.*;

import com.clothingstore.bus.ProductBUS;
import com.clothingstore.models.ProductModel;

public class InvoiceProduct extends JPanel {

  public InvoiceProduct(ProductModel productModel, int size, int quantity) {
    initComponents(productModel, size, quantity);
  }

  private void initComponents(ProductModel productModel, int size, int quantity) {
    Name = new JLabel();
    Size = new JLabel();
    Price = new JLabel();
    ButtonDel = new JButton();

    setBorder(BorderFactory.createEtchedBorder());
    setMinimumSize(new Dimension(220, 40));
    setPreferredSize(new Dimension(220, 65));
    setLayout(new AbsoluteLayout());
    setBackground(new Color(179, 209, 255));

    Name.setFont(new Font("Segoe UI", 3, 14));
    Name.setText("" + productModel.getName());

    add(Name, new AbsoluteConstraints(5, 6, 230, 30));

    Size.setFont(new Font("Segoe UI", 1, 14));
    Size.setForeground(Color.BLUE);
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

    JSpinner spinner = new JSpinner(new SpinnerNumberModel(quantity, 1, null, 1));
    spinner.setBackground(new Color(255, 204, 204));
    add(spinner, new AbsoluteConstraints(90, 34, 55, 20));

    Price.setFont(new Font("Segoe UI", 0, 15));
    Price.setForeground(new Color(255, 0, 0));

    double totalPrice = ProductBUS.getInstance().getModelById(productModel.getId()).getPrice()
        * (int) spinner.getValue();
    Price.setText("" + totalPrice);
    add(Price, new AbsoluteConstraints(160, 28, 90, 28));

    ImageIcon originalIcon = new ImageIcon(getClass().getResource("/resources/icons/delete.png"));
    Image originalImage = originalIcon.getImage();
    Image scaledImage = originalImage.getScaledInstance(17, 17, java.awt.Image.SCALE_REPLICATE);
    ImageIcon scaledIcon = new ImageIcon(scaledImage);

    ButtonDel.setIcon(scaledIcon);
    add(ButtonDel, new AbsoluteConstraints(240, 22, 25, 25));
    ButtonDel.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // JOptionPane.showConfirmDialog("Bạn có muốn xóa sản phẩm này không?", e);
      }

    });
  }

  private JLabel Name;
  private JLabel Price;
  private JLabel Size;
  private JButton ButtonDel;
}

package com.clothingstore.gui.employee;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import org.netbeans.lib.awtextra.*;

import com.clothingstore.gui.components.invoiceDetail.InvoiceDetail;
import com.clothingstore.models.OrderItemModel;
import com.clothingstore.models.OrderModel;

public class Invoice extends JPanel {
  private static Invoice instance;

  public static Invoice getInstance() {
    if (instance == null) {
      instance = new Invoice(null);
    }
    return instance;
  }

  public Invoice(List<OrderItemModel> orderItemModel) {
    initComponents(orderItemModel);
    // updateCartUI(orderItemModel);
  }

  private void initComponents(List<OrderItemModel> orderItemModel) {
    Header = new JPanel();
    Index = new JLabel();
    None = new JLabel();
    NameShop = new JTextField();
    Content = new JPanel();
    Scroll = new JScrollPane();
    Invoices = new JPanel();
    Footer = new JPanel();
    TextSum = new JLabel();
    Value = new JLabel();
    ButtonCancel = new JButton();
    ButtonPay = new JButton();
    ButtonPrint = new JLabel();

    Color color = new Color(128, 179, 255);

    setPreferredSize(new Dimension(300, 370));
    setLayout(new BorderLayout());

    Header.setBackground(new Color(0, 51, 128));
    Header.setLayout(new BorderLayout());

    Index.setFont(new Font("Segoe UI", 0, 14));
    Index.setHorizontalAlignment(SwingConstants.CENTER);
    Index.setText("45");
    Index.setPreferredSize(new Dimension(40, 50));
    Index.setForeground(Color.WHITE);

    None.setPreferredSize(new Dimension(40, 50));

    NameShop.setEditable(false);
    NameShop.setFont(new Font("Segoe UI", 1, 18));
    NameShop.setHorizontalAlignment(JTextField.CENTER);
    NameShop.setBackground(new Color(128, 179, 255));
    NameShop.setText("The Shop VIP");

    Content.setLayout(new BorderLayout());

    Invoices.setBackground(new Color(255, 255, 255));
    Invoices.setLayout(new GridLayout(10, 1));
    if (orderItemModel != null) {
      for (OrderItemModel orderItemModels : orderItemModel) {
        InvoiceProduct invoiceProduct = new InvoiceProduct(orderItemModels);
        System.out.println("tao thanh cong invoice pro");
        invoiceProduct.setVisible(true);
        Invoices.add(invoiceProduct);
        System.out.println("add thanh cong invoice pro");
        Invoices.setVisible(true);
      }
    } else {
      System.out.println("dang null");
    }

    // InvoiceProduct invoiceProduct = new InvoiceProduct();
    // Invoices.add(invoiceProduct);
    // InvoiceProduct invoiceProduct2 = new InvoiceProduct();
    // Invoices.add(invoiceProduct2);

    Scroll.setViewportView(Invoices);

    Footer.setPreferredSize(new Dimension(301, 120));
    Footer.setLayout(new AbsoluteLayout());
    Footer.setBackground(color);

    TextSum.setFont(new Font("Segoe UI", 1, 13));
    TextSum.setHorizontalAlignment(SwingConstants.CENTER);
    TextSum.setText("Tổng Thanh Toán");

    Value.setFont(new Font("Segoe UI", 0, 18));
    Value.setForeground(new Color(255, 51, 51));
    Value.setHorizontalAlignment(SwingConstants.CENTER);
    double totalPrice = 0;
    if (orderItemModel != null && !orderItemModel.isEmpty()) {
      for (OrderItemModel order : orderItemModel) {
        double amount = order.getPrice() * order.getQuantity();
        totalPrice += amount;
      }
    }
    Value.setText("" + totalPrice);

    ButtonCancel.setText("Hủy");
    ButtonCancel.setBackground(Color.BLUE);
    ButtonCancel.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa toàn bộ sản phẩm khỏi giỏ hàng?",
            "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
          System.out.println(" " + orderItemModel.size());
          orderItemModel.clear();
          revalidate();
          repaint();
        } else {
          return;
        }
      }
    });

    ButtonCancel.setPreferredSize(new Dimension(72, 20));

    ButtonPay.setFont(new Font("Segoe UI", 0, 10));
    ButtonPay.setText("Thanh Toán");
    ButtonPay.setBackground(Color.BLUE);
    ButtonPay.setPreferredSize(new Dimension(72, 20));
    ButtonPay.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        InvoiceDetail invoiceDetail = new InvoiceDetail();
        invoiceDetail.setVisible(true);
      }
    });

    ButtonPrint.setIcon(new ImageIcon(getClass().getResource("/config/icon/printer.png")));

    Header.add(Index, BorderLayout.LINE_START);
    Header.add(None, BorderLayout.LINE_END);
    Header.add(NameShop, BorderLayout.CENTER);
    add(Header, BorderLayout.PAGE_START);

    Content.add(Scroll, BorderLayout.CENTER);
    add(Content, BorderLayout.CENTER);

    Footer.add(TextSum, new AbsoluteConstraints(0, 20, 120, 30));
    Footer.add(Value, new AbsoluteConstraints(0, 40, 120, 30));
    Footer.add(ButtonCancel, new AbsoluteConstraints(40, 80, 90, 30));
    Footer.add(ButtonPay, new AbsoluteConstraints(170, 80, 90, 30));
    Footer.add(ButtonPrint, new AbsoluteConstraints(250, 30, -1, -1));
    add(Footer, BorderLayout.SOUTH);

  }

  // public void updateCartUI(List<OrderItemModel> orderItemModels) {
  // System.out.println("update");
  // if (orderItemModels != null) {
  // for (OrderItemModel orderItemModel1 : orderItemModels) {
  // InvoiceProduct invoiceProduct = new InvoiceProduct(orderItemModel1);
  // System.out.println("tao thanh cong invoid pro");
  // Invoices.add(invoiceProduct);
  // System.out.println("add thanh cong invoid pro");
  // Invoices.setVisible(true);
  // }
  // } else {
  // System.out.println("ddang null");
  // }

  // }

  private JButton ButtonCancel;
  private JButton ButtonPay;
  private JLabel ButtonPrint;
  private JPanel Content;
  private JPanel Footer;
  private JPanel Header;
  private JLabel Index;
  private JPanel Invoices;
  private JTextField NameShop;
  private JLabel None;
  private JScrollPane Scroll;
  private JLabel TextSum;
  private JLabel Value;
}

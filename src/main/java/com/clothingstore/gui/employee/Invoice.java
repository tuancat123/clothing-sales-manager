package com.clothingstore.gui.employee;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import org.netbeans.lib.awtextra.*;
import com.clothingstore.gui.employee.invoiceDetail.InvoiceDetail;
import com.clothingstore.models.OrderItemModel;
import com.clothingstore.models.ProductModel;

public class Invoice extends JPanel {
  private static Invoice instance;
  List<OrderItemModel> orderItemList = new ArrayList<>();

  public static Invoice getInstance() {
    if (instance == null) {
      instance = new Invoice();
    }
    return instance;
  }

  public Invoice() {
    initComponents();
    // updateCartUI(orderItemModel);
  }

  private void initComponents() {
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

    ButtonCancel.setText("Hủy");
    ButtonCancel.setBackground(Color.BLUE);
    ButtonCancel.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa toàn bộ sản phẩm khỏi giỏ hàng?",
            "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
          orderItemList.clear();
          totalPrice = 0;
          Value.setText("" + totalPrice);
          Invoices.removeAll();
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
    ButtonPay.addActionListener(PayAction);

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
  private double totalPrice;

  public void addToCart(ProductModel productModel, int size, int quantity) {

    OrderItemModel orderItemModel = new OrderItemModel(0, 0, productModel.getId(), size, quantity,
        productModel.getPrice() * quantity);
    orderItemList.add(orderItemModel);
    totalPrice += productModel.getPrice() * quantity;
    Value.setText(totalPrice + "");
    InvoiceProduct invoiceProduct = new InvoiceProduct(productModel, size, quantity);
    Invoices.add(invoiceProduct);
    revalidate();
    repaint();
  }

  private ActionListener PayAction = new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {
      InvoiceDetail invoiceDetail = new InvoiceDetail(orderItemList);
      invoiceDetail.setVisible(true);
    }

  };

}

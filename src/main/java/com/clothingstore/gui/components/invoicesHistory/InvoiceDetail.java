package com.clothingstore.gui.components.invoicesHistory;

import javax.swing.*;

import com.clothingstore.gui.components.invoiceDetail.Product;
import com.clothingstore.bus.CustomerBUS;
import com.clothingstore.bus.OrderItemBUS;
import com.clothingstore.bus.UserBUS;
import com.clothingstore.gui.components.invoiceDetail.HeaderInvoice;
import com.clothingstore.models.OrderItemModel;
import com.clothingstore.models.OrderModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDetail extends JPanel {

  private String name;
  private String value;

  OrderModel orderModel;
  List<OrderItemModel> orderItemModels;

  public InvoiceDetail(OrderModel orderModel) {
    this.orderModel = orderModel;
    orderItemModels = OrderItemBUS.getInstance().searchModel(String.valueOf(orderModel.getId()),
        new String[] { "order_id" });
    initComponents();
  }

  public InvoiceDetail(String name, String value) {
    this.name = name;
    this.value = value;
  }

  public List<InvoiceDetail> getData() {
    return new ArrayList<InvoiceDetail>() {
      {

        add(new InvoiceDetail("Id Invoice", "" + orderModel.getId()));
        add(new InvoiceDetail("Employee Name", UserBUS.getInstance().getModelById(orderModel.getUserId()).getName()));
        add(new InvoiceDetail("Date", "" + orderModel.getOrderDate()));
        add(new InvoiceDetail("Total", "" + orderModel.getTotalPrice()));
        add(new InvoiceDetail("Paying", "chưa tìm ra chỗ để lấy method"));
        add(new InvoiceDetail("Customer Name",
            CustomerBUS.getInstance().getModelById(orderModel.getCustomerId()).getCustomerName()));
        add(new InvoiceDetail("Customer Phone",
            CustomerBUS.getInstance().getModelById(orderModel.getCustomerId()).getPhone()));
        add(new InvoiceDetail("Products", "" + orderItemModels.size()));
      }
    };
  }

  public void initComponents() {
    NamePanel = new JLabel();
    Info = new JPanel();
    Products = new JPanel();
    Scroll = new JScrollPane();
    Product = new JPanel();
    HeaderProducts = new JPanel();

    mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());

    setLayout(new BorderLayout());
    setBackground(new Color(153, 194, 255));

    Scroll.setViewportView(mainPanel);

    Info.setLayout(new GridLayout(0, 1));

    NamePanel.setText("-- Detail --");
    NamePanel.setHorizontalAlignment(SwingConstants.CENTER);
    NamePanel.setVerticalAlignment(SwingConstants.CENTER);
    NamePanel.setFont(new Font("Segoe UI", 1, 17));
    NamePanel.setPreferredSize(new Dimension(150, 70));

    add(NamePanel, BorderLayout.NORTH);
    for (InvoiceDetail invoiceDetail : getData()) {
      JPanel panel = new JPanel();
      panel.setBackground(Color.WHITE);
      panel.setPreferredSize(new Dimension(60, 60));
      panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
      panel.setLayout(new BorderLayout());

      JLabel Name = new JLabel(invoiceDetail.name);
      Name.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
      Name.setFont(new Font("Segoe UI", 1, 14));
      panel.add(Name, BorderLayout.WEST);

      JLabel Value = new JLabel(invoiceDetail.value);
      Value.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 19));
      Value.setFont(new Font("Segoe UI", 0, 14));
      panel.add(Value, BorderLayout.EAST);

      Info.add(panel);
    }
    Products.setLayout(new BorderLayout());

    HeaderProducts.setLayout(new BorderLayout());
    HeaderProducts.setPreferredSize(new Dimension(100, 40));
    HeaderProducts.add(HeaderInvoice.getInstance(), BorderLayout.CENTER);

    Product.setLayout(new GridLayout(5, 1));
    for (int i = 0; i < orderItemModels.size(); i++) {
      Product product = new Product(orderModel, orderItemModels.get(i));
      Product.add(product);
    }

    Products.add(HeaderProducts, BorderLayout.NORTH);
    Products.add(Product, BorderLayout.CENTER);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(0, 0, 0, 10);

    mainPanel.add(Info, BorderLayout.CENTER);
    mainPanel.add(Products, BorderLayout.SOUTH);
    add(Scroll, BorderLayout.CENTER);
  }

  private JPanel mainPanel;
  private JLabel NamePanel;
  private JPanel Info;
  private JPanel Products;
  private JScrollPane Scroll;
  private JPanel Product;
  private JPanel HeaderProducts;
}

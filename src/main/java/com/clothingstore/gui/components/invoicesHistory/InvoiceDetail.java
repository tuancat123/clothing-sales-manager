package com.clothingstore.gui.components.invoicesHistory;

import javax.swing.*;

import com.clothingstore.bus.OrderItemBUS;
import com.clothingstore.bus.PaymentBUS;
import com.clothingstore.bus.PaymentMethodBUS;
import com.clothingstore.bus.ProductBUS;
import com.clothingstore.bus.UserBUS;
import com.clothingstore.gui.components.invoiceDetail.HeaderInvoice;
import com.clothingstore.gui.components.invoiceDetail.Product;
import com.clothingstore.models.CustomerModel;
import com.clothingstore.models.OrderItemModel;
import com.clothingstore.models.OrderModel;
import com.clothingstore.models.PaymentMethodModel;
import com.clothingstore.models.PaymentModel;
import com.clothingstore.models.ProductModel;
import com.clothingstore.models.UserModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDetail extends JPanel {

  private String name;
  private String value;
  private static OrderModel order;
  private static CustomerModel customer;
  private static List<PaymentModel> paymentModels;
  private static List<PaymentMethodModel> paymentMethodModels;
  private static List<OrderItemModel> orderItemModels;
  private static List<ProductModel> productModels;
  private static List<UserModel> userModels;
  //TODO: Need to optimize, runs pretty slow.
  public InvoiceDetail() {
    revalidate();
    repaint();
    initComponents();
  }

  public InvoiceDetail(OrderModel orderModel, CustomerModel customerModel) {
    revalidate();
    repaint();
    InvoiceDetail.order = orderModel;
    InvoiceDetail.customer = customerModel;

    // Initialize the lists here
    paymentModels = new ArrayList<>();
    paymentModels.addAll(PaymentBUS.getInstance().getAllModels());
    paymentModels.removeIf(paymentModel -> paymentModel.getOrderId() != InvoiceDetail.order.getId());

    paymentMethodModels = new ArrayList<>();
    paymentMethodModels.addAll(PaymentMethodBUS.getInstance().getAllModels());

    orderItemModels = new ArrayList<>();
    orderItemModels.addAll(OrderItemBUS.getInstance().getAllModels());
    orderItemModels.removeIf(orderItemModel -> orderItemModel.getOrderId() != InvoiceDetail.order.getId());

    productModels = new ArrayList<>();
    productModels.addAll(ProductBUS.getInstance().getAllModels());

    userModels = new ArrayList<>();
    userModels.addAll(UserBUS.getInstance().getAllModels());
    userModels.removeIf(userModel -> userModel.getId() != InvoiceDetail.order.getUserId());
    initComponents();
  }

  public InvoiceDetail(String name, String value) {
    this.name = name;
    this.value = value;
  }

  public static ArrayList<InvoiceDetail> getData() {
    // Initialize with an empty string by default
    String alter = "";
    if (InvoiceDetail.order.getUserId() == userModels.get(0).getId()) {
      alter = userModels.get(0).getName();
    }

    String employeeName = new String(alter);
    ArrayList<InvoiceDetail> data = new ArrayList<InvoiceDetail>() {
      {
        add(new InvoiceDetail("Id Invoice", "" + InvoiceDetail.order.getId()));
        add(new InvoiceDetail("Employee Name", employeeName));
        add(new InvoiceDetail("Date", "" + InvoiceDetail.order.getOrderDate()));
        add(new InvoiceDetail("Total", "" + InvoiceDetail.order.getTotalPrice()));
        add(new InvoiceDetail("Paying", "Cash"));
        add(new InvoiceDetail("Customer Name", InvoiceDetail.customer.getCustomerName()));
        add(new InvoiceDetail("Customer Phone", InvoiceDetail.customer.getPhone()));
        add(new InvoiceDetail("Products", "" + orderItemModels.size()));
      }
    };

    return data;
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
    for (int i = 0; i < 5; i++) {
      Product product = new Product();
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

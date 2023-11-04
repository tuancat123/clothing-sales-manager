package com.clothingstore.gui.components.invoicesHistory;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

import com.clothingstore.bus.CustomerBUS;
import com.clothingstore.bus.OrderBUS;
import com.clothingstore.models.CustomerModel;
import com.clothingstore.models.OrderModel;
import com.toedter.calendar.JDateChooser;

public class HistoryList extends JPanel {

  private static HistoryList instance;

  public static HistoryList getInstance() {
    if (instance == null) {
      instance = new HistoryList();
    }
    return instance;
  }

  public HistoryList() {
    initComponents();
  }

  private void initComponents() {

    Header = new JPanel();
    NameHeader = new JPanel();
    NamePanel = new JLabel();
    ButtonBack = new JButton();
    Panel = new JPanel();
    ButtonSearch = new JButton();
    SearchValue = new JTextField();
    Invoices = new JPanel();
    Scroll = new JScrollPane();
    ChooseDate = new JPanel();
    fillPanel = new JPanel();

    Color color = new Color(179, 209, 255);

    setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 4));
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(400, 170));
    setBackground(color);

    Header.setLayout(new BorderLayout());
    Header.setPreferredSize(new Dimension(400, 95));
    Header.setBackground(color);

    NameHeader.setLayout(new BorderLayout());

    NamePanel.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
    NamePanel.setHorizontalAlignment(SwingConstants.CENTER);
    NamePanel.setText("Hoạt Động");
    NameHeader.setBackground(color);
    NameHeader.add(NamePanel, BorderLayout.CENTER);

    ButtonBack.setBackground(new Color(242, 242, 242));
    ButtonBack.setIcon(new ImageIcon(getClass().getResource("/config/icon/back.png"))); // NOI18N
    ButtonBack.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 1));
    NameHeader.add(ButtonBack, BorderLayout.LINE_START);
    ButtonBack.addActionListener(closeHistoryList);

    Header.add(NameHeader, BorderLayout.NORTH);

    Panel.setBorder(BorderFactory.createEmptyBorder(4, 4, 7, 1));
    Panel.setLayout(new BorderLayout());
    Panel.setBackground(color);

    ButtonSearch.setIcon(new ImageIcon(getClass().getResource("/config/icon/search.png"))); // NOI18N
    ButtonSearch.setBorder(null);
    ButtonSearch.setBackground(Color.WHITE);
    Panel.add(ButtonSearch, BorderLayout.WEST);

    // ...

    SearchValue.setBackground(new Color(242, 242, 242));
    SearchValue.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
    SearchValue.setText("Tìm theo mã hóa đơn");
    SearchValue.setBorder(BorderFactory.createEmptyBorder(1, 6, 1, 1));
    SearchValue.addFocusListener(new FocusListener() {
      @Override
      public void focusGained(FocusEvent e) {
        if (SearchValue.getText().equals("Tìm theo mã hóa đơn")) {
          SearchValue.setText("");
        }
      }

      @Override
      public void focusLost(FocusEvent e) {
        if (SearchValue.getText().isEmpty()) {
          SearchValue.setText("Tìm theo mã hóa đơn");
        }
      }
    });

    Panel.add(SearchValue, BorderLayout.CENTER);

    setStartDate();
    setEndDate();
    setFilterButton();
    fillPanel.add(startDate, BorderLayout.WEST);
    fillPanel.add(endDate, BorderLayout.CENTER);
    fillPanel.add(filterButton, BorderLayout.EAST);
    Panel.add(fillPanel, BorderLayout.SOUTH);

    Header.add(Panel, BorderLayout.SOUTH);

    add(Header, BorderLayout.PAGE_START);

    Invoices.setLayout(new GridLayout(0, 1));
    Invoices.setBackground(color);
    // List<OrderModel> orderList = new ArrayList<>();
    // orderList.addAll(OrderBUS.getInstance().getAllModels());
    // List<CustomerModel> customerList = new ArrayList<>();
    // customerList.addAll(CustomerBUS.getInstance().getAllModels());

    // Map<Integer, CustomerModel> customerMap = new HashMap<>();

    // for (CustomerModel customer : customerList) {
    //   customerMap.put(customer.getId(), customer);
    // }

    // Iterator<OrderModel> orderIterator = orderList.iterator();
    // while (orderIterator.hasNext()) {
    //   OrderModel orderModel = orderIterator.next();
    //   int customerId = orderModel.getCustomerId();
    //   CustomerModel customerModel = customerMap.get(customerId);

    //   if (customerModel != null) {
    //     Invoice invoice = new Invoice(orderModel, customerModel);
    //     Invoices.add(invoice);
    //     InvoiceDetail invoiceDetail = new InvoiceDetail(orderModel, customerModel);
    //   }
    // }
    List<OrderModel> orderList = OrderBUS.getInstance().getAllModels();
    for(OrderModel orderModel: orderList){
      Invoice invoice = new Invoice(orderModel);
      Invoices.add(invoice);
    }

    Scroll.setViewportView(Invoices);
    add(Scroll, BorderLayout.CENTER);
  }

  private ActionListener closeHistoryList = e -> {
    setVisible(false);
  };

  public void setStartDate() {
    this.startDate = new JDateChooser();
    startDate.setBounds(40, 75, 150, 30);
    startDate.setDateFormatString("yyyy-MM-dd");
  }

  public void setEndDate() {
    this.endDate = new JDateChooser();
    endDate.setBounds(200, 75, 150, 30);
    endDate.setDateFormatString("yyyy-MM-dd");
  }

  public void setFilterButton() {
    this.filterButton = new JButton("Filter");
    filterButton.setBounds(360, 75, 80, 30);
  }

  private JButton ButtonBack;
  private JButton ButtonSearch;
  private JPanel Header;
  private JPanel Invoices;
  private JPanel NameHeader;
  private JLabel NamePanel;
  private JTextField SearchValue;
  private JPanel Panel;
  private JScrollPane Scroll;
  private JPanel ChooseDate;
  private JDateChooser startDate;
  private JDateChooser endDate;
  private JPanel fillPanel;
  private JButton filterButton;
}

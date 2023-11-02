package com.clothingstore.gui.components.invoicesHistory;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;

import com.clothingstore.bus.CustomerBUS;
import com.clothingstore.bus.OrderBUS;
import com.clothingstore.models.CustomerModel;
import com.clothingstore.models.OrderModel;

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

    Color color = new Color(179, 209, 255);

    setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 4));
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(322, 170));
    setBackground(color);

    Header.setLayout(new BorderLayout());
    Header.setPreferredSize(new Dimension(250, 65));
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

    SearchValue.setBackground(new Color(242, 242, 242));
    SearchValue.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
    SearchValue.setText("Tìm theo mã hóa đơn");
    SearchValue.setBackground(Color.white);
    SearchValue.setBorder(BorderFactory.createEmptyBorder(1, 6, 1, 1));
    Panel.add(SearchValue, BorderLayout.CENTER);

    Header.add(Panel, BorderLayout.SOUTH);

    add(Header, BorderLayout.PAGE_START);

    Invoices.setLayout(new GridLayout(0, 1));
    Invoices.setBackground(color);
    java.util.List<OrderModel> orderList = new ArrayList<>();
    orderList.addAll(OrderBUS.getInstance().getAllModels());
    java.util.List<CustomerModel> customerList = new ArrayList<>();
    customerList.addAll(CustomerBUS.getInstance().getAllModels());

    for (int i = 0; i < orderList.size(); i++) {
      for (int j = 0; j < customerList.size(); j++) {
        if (customerList.get(j).getId() == orderList.get(i).getCustomerId()) {
          Invoice invoice = new Invoice(orderList.get(i), customerList.get(j));
          Invoices.add(invoice);
          invoice.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
              throw new UnsupportedOperationException("Unimplemented");
              // new InvoiceDetail();
            }

            @Override
            public void mousePressed(MouseEvent e) {
              throw new UnsupportedOperationException("Unimplemented");
              // new InvoiceDetail();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
              throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
              throw new UnsupportedOperationException("Unimplemented");
            }

            @Override
            public void mouseExited(MouseEvent e) {
              throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
            }

          });
        }
      }

    }
    Scroll.setViewportView(Invoices);
    add(Scroll, BorderLayout.CENTER);
  }

  private ActionListener closeHistoryList = e -> {
    setVisible(false);
  };

  private JButton ButtonBack;
  private JButton ButtonSearch;
  private JPanel Header;
  private JPanel Invoices;
  private JPanel NameHeader;
  private JLabel NamePanel;
  private JTextField SearchValue;
  private JPanel Panel;
  private JScrollPane Scroll;
}

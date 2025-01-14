package com.clothingstore.gui.components.invoicesHistory;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import javax.swing.*;

import com.clothingstore.bus.OrderBUS;
import com.clothingstore.gui.models.NavData;
import com.clothingstore.models.OrderModel;
import com.toedter.calendar.JDateChooser;

public class HistoryList extends JPanel {

  private static HistoryList instance;
  Date currentDate = new Date();
  List<OrderModel> orderList;

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
    ButtonMenu = new JButton();
    Panel = new JPanel();
    ButtonSearch = new JButton();
    SearchValue = new JTextField();
    Invoices = new JPanel();
    Scroll = new JScrollPane();
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

    ButtonMenu.setIcon(new ImageIcon(getClass().getResource("/resources/icons/menu.png")));
    ButtonMenu.setBackground(color);
    ButtonMenu.setBorder(null);
    ButtonMenu.addActionListener(NavData.MenuAction());
    NameHeader.add(ButtonMenu, BorderLayout.LINE_START);

    Header.add(NameHeader, BorderLayout.NORTH);

    Panel.setBorder(BorderFactory.createEmptyBorder(4, 4, 7, 1));
    Panel.setLayout(new BorderLayout());
    Panel.setBackground(color);

    ButtonSearch.setIcon(new ImageIcon(getClass().getResource("/resources/icons/search.png"))); // NOI18N
    ButtonSearch.setBorder(null);
    ButtonSearch.setBackground(Color.WHITE);
    Panel.add(ButtonSearch, BorderLayout.WEST);

    SearchValue.setBackground(new Color(242, 242, 242));
    SearchValue.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
    SearchValue.setText("Tìm theo mã hóa đơn");
    SearchValue.setBackground(Color.WHITE);
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
    setRemoveFilterButton();
    fillPanel.setBackground(color);
    fillPanel.add(startDate, BorderLayout.WEST);
    fillPanel.add(endDate, BorderLayout.CENTER);
    fillPanel.add(filterButton, BorderLayout.EAST);
    fillPanel.add(removeFilterButton, BorderLayout.EAST);
    Panel.add(fillPanel, BorderLayout.SOUTH);

    Header.add(Panel, BorderLayout.SOUTH);

    add(Header, BorderLayout.PAGE_START);

    Invoices.setLayout(new GridLayout(0, 1));
    Invoices.setBackground(color);
    orderList = new ArrayList<>(OrderBUS.getInstance().getAllModels());
    Collections.reverse(orderList);
    CheckResponsive();
    for (OrderModel orderModel : orderList) {
      Invoice invoice = new Invoice(orderModel);
      Invoices.add(invoice);
    }

    Scroll.setViewportView(Invoices);
    Scroll.getVerticalScrollBar().setUnitIncrement(10);
    add(Scroll, BorderLayout.CENTER);
  }


  public void setStartDate() {
    this.startDate = new JDateChooser();
    startDate.setBounds(40, 75, 150, 30);
    startDate.setDateFormatString("yyyy-MM-dd");
    JTextField editor = (JTextField) startDate.getDateEditor();
    editor.setEditable(false);
  }

  public void setEndDate() {
    this.endDate = new JDateChooser();
    endDate.setBounds(200, 75, 150, 30);
    endDate.setDateFormatString("yyyy-MM-dd");
    JTextField editor = (JTextField) endDate.getDateEditor();
    editor.setEditable(false);
  }

  public void setFilterButton() {
    this.filterButton = new JButton("Filter");
    filterButton.setBounds(360, 75, 80, 30);
    filterButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Date fromDate = startDate.getDate();
        Date toDate = endDate.getDate();
        checkDate(fromDate, toDate);
        CheckResponsive();
      }
    });
  }

  public void setRemoveFilterButton() {
    this.removeFilterButton = new JButton("Remove Filter");
    removeFilterButton.setBounds(360, 75, 80, 30);
    removeFilterButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Invoices.removeAll();
        startDate.setDate(null);
        endDate.setDate(null);
        orderList = new ArrayList<>(OrderBUS.getInstance().getAllModels());
        Collections.reverse(orderList);
        for (OrderModel orderModel : orderList) {
          Invoice invoice = new Invoice(orderModel);
          Invoices.add(invoice);
        }
        CheckResponsive();
      }
    });
  }

  private void checkDate(Date fromDate, Date toDate) {
    if (fromDate == null && toDate != null) {
      JOptionPane.showMessageDialog(null, "không được để trống ngày bắt đầu", "Lỗi",
          JOptionPane.ERROR_MESSAGE);
    } else if (toDate == null && fromDate != null) {
      JOptionPane.showMessageDialog(null, "không được để trống ngày kết thúc", "Lỗi",
          JOptionPane.ERROR_MESSAGE);
    } else {
      int result1 = fromDate.compareTo(currentDate);
      int result2 = fromDate.compareTo(toDate);
      if (result1 > 0) {
        JOptionPane.showMessageDialog(null, "Ngày bắt đầu không thể lớn hơn ngày hiện tại.", "Lỗi",
            JOptionPane.ERROR_MESSAGE);
        startDate.setDate(null);
      } else if (result2 > 0) {
        JOptionPane.showMessageDialog(null, "Ngày bắt đầu không thể lớn hơn ngày kết thúc.", "Lỗi",
            JOptionPane.ERROR_MESSAGE);
        startDate.setDate(null);
      } else {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fromDateStr = (fromDate != null) ? sdf.format(fromDate) : null;
        String toDateStr = (toDate != null) ? sdf.format(toDate) : null;

        orderList = new ArrayList<>(OrderBUS.getInstance().searchDateToDate(fromDateStr, toDateStr));
        Collections.reverse(orderList);
        if (orderList.isEmpty()) {
          JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn trong khoảng thời gian đã chọn.", "Thông báo",
              JOptionPane.INFORMATION_MESSAGE);
        } else {
          Invoices.removeAll();
          for (OrderModel orderModel : orderList) {
            Invoice invoice = new Invoice(orderModel);
            Invoices.add(invoice);
          }
        }
      }
    }
  }

  private void CheckResponsive(){
    if(orderList.size() > 10)
      Invoices.setLayout(new GridLayout(0, 1));
    else 
      Invoices.setLayout(new GridLayout(10, 1));

  }

  private JButton ButtonMenu;
  private JButton ButtonSearch;
  private JPanel Header;
  private JPanel Invoices;
  private JPanel NameHeader;
  private JLabel NamePanel;
  private JTextField SearchValue;
  private JPanel Panel;
  private JScrollPane Scroll;
  private JDateChooser startDate;
  private JDateChooser endDate;
  private JPanel fillPanel;
  private JButton filterButton;
  private JButton removeFilterButton;
}

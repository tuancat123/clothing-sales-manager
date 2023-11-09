package com.clothingstore.gui.employee.invoiceDetail;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.netbeans.lib.awtextra.*;

import com.clothingstore.bus.CustomerBUS;
import com.clothingstore.bus.OrderBUS;
import com.clothingstore.bus.OrderItemBUS;
import com.clothingstore.bus.PointBUS;
import com.clothingstore.bus.ProductBUS;
import com.clothingstore.bus.SizeItemBUS;
import com.clothingstore.gui.components.InvoiceProduct;
import com.clothingstore.models.CustomerModel;
import com.clothingstore.models.OrderItemModel;
import com.clothingstore.models.OrderModel;
import com.clothingstore.models.ProductModel;
import com.clothingstore.models.SizeItemModel;

import services.Authentication;
import services.PDFWriter;

public class InvoiceDetail extends JFrame {
  java.util.List<OrderItemModel> orderItemModel = new ArrayList<>();
  double totalInvoice = 0;

  public InvoiceDetail(List<OrderItemModel> orderList) {
    initComponents(orderList);
    setAlwaysOnTop(true);
    setLocationRelativeTo(null);
    CustomerInfo.setVisible(false);
  }

  private void initComponents(List<OrderItemModel> orderList) {
    GridBagConstraints gridBagConstraints;
    setPreferredSize(new Dimension(800, 600));

    Header = new JPanel();
    NameFrame = new JLabel();
    Content = new JPanel();
    ContentHeader = new JPanel();
    Scroll = new JScrollPane();
    Products = new JPanel();
    Footer = new JPanel();
    CustomerPanel = new JPanel();
    CashCheckBox = new JCheckBox();
    CreditCheckBox = new JCheckBox();
    RegularCus = new JCheckBox();
    WalkInCus = new JCheckBox();
    CustomerInfo = new JPanel();
    NameText = new JLabel();
    Point = new JCheckBox();
    PhoneText = new JLabel();
    Name = new JTextField();
    Phone = new JTextField();
    UsePoint = new JTextField();
    Buttons = new JPanel();
    ButtonExit = new JButton();
    ButtonPay = new JButton();
    PayPanel = new JPanel();
    TotalText = new JLabel();
    CusPayingText = new JLabel();
    ChangeText = new JLabel();
    Change = new JTextField();
    TotalInvoiceText = new JLabel();
    PointText = new JLabel();
    Total = new JTextField();
    CusPaying = new JTextField();
    TotalInvoice = new JTextField();
    Discount = new JTextField();
    Slogan = new JLabel("Respecting your customers is respecting yourself");

    Header.setLayout(new AbsoluteLayout());

    NameFrame.setFont(new Font("Segoe UI", 1, 24));
    NameFrame.setText("Invoice Products");

    Content.setLayout(new BorderLayout());

    ContentHeader.setBorder(BorderFactory.createEtchedBorder());
    ContentHeader.setPreferredSize(new Dimension(800, 40));
    ContentHeader.setLayout(new BorderLayout());

    Products.setBackground(new Color(255, 255, 255));
    Products.setLayout(new GridLayout(0, 1));

    int sr = 1;
    for (OrderItemModel orderItemModel : orderList) {
      InvoiceProduct product = new InvoiceProduct(orderItemModel, sr);
      sr++;
      product.setBackground(Color.WHITE);
      Products.add(product);
    }

    Scroll.setViewportView(Products);

    Footer.setLayout(new BorderLayout());

    CustomerPanel.setLayout(new AbsoluteLayout());

    RegularCus.setFont(new Font("Segoe UI", 1, 15));
    RegularCus.setForeground(new Color(0, 102, 102));
    RegularCus.setText("Regular customers");
    RegularCus.addActionListener(RegularCusOnCheck);

    WalkInCus.setFont(new Font("Segoe UI", 1, 15));
    WalkInCus.setSelected(true);
    WalkInCus.setText("Walk-in customers");
    WalkInCus.addActionListener(WalkInCusOnCheck);

    CustomerInfo.setLayout(new AbsoluteLayout());

    NameText.setFont(new Font("Segoe UI", 3, 14));
    NameText.setText("Name:");
    Point.setFont(new Font("Segoe UI", 0, 14));
    Point.setForeground(new Color(255, 102, 0));
    Point.setText("0 Point");

    PhoneText.setFont(new Font("Segoe UI", 3, 14));
    PhoneText.setText("Phone: ");

    Name.setFont(new Font("Segoe UI", 0, 14));
    Name.setHorizontalAlignment(JTextField.RIGHT);

    Phone.setFont(new Font("Segoe UI", 0, 14));
    Phone.addFocusListener(LostFocusPhone);
    Phone.setHorizontalAlignment(JTextField.RIGHT);

    UsePoint.setFont(new Font("Segoe UI", 0, 14));
    UsePoint.setHorizontalAlignment(JTextField.RIGHT);

    Slogan.setFont(new Font("Segoe UI", 2, 13));

    Buttons.setBorder(BorderFactory.createEtchedBorder());
    Buttons.setBackground(new Color(51, 51, 204));
    Buttons.setLayout(new GridBagLayout());

    ButtonExit.setBackground(new Color(153, 255, 255));
    ButtonExit.setFont(new Font("Segoe UI", 1, 14));
    ButtonExit.setText("Exit");
    ButtonExit.setPreferredSize(new Dimension(80, 30));
    ButtonExit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        dispose();
      }
    });
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.insets = new Insets(10, 10, 10, 40);

    ButtonPay.setBackground(new Color(153, 255, 255));
    ButtonPay.setFont(new Font("Segoe UI", 1, 14));
    ButtonPay.setText("Pay");
    ButtonPay.setPreferredSize(new Dimension(80, 30));

    PayPanel.setPreferredSize(new Dimension(280, 230));
    PayPanel.setLayout(new AbsoluteLayout());

    TotalText.setFont(new Font("Segoe UI", 3, 14));
    TotalText.setForeground(new Color(255, 0, 51));
    TotalText.setHorizontalAlignment(SwingConstants.RIGHT);
    TotalText.setText("Total:");

    TotalInvoiceText.setFont(new Font("Segoe UI", 3, 14));
    TotalInvoiceText.setForeground(new Color(51, 51, 255));
    TotalInvoiceText.setHorizontalAlignment(SwingConstants.RIGHT);
    TotalInvoiceText.setText("Total Invoice:");

    PointText.setFont(new Font("Segoe UI", 3, 14));
    PointText.setForeground(new Color(51, 51, 255));
    PointText.setHorizontalAlignment(SwingConstants.RIGHT);
    PointText.setText("Point:");

    Total.setFont(new Font("Segoe UI", 0, 15));
    Total.setForeground(new Color(255, 51, 51));
    Total.setText("3000000");

    TotalInvoice.setFont(new Font("Segoe UI", 0, 14));
    for (OrderItemModel orderItemModel : orderList) {
      totalInvoice = totalInvoice + orderItemModel.getPrice() * orderItemModel.getQuantity();
    }
    TotalInvoice.setText("" + String.valueOf(totalInvoice));

    Discount.setFont(new Font("Segoe UI", 0, 14));
    Discount.setForeground(new Color(153, 0, 51));
    Discount.setText("3000");

    CashCheckBox.setFont(new Font("Segoe UI", 1, 15));
    CashCheckBox.setForeground(new Color(0, 102, 102));
    CashCheckBox.setSelected(true);
    CashCheckBox.setText("Cash");
    CashCheckBox.addActionListener(CashOnCheck);

    CreditCheckBox.setFont(new Font("Segoe UI", 1, 15));
    CreditCheckBox.setForeground(new Color(0, 102, 102));
    CreditCheckBox.setText("Credit");
    CreditCheckBox.addActionListener(CreditOnCheck);

    CusPayingText.setFont(new Font("Segoe UI", 3, 14));
    CusPayingText.setForeground(new Color(51, 51, 255));
    CusPayingText.setHorizontalAlignment(SwingConstants.RIGHT);
    CusPayingText.setText("Customer Paying: ");

    CusPaying.setFont(new Font("Segoe UI", 0, 14));
    CusPaying.setForeground(new Color(255, 51, 255));
    CusPaying.addFocusListener(LostFocusCustomerPay);

    // if (Double.parseDouble(CusPaying.getText()) < totalInvoice) {
    // JOptionPane.showMessageDialog(null, "Tiền khách hàng đưa ít hơn tiền hóa
    // đơn.");
    // }

    ChangeText.setFont(new Font("Segoe UI", 3, 14));
    ChangeText.setForeground(new Color(51, 51, 255));
    ChangeText.setHorizontalAlignment(SwingConstants.RIGHT);
    ChangeText.setText("Change: ");

    Change.setFont(new Font("Segoe UI", 0, 14));
    Change.setForeground(new Color(255, 51, 255));

    ButtonPay.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        boolean isCashSelected = CashCheckBox.isSelected();
        boolean isCreditSelected = CreditCheckBox.isSelected();
        boolean isRegularCustomer = RegularCus.isSelected();

        if (isCashSelected) {
          double change = 0;
          if (!CusPaying.getText().isBlank() && !CusPaying.getText().isEmpty()) {
            double cusPaying = Double.parseDouble(CusPaying.getText());
            double totalReceipt = Double.parseDouble(Total.getText());
            change = cusPaying - totalReceipt;
          }
          if (change >= 0) {
            JOptionPane.showMessageDialog(null, "Thanh toán thành công và số tiền cần phải thối là " + change + " đ");
            OrderModel orderModel = new OrderModel();
            if (isRegularCustomer) {
              java.util.List<CustomerModel> customerModel = CustomerBUS.getInstance().searchModel(
                  String.valueOf(Phone.getText()),
                  new String[] { "phone" });
              orderModel.setCustomerId(customerModel.get(0).getId());
            }
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            orderModel.setUserId(Authentication.getCurrentUser().getId());
            orderModel.setOrderDate(currentTime);
            orderModel.setTotalPrice(0);
            OrderBUS.getInstance().addModel(orderModel);
            for (OrderItemModel orderItemModel : orderItemModel) {
              ProductModel productModel = ProductBUS.getInstance().getModelById(orderItemModel.getProductId());
              List<SizeItemModel> sizeItemModels = SizeItemBUS.getInstance()
                  .searchModel(String.valueOf(productModel.getId()), new String[] { "product_id" });
              for (SizeItemModel sizeItemModel : sizeItemModels) {
                if (orderItemModel.getSizeId() == sizeItemModel.getSizeId()) {
                  sizeItemModel.setQuantity(sizeItemModel.getQuantity() - orderItemModel.getQuantity());
                  SizeItemBUS.getInstance().updateModel(sizeItemModel);
                  break;
                }
              }
              OrderItemBUS.getInstance().addModel(orderItemModel);
            }
            int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn xuất hóa đơn không?");
            if (choice == JOptionPane.YES_OPTION) {
              JFileChooser fileChooser = new JFileChooser();
              fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
              FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Files", "pdf");
              fileChooser.setFileFilter(filter);
              int result = fileChooser.showSaveDialog(null);
              if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String filePath = selectedFile.getAbsolutePath();
                if (!filePath.toLowerCase().endsWith(".pdf")) {
                  filePath += ".pdf";
                }
                PDFWriter.getInstance().exportReceiptToPDF(orderModel, filePath);
              }
            } else {
              return;
            }
          } else {
            JOptionPane.showMessageDialog(null, "Số tiền khách trả ít hơn số tiền ở hóa đơn. Vui lòng kiểm tra lại.");
            return;
          }
        }
      }
    });

    Header.add(NameFrame, new AbsoluteConstraints(10, 0, 410, 30));
    getContentPane().add(Header, BorderLayout.PAGE_START);

    ContentHeader.add(HeaderInvoice.getInstance(), BorderLayout.CENTER);
    Content.add(ContentHeader, BorderLayout.PAGE_START);
    Content.add(Scroll, BorderLayout.CENTER);
    getContentPane().add(Content, BorderLayout.CENTER);

    CustomerPanel.add(RegularCus, new AbsoluteConstraints(210, 10, 170, -1));
    CustomerPanel.add(WalkInCus, new AbsoluteConstraints(30, 10, 170, -1));

    CustomerInfo.add(PhoneText, new AbsoluteConstraints(20, 20, -1, -1));
    CustomerInfo.add(Phone, new AbsoluteConstraints(80, 20, 140, 25));
    CustomerInfo.add(NameText, new AbsoluteConstraints(240, 20, -1, -1));
    CustomerInfo.add(Name, new AbsoluteConstraints(300, 20, 170, 25));
    CustomerInfo.add(Point, new AbsoluteConstraints(70, 50, -1, 20));
    CustomerInfo.add(UsePoint, new AbsoluteConstraints(190, 50, 150, 20));
    CustomerPanel.add(CustomerInfo, new AbsoluteConstraints(10, 40, 500, 120));

    CustomerPanel.add(Slogan, new AbsoluteConstraints(60, 0, 500, 120));

    Footer.add(CustomerPanel, BorderLayout.CENTER);

    Buttons.add(ButtonExit, gridBagConstraints);
    Buttons.add(ButtonPay, new GridBagConstraints());
    Footer.add(Buttons, BorderLayout.PAGE_END);

    PayPanel.add(TotalInvoiceText, new AbsoluteConstraints(10, 10, 130, 30));
    PayPanel.add(TotalInvoice, new AbsoluteConstraints(160, 10, 110, 30));
    PayPanel.add(PointText, new AbsoluteConstraints(10, 50, 130, 30));
    PayPanel.add(Discount, new AbsoluteConstraints(160, 50, 110, 30));
    PayPanel.add(TotalText, new AbsoluteConstraints(10, 90, 130, 30));
    PayPanel.add(Total, new AbsoluteConstraints(160, 90, 110, 30));
    PayPanel.add(CashCheckBox, new AbsoluteConstraints(60, 130, 60, 30));
    PayPanel.add(CreditCheckBox, new AbsoluteConstraints(170, 130, 80, 30));
    PayPanel.add(CusPayingText, new AbsoluteConstraints(0, 160, 130, 30));
    PayPanel.add(CusPaying, new AbsoluteConstraints(140, 160, 110, 30));
    PayPanel.add(ChangeText, new AbsoluteConstraints(0, 195, 130, 30));
    PayPanel.add(Change, new AbsoluteConstraints(140, 195, 110, 30));
    Footer.add(PayPanel, BorderLayout.EAST);

    getContentPane().add(Footer, BorderLayout.PAGE_END);
    pack();
  }

  private JButton ButtonExit;
  private JButton ButtonPay;
  private JPanel Buttons;
  private JPanel Content;
  private JPanel CustomerInfo;
  private JPanel CustomerPanel;
  private JTextField Discount;
  private JPanel Footer;
  private JPanel Products;
  private JPanel ContentHeader;
  private JPanel Header;
  private JTextField Name;
  private JLabel NameFrame;
  private JLabel NameText;
  private JPanel PayPanel;
  private JTextField Phone;
  private JLabel PhoneText;
  private JCheckBox Point;
  private JLabel PointText;
  private JLabel TotalInvoiceText;
  private JCheckBox CashCheckBox;
  private JCheckBox CreditCheckBox;
  private JTextField CusPaying;
  private JLabel CusPayingText;
  private JLabel ChangeText;
  private JTextField Change;
  private JCheckBox RegularCus;
  private JScrollPane Scroll;
  private JTextField Total;
  private JTextField TotalInvoice;
  private JLabel TotalText;
  private JTextField UsePoint;
  private JCheckBox WalkInCus;
  private JLabel Slogan;

  public ActionListener WalkInCusOnCheck = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent arg0) {
      if (WalkInCus.isSelected()) {
        RegularCus.setSelected(false);
        UsePoint.setVisible(false);
        CustomerInfo.setVisible(false);
        Slogan.setVisible(true);
      }
    }
  };

  public ActionListener RegularCusOnCheck = new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent arg0) {
      if (RegularCus.isSelected()) {
        WalkInCus.setSelected(false);
        CustomerInfo.setVisible(true);
        Slogan.setVisible(false);
      }
    }

  };

  public ActionListener CashOnCheck = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent arg0) {
      CreditCheckBox.setSelected(false);
      PayPanel.setPreferredSize(new Dimension(280, 230));
      CusPayingText.setVisible(true);
      CusPaying.setVisible(true);
      ChangeText.setVisible(true);
      Change.setVisible(true);
      PayPanel.revalidate();
      PayPanel.repaint();
    }

  };
  public ActionListener CreditOnCheck = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent arg0) {
      CashCheckBox.setSelected(false);
      PayPanel.setPreferredSize(new Dimension(280, 170));
      CusPayingText.setVisible(false);
      CusPaying.setVisible(false);
      ChangeText.setVisible(false);
      Change.setVisible(false);
      PayPanel.revalidate();
      PayPanel.repaint();
    }

  };
  public FocusListener LostFocusPhone = new FocusListener() {

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
      List<CustomerModel> customerList = CustomerBUS.getInstance().searchModel(String.valueOf(Phone.getText()),
          new String[] { "phone" });
      if (customerList != null) {
        CustomerModel customerModel = customerList.get(0);
        Name.setText(customerModel.getCustomerName());
        // System.out.println(PointBUS.getInstance().searchModel(String.valueOf(customerModel.getId()),
        // new String[]{"customer_id"}));
        Point.setText(
            PointBUS.getInstance().searchModel(String.valueOf(customerModel.getId()), new String[] { "customer_id" })
                .get(0).getPointsEarned() + " Point");
        revalidate();
        repaint();
      }
    }

  };
  public FocusListener LostFocusCustomerPay = new FocusListener() {
    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
      boolean isWalkingCustomerSelected = WalkInCus.isSelected();
      if (isWalkingCustomerSelected) {
        Discount.setText("0");
      }
    }
  };
}
package com.clothingstore.gui.components;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;

import org.netbeans.lib.awtextra.*;

import com.clothingstore.bus.CategoryBUS;
import com.clothingstore.bus.ImportItemsBUS;
import com.clothingstore.bus.ProductBUS;
import com.clothingstore.bus.SizeBUS;
import com.clothingstore.bus.SizeItemBUS;
import com.clothingstore.models.ImportItemsModel;
import com.clothingstore.models.OrderItemModel;
import com.clothingstore.models.ProductModel;
import com.clothingstore.models.SizeItemModel;
import com.clothingstore.models.SizeModel;
import com.clothingstore.models.UserModel;
import services.Authentication;

import com.clothingstore.gui.employee.Invoice;

public class ProductDetail extends JFrame {

  public Authentication authentication;
  static UserModel currentUser = Authentication.getCurrentUser();
  HomePage homePage = HomePage.getInstance();
  Color color = new Color(230, 240, 255);
  int selectedSizeId = -1;

  public ProductDetail(ProductModel productModel) {
    setAlwaysOnTop(true);
    setSize(new Dimension(685, 390));
    setPreferredSize(new Dimension(685, 390));
    setResizable(false);
    getContentPane().setLayout(new AbsoluteLayout());
    getContentPane().setBackground(color);
    setLocationRelativeTo(null);

    initComponents(productModel);
  }

  ImportItemsModel importItemsModel = null;
  SizeItemModel sizeItemModel = null;
  SizeModel sizeModel = null;

  private void initComponents(ProductModel productModel) {
    int selectedProductId = productModel.getId();
    List<ImportItemsModel> importItemsModels = new ArrayList<>();
    List<ProductModel> productModels = new ArrayList<>();
    List<SizeItemModel> sizeItemModels = new ArrayList<>();
    List<SizeModel> sizeModels = new ArrayList<>();

    importItemsModels.addAll(ImportItemsBUS.getInstance().getAllModels());
    productModels.addAll(ProductBUS.getInstance().getAllModels());
    sizeItemModels.addAll(SizeItemBUS.getInstance().getAllModels());
    sizeModels.addAll(SizeBUS.getInstance().getAllModels());

    Iterator<ImportItemsModel> importItemsIterator = importItemsModels.iterator();
    while (importItemsIterator.hasNext()) {
      ImportItemsModel importItemsModel = importItemsIterator.next();
      if (importItemsModel.getProduct_id() != productModel.getId()) {
        importItemsIterator.remove();
      }
    }

    Iterator<SizeItemModel> sizeItemIterator = sizeItemModels.iterator();
    while (sizeItemIterator.hasNext()) {
      SizeItemModel sizeItemModel = sizeItemIterator.next();
      if (sizeItemModel.getProductId() != productModel.getId()) {
        sizeItemIterator.remove();
      }
    }

    ImagePanel = new JPanel();
    Name = new JTextField();
    Rating = new JLabel();
    OriPrice = new JLabel();
    Price = new JLabel();
    Id = new JLabel();
    AmountText = new JLabel();
    CategoryText = new JLabel();
    Gender = new JLabel();
    Category = new JLabel();
    GenderText = new JLabel();
    Scroll = new JScrollPane();
    Describe = new JTextPane();
    DescribeText = new JLabel();
    SizePanel = new JPanel();
    ButtonExit = new JButton();
    ButtonAdd = new JButton();
    buttonDelete = new JButton();
    Remaining = new JLabel();

    ImagePanel.setLayout(new GridBagLayout());

    // try {
    // BufferedImage originalImage = ImageUtil.fromBase64(productModel.getImage());
    // Image scaledImage = originalImage.getScaledInstance(180, 180,
    // java.awt.Image.SCALE_SMOOTH);
    // ImageIcon scaledIcon = new ImageIcon(scaledImage);
    // JLabel imageLabel = new JLabel(scaledIcon);
    // JPanel imagePanel = new JPanel(new GridBagLayout());
    // imagePanel.add(imageLabel, new GridBagConstraints());
    // imagePanel.setBackground(color);
    // getContentPane().add(imagePanel, new AbsoluteConstraints(10, 20, 190, 270));
    // } catch (IOException e) {
    // e.printStackTrace();
    // }

    Name.setEditable(false);
    Name.setBackground(new Color(255, 255, 255));
    Name.setFont(new Font("Segoe UI", 1, 16));
    Name.setText(productModel.getName());
    Name.setAutoscrolls(false);

    getContentPane().add(Name, new AbsoluteConstraints(240, 20, 260, 40));

    Rating.setFont(new Font("Segoe UI", 0, 14));
    Rating.setForeground(new Color(102, 102, 255));
    Rating.setText("Rating: ");
    getContentPane().add(Rating, new AbsoluteConstraints(240, 60, 70, 20));

    OriPrice.setFont(new Font("Segoe UI", 0, 17));
    OriPrice.setForeground(new Color(153, 153, 153));
    Double originPrice = productModel.getPrice() + 150000;
    OriPrice.setText("<html><s> " + originPrice + "</s></html>");
    getContentPane().add(OriPrice, new AbsoluteConstraints(240, 80, 70, 30));

    Price.setFont(new Font("Segoe UI", 0, 21));
    Price.setForeground(new Color(255, 51, 51));
    Price.setText(productModel.getPrice() + "đ");
    getContentPane().add(Price, new AbsoluteConstraints(310, 70, -1, 50));

    Id.setFont(new Font("Segoe UI Semibold", 0, 13));
    Id.setHorizontalAlignment(SwingConstants.RIGHT);
    Id.setText(String.valueOf(productModel.getId()));
    getContentPane().add(Id, new AbsoluteConstraints(600, 10, 60, 20));

    AmountText.setFont(new Font("Segoe UI", 2, 15));
    AmountText.setForeground(new Color(102, 102, 102));
    AmountText.setHorizontalAlignment(SwingConstants.RIGHT);
    AmountText.setText("Amount:");
    getContentPane().add(AmountText, new AbsoluteConstraints(240, 240, 60, 20));

    CategoryText.setFont(new Font("Segoe UI", 2, 15));
    CategoryText.setForeground(new Color(102, 102, 102));
    CategoryText.setText("Category:");
    getContentPane().add(CategoryText, new AbsoluteConstraints(240, 110, 70, -1));

    Gender.setFont(new Font("Segoe UI", 0, 16));
    if (productModel.getGender().equals("1")) {
      Gender.setText("Nam");
    } else {
      Gender.setText("Nữ");
    }

    getContentPane().add(Gender, new AbsoluteConstraints(320, 130, 130, 30));

    Category.setFont(new Font("Segoe UI", 0, 16));
    Category.setForeground(new Color(0, 51, 51));
    Category.setText(CategoryBUS.getInstance().getCategoryById(productModel.getCategoryId()).getCategoryName());
    getContentPane().add(Category, new AbsoluteConstraints(320, 110, 130, 20));

    GenderText.setFont(new Font("Segoe UI", 2, 15));
    GenderText.setForeground(new Color(102, 102, 102));
    GenderText.setHorizontalAlignment(SwingConstants.RIGHT);
    GenderText.setText("Gender:");
    getContentPane().add(GenderText, new AbsoluteConstraints(240, 130, 60, 30));

    // TODO: ERD Không có cột Description?
    Describe.setFont(new Font("Segoe UI", 0, 14));
    Describe.setText("Trong ví dụ này, chúng ta sử dụng GridBagLayout để quản lý việc");
    Scroll.setViewportView(Describe);

    getContentPane().add(Scroll, new AbsoluteConstraints(320, 160, 230, 70));

    DescribeText.setFont(new Font("Segoe UI", 2, 15));
    DescribeText.setForeground(new Color(102, 102, 102));
    DescribeText.setHorizontalAlignment(SwingConstants.RIGHT);
    DescribeText.setText("Describe:");
    getContentPane().add(DescribeText, new AbsoluteConstraints(240, 160, 60, 20));

    JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
    spinner.setBackground(new Color(255, 204, 204));
    getContentPane().add(spinner, new AbsoluteConstraints(320, 240, 60, 20));

    SizePanel.setBackground(new Color(204, 255, 204));
    SizePanel.setLayout(new java.awt.GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(0, 0, 0, 10);

    ButtonGroup sizeGroup = new ButtonGroup();
    JRadioButton sizeS = new JRadioButton("S");
    sizeS.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        selectedSizeId = 1;
        int spinnerValue = (int) spinner.getValue();
        for (int i = 0; i < sizeItemModels.size(); i++) {
          if (sizeItemModels.get(i).getSizeId() == 1) {
            Remaining.setFont(new Font("Segoe UI", 0, 15));
            Remaining.setForeground(new Color(102, 102, 102));
            Remaining.setText(sizeItemModels.get(i).getQuantity() + " sản phẩm có sẵn.");
            getContentPane().add(Remaining, new AbsoluteConstraints(440, 240, 170, 17));
            if (spinnerValue >= sizeItemModels.get(i).getQuantity()) {
              spinner.setValue(sizeItemModels.get(i).getQuantity());
            }
            break;
          }
        }
      }
    });

    JRadioButton sizeM = new JRadioButton("M");
    sizeM.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        selectedSizeId = 2;
        int spinnerValue = (int) spinner.getValue();
        for (int i = 0; i < sizeItemModels.size(); i++) {
          if (sizeItemModels.get(i).getSizeId() == 2) {
            Remaining.setFont(new Font("Segoe UI", 0, 15));
            Remaining.setForeground(new Color(102, 102, 102));
            Remaining.setText(sizeItemModels.get(i).getQuantity() + " sản phẩm có sẵn.");
            getContentPane().add(Remaining, new AbsoluteConstraints(440, 240, 170, 17));
            if (spinnerValue >= sizeItemModels.get(i).getQuantity()) {
              spinner.setValue(sizeItemModels.get(i).getQuantity());
            }
            break;
          }
        }
      }

    });
    JRadioButton sizeL = new JRadioButton("L");
    sizeL.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        selectedSizeId = 3;
        int spinnerValue = (int) spinner.getValue();
        for (int i = 0; i < sizeItemModels.size(); i++) {
          if (sizeItemModels.get(i).getSizeId() == 3) {
            Remaining.setFont(new Font("Segoe UI", 0, 15));
            Remaining.setForeground(new Color(102, 102, 102));
            Remaining.setText(sizeItemModels.get(i).getQuantity() + " sản phẩm có sẵn.");
            getContentPane().add(Remaining, new AbsoluteConstraints(440, 240, 170, 17));
            if (spinnerValue >= sizeItemModels.get(i).getQuantity()) {
              spinner.setValue(sizeItemModels.get(i).getQuantity());
            }
            break;
          }
        }
      }

    });
    JRadioButton sizeXL = new JRadioButton("XL");
    sizeXL.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        selectedSizeId = 4;
        int spinnerValue = (int) spinner.getValue();
        for (int i = 0; i < sizeItemModels.size(); i++) {
          if (sizeItemModels.get(i).getSizeId() == 4) {
            Remaining.setFont(new Font("Segoe UI", 0, 15));
            Remaining.setForeground(new Color(102, 102, 102));
            Remaining.setText(sizeItemModels.get(i).getQuantity() + " sản phẩm có sẵn.");
            getContentPane().add(Remaining, new AbsoluteConstraints(440, 240, 170, 17));
            if (spinnerValue >= sizeItemModels.get(i).getQuantity()) {
              spinner.setValue(sizeItemModels.get(i).getQuantity());
            }
            break;
          }
        }
      }

    });
    JRadioButton sizeXXL = new JRadioButton("XXL");
    sizeXXL.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        selectedSizeId = 5;
        int spinnerValue = (int) spinner.getValue();
        for (int i = 0; i < sizeItemModels.size(); i++) {
          if (sizeItemModels.get(i).getSizeId() == 5) {
            Remaining.setFont(new Font("Segoe UI", 0, 15));
            Remaining.setForeground(new Color(102, 102, 102));
            Remaining.setText(sizeItemModels.get(i).getQuantity() + " sản phẩm có sẵn.");
            getContentPane().add(Remaining, new AbsoluteConstraints(440, 240, 170, 17));
            if (spinnerValue >= sizeItemModels.get(i).getQuantity()) {
              spinner.setValue(sizeItemModels.get(i).getQuantity());
            }
            break;
          }
        }
      }

    });

    sizeGroup.add(sizeS);
    sizeGroup.add(sizeM);
    sizeGroup.add(sizeL);
    sizeGroup.add(sizeXL);
    sizeGroup.add(sizeXXL);

    SizePanel.add(sizeS);
    SizePanel.add(sizeM);
    SizePanel.add(sizeL);
    SizePanel.add(sizeXL);
    SizePanel.add(sizeXXL);
    getContentPane().add(SizePanel, new AbsoluteConstraints(250, 270, 250, 20));

    ButtonExit.setText("Exit");
    ButtonExit.setPreferredSize(new Dimension(72, 28));
    ButtonExit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });
    getContentPane().add(ButtonExit, new AbsoluteConstraints(200, 320, -1, -1));

    if (currentUser.getRoleId() == 3) {
      System.out.println(currentUser.getRoleId());
      ButtonAdd.setText("Add To Cart");
      ButtonAdd.setPreferredSize(new Dimension(94, 28));
      ButtonAdd.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (selectedProductId != -1 && selectedSizeId != -1) {
            int quantity = (int) spinner.getValue();
            OrderItemModel orderItem = new OrderItemModel();
            orderItem.setProductId(selectedProductId);
            orderItem.setSizeId(selectedSizeId);
            orderItem.setQuantity(quantity);
            List<ProductModel> productModels = ProductBUS.getInstance().searchModel(String.valueOf(selectedProductId),
                new String[] { "id" });
            orderItem.setPrice(productModels.get(0).getPrice());
            //TODO: Fix trùng lặp trong giỏ hàng, đoạn code đã note dưới gây ra crash ứng dụng.
            // if (Product.cartItems != null && !Product.cartItems.isEmpty()) {
            //   for (OrderItemModel orderItemModel : Product.cartItems) {
            //     if (selectedProductId == orderItemModel.getProductId()
            //         && selectedSizeId == orderItemModel.getSizeId()) {
            //       JOptionPane.showMessageDialog(null, "Sản phẩm này với size bạn chọn đã có trong giỏ hàng của bạn!");
            //     }
            //   }
            // }
            Product.cartItems.add(orderItem);
            Invoice invoice = new Invoice(Product.cartItems);
            homePage.add(invoice, BorderLayout.EAST);
            homePage.setVisible(true);
          }
        }
      });
      getContentPane().add(ButtonAdd, new AbsoluteConstraints(380, 320, -1, -1));
    } else {
      System.out.println(currentUser.getRoleId());
      buttonDelete.setText("Delete");
      buttonDelete.setPreferredSize(new Dimension(94, 28));
      buttonDelete.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          JOptionPane.showMessageDialog(null, "Do you want to delete this product?", "Notification",
              JOptionPane.ERROR_MESSAGE);
        }
      });
      getContentPane().add(buttonDelete, new AbsoluteConstraints(380, 320, -1, -1));
    }

    pack();
  }

  private JLabel AmountText;
  private JButton ButtonAdd;
  private JButton ButtonExit;
  private JButton buttonDelete;
  private JLabel Category;
  private JLabel CategoryText;
  private JTextPane Describe;
  private JLabel DescribeText;
  private JLabel Gender;
  private JLabel GenderText;
  private JLabel Id;
  private JPanel ImagePanel;
  private JTextField Name;
  private JLabel OriPrice;
  private JLabel Price;
  private JLabel Rating;
  private JLabel Remaining;
  private JScrollPane Scroll;
  private JPanel SizePanel;
}

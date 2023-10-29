package com.clothingstore.gui.components;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import org.netbeans.lib.awtextra.*;

import com.clothingstore.bus.CategoryBUS;
import com.clothingstore.bus.ImportItemsBUS;
import com.clothingstore.bus.SizeBUS;
import com.clothingstore.bus.SizeItemBUS;
import com.clothingstore.models.ImportItemsModel;
import com.clothingstore.models.ProductModel;
import com.clothingstore.models.SizeItemModel;
import com.clothingstore.models.SizeModel;

public class ProductDetail extends JFrame {

    Color color = new Color(230, 240, 255);

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

    private void initComponents(ProductModel productModel) {

        ImagePanel = new JPanel();
        Image = new JLabel();
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
        Control = new JPanel();
        SizePanel = new JPanel();
        ButtonExit = new JButton();
        ButtonAdd = new JButton();
        Remaining = new JLabel();

        ImagePanel.setLayout(new GridBagLayout());

        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/config/image/polo3.png"));
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(180, 180, java.awt.Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        Image.setIcon(scaledIcon);
        ImagePanel.add(Image, new GridBagConstraints());
        ImagePanel.setBackground(color);
        getContentPane().add(ImagePanel, new AbsoluteConstraints(10, 20, 190, 270));

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
        Id.setText("0003");
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

        Describe.setFont(new Font("Segoe UI", 0, 14));
        Describe.setText("Trong ví dụ này, chúng ta sử dụng GridBagLayout để quản lý việc");
        Scroll.setViewportView(Describe);

        getContentPane().add(Scroll, new AbsoluteConstraints(320, 160, 230, 70));

        DescribeText.setFont(new Font("Segoe UI", 2, 15));
        DescribeText.setForeground(new Color(102, 102, 102));
        DescribeText.setHorizontalAlignment(SwingConstants.RIGHT);
        DescribeText.setText("Describe:");
        getContentPane().add(DescribeText, new AbsoluteConstraints(240, 160, 60, 20));

        Control.setBackground(new Color(255, 204, 204));
        Control.setLayout(new BorderLayout());
        Control control = new Control();
        Control.add(control);
        getContentPane().add(Control, new AbsoluteConstraints(320, 240, 60, 20));

        SizePanel.setBackground(new Color(204, 255, 204));
        SizePanel.setLayout(new java.awt.GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 10);
        List<SizeItemModel> sizeList = SizeItemBUS.getInstance().getAllModels();
        List<SizeModel> sizeName = SizeBUS.getInstance().getAllModels();
        
        //TODO: Fix hiển thị Size, hoàn thành nút Add to cart, hiển thị số lượng sản phẩm còn lại theo size dựa theo bản import_items
        Map<Integer, SizeModel> sizeMap = new HashMap<>();
        for (SizeModel sizes : sizeName) {
            sizeMap.put(sizes.getId(), sizes);
        }

        for (SizeItemModel size : sizeList) {
            SizeModel sizes = sizeMap.get(size.getId());
            if (sizes != null) {
                JButton button = new JButton(" " + sizes.getSize());
                SizePanel.add(button, gbc);
            }
        }

        getContentPane().add(SizePanel, new AbsoluteConstraints(250, 270, 190, 20));

        ButtonExit.setText("Exit");
        ButtonExit.setPreferredSize(new Dimension(72, 28));
        ButtonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        getContentPane().add(ButtonExit, new AbsoluteConstraints(200, 320, -1, -1));

        ButtonAdd.setText("Add To Cart");
        ButtonAdd.setPreferredSize(new Dimension(94, 28));

        getContentPane().add(ButtonAdd, new AbsoluteConstraints(380, 320, -1, -1));
        ImportItemsModel importItemsModels = ImportItemsBUS.getInstance().getModelById(productModel.getId());
        Remaining.setFont(new Font("Segoe UI", 0, 15));
        Remaining.setText(importItemsModels.getQuantity() + "  sản phẩm có sẵn");
        Remaining.setForeground(new Color(102, 102, 102));
        getContentPane().add(Remaining, new AbsoluteConstraints(440, 240, 170, 17));

        pack();
    }

    private JLabel AmountText;
    private JButton ButtonAdd;
    private JButton ButtonExit;
    private JLabel Category;
    private JLabel CategoryText;
    private JPanel Control;
    private JTextPane Describe;
    private JLabel DescribeText;
    private JLabel Gender;
    private JLabel GenderText;
    private JLabel Id;
    private JLabel Image;
    private JPanel ImagePanel;
    private JTextField Name;
    private JLabel OriPrice;
    private JLabel Price;
    private JLabel Rating;
    private JLabel Remaining;
    private JScrollPane Scroll;
    private JPanel SizePanel;
}

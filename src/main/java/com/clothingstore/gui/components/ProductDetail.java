package com.clothingstore.gui.components;

import java.awt.*;
import javax.swing.*;

import org.netbeans.lib.awtextra.*;

public class ProductDetail extends JFrame {

    Color color = new Color(230, 240, 255);

    public ProductDetail() {
        setAlwaysOnTop(true);
        setSize(new Dimension(685, 390));
        setPreferredSize(new Dimension(685, 390));
        setResizable(false);
        getContentPane().setLayout(new AbsoluteLayout());
        getContentPane().setBackground(color);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {

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
        Name.setText("Black Polo");
        Name.setAutoscrolls(false);
        
        getContentPane().add(Name, new AbsoluteConstraints(240, 20, 260, 40));

        Rating.setFont(new Font("Segoe UI", 0, 14)); 
        Rating.setForeground(new Color(102, 102, 255));
        Rating.setText("Rating: 4.5");
        getContentPane().add(Rating, new AbsoluteConstraints(240, 60, 70, 20));

        OriPrice.setFont(new Font("Segoe UI", 0, 17)); 
        OriPrice.setForeground(new Color(153, 153, 153));
        OriPrice.setText("<html><s>450.000</s></html>");
        getContentPane().add(OriPrice, new AbsoluteConstraints(240, 80, 70, 30));

        Price.setFont(new Font("Segoe UI", 0, 21)); 
        Price.setForeground(new Color(255, 51, 51));
        Price.setText("350.000 đ");
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
        CategoryText.setText("Category: ");
        getContentPane().add(CategoryText, new AbsoluteConstraints(240, 110, 70, -1));

        Gender.setFont(new Font("Segoe UI", 0, 16)); 
        Gender.setText("Men");
        getContentPane().add(Gender, new AbsoluteConstraints(320, 130, 130, 30));

        Category.setFont(new Font("Segoe UI", 0, 16)); 
        Category.setForeground(new Color(0, 51, 51));
        Category.setText("Polo");
        getContentPane().add(Category, new AbsoluteConstraints(320, 110, 130, 20));

        GenderText.setFont(new Font("Segoe UI", 2, 15)); 
        GenderText.setForeground(new Color(102, 102, 102));
        GenderText.setHorizontalAlignment(SwingConstants.RIGHT);
        GenderText.setText("Gender:");
        getContentPane().add(GenderText, new AbsoluteConstraints(240, 130, 60, 30));

        Describe.setFont(new Font("Segoe UI", 0, 14)); 
        Describe.setText("Trong ví dụ này, chúng ta sử dụng GridBagLayout để quản lý việc  ");
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

        for(int i = 0; i<3; i++){
            JButton button = new JButton("M");
            SizePanel.add(button, gbc);
        }

        getContentPane().add(SizePanel, new AbsoluteConstraints(250, 270, 190, 20));

        ButtonExit.setText("Exit");
        ButtonExit.setPreferredSize(new Dimension(72, 28));

        getContentPane().add(ButtonExit, new AbsoluteConstraints(200, 320, -1, -1));

        ButtonAdd.setText("Add To Cart");
        ButtonAdd.setPreferredSize(new Dimension(94, 28));

        getContentPane().add(ButtonAdd, new AbsoluteConstraints(380, 320, -1, -1));

        Remaining.setFont(new Font("Segoe UI", 0, 15)); 
        Remaining.setText("45 sản phẩm có sẵn");
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

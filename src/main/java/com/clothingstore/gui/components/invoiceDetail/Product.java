package com.clothingstore.gui.components.invoiceDetail;

import javax.swing.*;
import org.netbeans.lib.awtextra.*;

import com.clothingstore.bus.ProductBUS;
import com.clothingstore.models.ImportItemsModel;
import com.clothingstore.models.ProductModel;

import java.awt.*;
import java.text.DecimalFormat;

public class Product extends JPanel {
    private static ProductModel productModel;
    DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    public Product(ImportItemsModel importItemsModel) {
        productModel = ProductBUS.getInstance().getModelById(importItemsModel.getProduct_id());
        initComponents(importItemsModel);
    }

    public void initComponents(ImportItemsModel importItemsModel) {
        Header = new JPanel();
        Sr = new JLabel();
        Name = new JLabel();
        Panel1 = new JPanel();
        QuantityText = new JLabel();
        Panel2 = new JPanel();
        SizeText = new JLabel();
        Panel3 = new JPanel();
        PriceText = new JLabel();
        Panel4 = new JPanel();
        AmountText = new JLabel();
        Detail = new JPanel();

        setLayout(new BorderLayout(5, 5));
        setBackground(Color.WHITE);

        Header.setLayout(new AbsoluteLayout());
        Header.setBackground(Color.WHITE);

        Name.setText(productModel.getName());
        Name.setForeground(new Color(26, 101, 101));
        Name.setFont(new Font("Segoe UI", 3, 15));

        Sr.setText("01");
        Sr.setFont(new Font("Segoe UI", 0, 14));

        Header.add(Sr, new AbsoluteConstraints(20, 10, 350, 20));
        Header.add(Name, new AbsoluteConstraints(50, 10, 450, 20));

        add(Header, BorderLayout.NORTH);

        Panel1.setRequestFocusEnabled(false);
        Panel1.setBackground(Color.WHITE);
        Panel1.setLayout(new BorderLayout());

        QuantityText.setFont(new Font("Segoe UI", 3, 13));
        QuantityText.setHorizontalAlignment(SwingConstants.CENTER);
        QuantityText.setText("" + importItemsModel.getQuantity());
        Panel1.add(QuantityText, BorderLayout.CENTER);

        Detail.add(Panel1);

        Panel2.setRequestFocusEnabled(false);
        Panel2.setLayout(new BorderLayout());
        Panel2.setBackground(Color.WHITE);

        SizeText.setFont(new Font("Segoe UI", 3, 13));
        SizeText.setHorizontalAlignment(SwingConstants.CENTER);
        SizeText.setText("" + importItemsModel.getSize_id());
        Panel2.add(SizeText, BorderLayout.CENTER);

        Detail.add(Panel2);

        Panel3.setRequestFocusEnabled(false);
        Panel3.setLayout(new BorderLayout());
        Panel3.setBackground(Color.WHITE);

        PriceText.setFont(new Font("Segoe UI", 3, 13));
        PriceText.setHorizontalAlignment(SwingConstants.CENTER);
        PriceText.setText("" + importItemsModel.getPrice());
        Panel3.add(PriceText, BorderLayout.CENTER);

        Detail.add(Panel3);

        Panel4.setRequestFocusEnabled(false);
        Panel4.setLayout(new BorderLayout());
        Panel4.setBackground(Color.WHITE);

        AmountText.setFont(new Font("Segoe UI", 3, 13));
        AmountText.setHorizontalAlignment(SwingConstants.CENTER);
        AmountText.setText("" + decimalFormat.format(importItemsModel.getPrice() * importItemsModel.getQuantity()));
        Panel4.add(AmountText, BorderLayout.CENTER);

        Detail.add(Panel4);

        Detail.setLayout(new GridLayout(1, 0, 5, 0));
        Detail.setBackground(Color.WHITE);
        add(Detail, BorderLayout.CENTER);
    }

    private JPanel Header;
    private JLabel Sr;
    private JLabel Name;
    private JLabel AmountText;
    private JPanel Panel1;
    private JPanel Panel2;
    private JPanel Panel3;
    private JPanel Panel4;
    private JLabel PriceText;
    private JLabel QuantityText;
    private JLabel SizeText;
    private JPanel Detail;
}

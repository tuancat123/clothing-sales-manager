package com.clothingstore.gui.components.importInvoice;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.netbeans.lib.awtextra.*;

import com.clothingstore.models.ImportItemsModel;
import com.clothingstore.models.ProductModel;


import java.awt.*;
import java.text.DecimalFormat;

public class Product extends JPanel {

  DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
  public Product(ImportItemsModel importItemsModel, int i) {
    initComponents(importItemsModel, i);
  }

  public void initComponents(ImportItemsModel importItemsModel, int i) {
    Header = new JPanel();
    Sr = new JLabel();
    Name = new JLabel();
    Panel1 = new JPanel();
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

    Name.setText("ok");
    Name.setForeground(new Color(26, 101, 101));
    Name.setFont(new Font("Segoe UI", 3, 15));

    Sr.setText(""+i);
    Sr.setFont(new Font("Segoe UI", 0, 14));

    Header.add(Sr, new AbsoluteConstraints(20, 10, 350, 20));
    Header.add(Name, new AbsoluteConstraints(50, 10, 450, 20));

    add(Header, BorderLayout.NORTH);

    Panel1.setRequestFocusEnabled(false);
    Panel1.setBackground(Color.WHITE);
    Panel1.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
    Panel1.setLayout(new BorderLayout());

    JSpinner spinner = new JSpinner(new SpinnerNumberModel(importItemsModel.getQuantity(), 1, null, 1));
    spinner.addChangeListener(new ChangeListener() {

        @Override
        public void stateChanged(ChangeEvent e) {
            importItemsModel.setQuantity(Integer.valueOf(spinner.getValue().toString()));
            repaint();
            revalidate();
        }
        
    });
    spinner.setPreferredSize(new Dimension(60,30));
    spinner.setBackground(new Color(255, 204, 204));
    Panel1.add(spinner, BorderLayout.WEST);

    Detail.add(Panel1);

    Panel2.setRequestFocusEnabled(false);
    Panel2.setLayout(new BorderLayout());
    Panel2.setBackground(Color.WHITE);

    SizeText.setFont(new Font("Segoe UI", 3, 13));
    SizeText.setHorizontalAlignment(SwingConstants.CENTER);
    SizeText.setText("S");
    Panel2.add(SizeText, BorderLayout.CENTER);

    Detail.add(Panel2);

    Panel3.setRequestFocusEnabled(false);
    Panel3.setLayout(new BorderLayout());
    Panel3.setBackground(Color.WHITE);

    PriceText.setFont(new Font("Segoe UI", 3, 13));
    PriceText.setHorizontalAlignment(SwingConstants.CENTER);
    PriceText.setText("4444");
    Panel3.add(PriceText, BorderLayout.CENTER);

    Detail.add(Panel3);

    Panel4.setRequestFocusEnabled(false);
    Panel4.setLayout(new BorderLayout());
    Panel4.setBackground(Color.WHITE);

    AmountText.setFont(new Font("Segoe UI", 3, 13));
    AmountText.setHorizontalAlignment(SwingConstants.CENTER);
    AmountText.setText("4444");
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
  private JLabel SizeText;
  private JPanel Detail;
}

package com.clothingstore.gui.components.invoicesHistory;

import javax.swing.*;
import javax.swing.border.Border;

import org.apache.poi.sl.usermodel.TableCell.BorderEdge;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import com.clothingstore.gui.models.NavData;

import java.awt.*;
import java.util.ArrayList;
import java.util.jar.Attributes.Name;


public class InvoiceDetail extends JPanel {

    private String name;
    private String value;

    private static InvoiceDetail instance;

    public static InvoiceDetail getInstance() {
        if (instance == null) {
            instance = new InvoiceDetail();
        }
        return instance;
    }

    public InvoiceDetail(){
        initComponents();
    }
    
    public InvoiceDetail(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static ArrayList<InvoiceDetail> getData() {
        ArrayList<InvoiceDetail> data = new ArrayList<InvoiceDetail>() {{
            add(new InvoiceDetail("Id Invoice", "0936622"));
            add(new InvoiceDetail("Customer name", "Huỳnh Ngọc Triều"));
            add(new InvoiceDetail("Date", "23/8/2023"));
            add(new InvoiceDetail("Total", "300.450.444"));
            add(new InvoiceDetail("Paying", "Cash"));
            add(new InvoiceDetail("Name Employee", "Bánh Văn A"));
            add(new InvoiceDetail("Phone Employee", "09366252"));
            add(new InvoiceDetail("Products", "6"));
        }};
        return data;
    }

    public void initComponents(){
        NamePanel = new JLabel();
        Info = new JPanel();
        Products = new JPanel();
        Scroll = new JScrollPane();

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        setLayout(new BorderLayout());

        Scroll.setViewportView(mainPanel);

        Info.setLayout(new GridLayout(0, 1));
        Info.setBackground(Color.WHITE);

        NamePanel.setText("-- History --");
        NamePanel.setHorizontalAlignment(SwingConstants.CENTER);
        NamePanel.setVerticalAlignment(SwingConstants.CENTER);
        NamePanel.setFont(new Font("Segoe UI", 1, 17));

        System.out.println();
        Info.add(NamePanel);

        for(InvoiceDetail invoiceDetail : getData()){
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

        Products.setLayout(new java.awt.GridBagLayout());
        Products.setPreferredSize(new Dimension(150, 150));

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
}

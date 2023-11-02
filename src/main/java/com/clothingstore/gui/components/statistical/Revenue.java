package com.clothingstore.gui.components.statistical;

import java.awt.*;

import javax.swing.*;

import com.clothingstore.gui.admin.dashboard.Card;
import com.clothingstore.gui.components.customerList.Invoice;

public class Revenue extends JPanel {
    private static Revenue instance;

    public static Revenue getInstance() {
        if (instance == null) {
            instance = new Revenue();
        }
        return instance;
    }

    public Revenue(){
        initComponents();
    }

    private void initComponents(){
        NamePanel = new JLabel("Revenue Statistics");
        ChartPanel = new JPanel();
        Scroll = new JScrollPane();
        CardPanel = new JPanel();
        Invoice = new JPanel();
        setLayout(new BorderLayout());
        setBackground(Color.gray);

        ChartPanel.setPreferredSize(new Dimension(620,400));
        ChartPanel.setLayout(new BorderLayout());
        ChartPanel.setBorder(BorderFactory.createEmptyBorder(0,0,1,15));
        Chart chart = new Chart();
        ChartPanel.add(chart, BorderLayout.CENTER);

        CardPanel.setLayout(new GridLayout(1,4,25,25));
        CardPanel.setBorder(BorderFactory.createEmptyBorder(15,5,15,5));
        CardPanel.setPreferredSize(new Dimension(90,150));

        Card card1 = new Card("cart.png", "Total Order","34", new Color(0, 230, 230), 30);
        CardPanel.add(card1);

        Card card2 = new Card("coin.png", "Total Revenue","340.000"+"$", new Color(255, 77, 77), 34);
        CardPanel.add(card2);

        Card card3 = new Card("clothing.png", "Products Sold","33", new Color(255, 128, 0), 33);
        CardPanel.add(card3);

        Card card4 = new Card("coin.png", "Points Earned","3000", new Color(153, 51, 255), 77);
        CardPanel.add(card4);

        Invoice.setLayout(new GridLayout(0,1));
        Invoice invoiceHeader = new Invoice("Id", "Customer Id", "Quantity", "Total");
        Invoice.add(invoiceHeader);

        for(int i = 0; i< 10; i++){
            Invoice invoice = new Invoice("001","002","5","3000.000");
            invoice.setPreferredSize(new Dimension(35,35));
            Invoice.add(invoice);
        }
        Scroll.setViewportView(Invoice);

        NamePanel.setFont(new Font("Segoe UI", 1, 17));
        ChartPanel.add(NamePanel, BorderLayout.NORTH);

        add(CardPanel, BorderLayout.NORTH);
        add(ChartPanel, BorderLayout.WEST);
        add(Scroll, BorderLayout.CENTER);
        
    }
    private JLabel NamePanel;
    private JPanel CardPanel;
    private JPanel ChartPanel;
    private JScrollPane Scroll;
    private JPanel Invoice;
}

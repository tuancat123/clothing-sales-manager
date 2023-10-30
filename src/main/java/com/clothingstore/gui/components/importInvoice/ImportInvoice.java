package com.clothingstore.gui.components.importInvoice;

import java.awt.*;
import javax.swing.*;

public class ImportInvoice extends JPanel {

    
    public ImportInvoice() {
        initComponents();
    }
    private void initComponents() {

        Icon = new JLabel();
        Detail = new JPanel();
        Panel = new JPanel();
        Price = new JLabel();
        IdInvoice = new JLabel();
        Time = new JPanel();
        Date = new JLabel();
        TimeHour = new JLabel();

        Color color = new Color(204, 224, 255);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(40, 60));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        setBackground(color);

        Icon.setIcon(new ImageIcon(getClass().getResource("/config/icon/coin.png")));
        Icon.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 0));
        add(Icon, BorderLayout.LINE_START);

        Detail.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5));
        Detail.setBackground(color);
        Detail.setLayout(new BorderLayout());

        Panel.setLayout(new GridLayout(2, 0));
        Panel.setBackground(color);

        Price.setHorizontalAlignment(SwingConstants.CENTER);
        Price.setText("4.000.000 Đ");
        Price.setFont(new Font("Segoe UI", 0, 14));
        Price.setForeground(Color.RED);
        Panel.add(Price);

        IdInvoice.setHorizontalAlignment(SwingConstants.CENTER);
        IdInvoice.setText("64747374");
        IdInvoice.setForeground(new Color(0, 128, 0));
        Panel.add(IdInvoice);

        Detail.add(Panel, BorderLayout.LINE_START);

        add(Detail, BorderLayout.CENTER);

        Time.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        Time.setLayout(new GridLayout(2, 1));
        Time.setBackground(color);

        Date.setText("05/10/2022");
        Date.setFont(new Font("Segoe UI", 0, 13));
        Date.setForeground(new Color(102, 0, 51));
        Time.add(Date);

        TimeHour.setHorizontalAlignment(SwingConstants.CENTER);
        TimeHour.setText("16h30");
        TimeHour.setForeground(new Color(0, 0, 102));
        TimeHour.setFont(new Font("Segoe UI", 0, 12));
        Time.add(TimeHour);

        add(Time, BorderLayout.EAST);
    }


    private JLabel Date;
    private JPanel Detail;
    private JLabel Icon;
    private JLabel IdInvoice;
    private JPanel Panel;
    private JLabel Price;
    private JPanel Time;
    private JLabel TimeHour;
}

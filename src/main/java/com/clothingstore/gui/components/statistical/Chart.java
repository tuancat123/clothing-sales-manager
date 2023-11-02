package com.clothingstore.gui.components.statistical;

import javax.swing.*;
import java.awt.*;
import java.time.*;

public class Chart extends JPanel {
    private int[] monthlyRevenue = {10000000, 12000000, 15000000, 12000000, 20000000, 22000000, 25000000};
    private int currentMonth = 0;

    private JPanel Panel;

    public Chart() {
        for(int i = 0; i<6; i++){
                currentMonth++;
                repaint();
        }
        initComponents();
    }

    public void initComponents(){
        Panel = new JPanel();
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        Panel.setLayout(new GridLayout(1,7));
        Panel.setPreferredSize(new Dimension(50,50));
        
        LocalDate today = LocalDate.now();
        LocalDate lastDayOfWeek = today.plusDays(1);

        for(int i = 2; i<9;i++){
            JLabel label = new JLabel(lastDayOfWeek.getDayOfWeek().toString());
            label.setFont(new Font("Segoe UI", 1, 12));
            label.setForeground(Color.DARK_GRAY);
            if(i==8)
                label.setForeground(new Color(255, 51, 0));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            lastDayOfWeek = lastDayOfWeek.plusDays(1);
            Panel.add(label);
        }
        add(Panel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        int barWidth = width / monthlyRevenue.length - 50; // trừ đi chiều dài panel
        int maxRevenue = getMaxRevenue();

        for (int i = 0; i <= currentMonth; i++) {
            int barHeight = (int) ((double) monthlyRevenue[i] / maxRevenue * (height - 50));
            int x = i * (barWidth + 50) + 25; // 50 khoảng cách giữa các cột 25 là padding trái
            int y = height - barHeight - 30;
            if(i==6)
                g.setColor(new Color(179, 36, 0));
            else
                g.setColor(new Color(51, 102, 153));

            g.fillRect(x, y, barWidth, barHeight);

            g.setColor(Color.black);
            g.drawRect(x, y, barWidth, barHeight);

            String valueText = String.valueOf(monthlyRevenue[i]);
            int textWidth = g.getFontMetrics().stringWidth(valueText);
            g.drawString(valueText, x + barWidth / 2 - textWidth / 2, y - 5);
        }
    }

    private int getMaxRevenue() {
        int max = Integer.MIN_VALUE;
        for (int revenue : monthlyRevenue) {
            if (revenue > max) {
                max = revenue;
            }
        }
        return max;
    }
}

package com.clothingstore;

import javax.swing.*;
import com.clothingstore.gui.customer.HomePage;

public class Application {
  public static void main(String[] args) {
        // Đặt Look and Feel
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Tạo và hiển thị giao diện người dùng
        SwingUtilities.invokeLater(() -> {
            HomePage homePage = HomePage.getInstance();
            homePage.setVisible(true);
        });
    }
}

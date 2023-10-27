package com.clothingstore;

import javax.swing.*;

import com.clothingstore.gui.admin.Dashboard.Dashboard;
import com.clothingstore.gui.customer.HomePage;
import com.clothingstore.gui.login.Login;

public class Application {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            HomePage homePage = HomePage.getInstance();
            homePage.setVisible(true);
            // Dashboard dashboard = Dashboard.getInstance();
            // dashboard.setVisible(true);
            // Login login = Login.getInstance();
            // login.setVisible(true);
        });
    }
}

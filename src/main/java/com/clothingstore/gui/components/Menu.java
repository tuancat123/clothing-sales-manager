package com.clothingstore.gui.components;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import com.clothingstore.gui.employee.HomePage;
import com.clothingstore.gui.models.MenuData;
import com.clothingstore.gui.models.MenuItemData;

public class Menu extends JPanel {
    private static Menu instance;

    JPopupMenu popupMenu;
    JMenuItem menuItem;

    public static Menu getInstance(ArrayList<MenuData> data) {
        if (instance == null) {
            instance = new Menu(data);
        }
        return instance;
    }

    public Menu(ArrayList<MenuData> data) {
        initComponents(data);
    }

    public void initComponents(ArrayList<MenuData> data) {
        setLayout(new GridLayout(10, 1, 10, 5));
        setPreferredSize(new Dimension(0,150));
        setBackground(new Color(0, 26, 51));
        for (MenuData menuData : data) {

            ArrayList<MenuItemData> dataMenuItem = menuData.getItemData();

            JButton menuButton = new JButton(menuData.getName());
            menuButton.addActionListener(menuData.getActionListener());

            menuButton.setPreferredSize(new Dimension(50, 50));
            menuButton.setBackground(new Color(153, 153, 255));
            menuButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            menuButton.setBorderPainted(false);
            menuButton.setOpaque(true);

            JPopupMenu popupMenu = new JPopupMenu();

            if (dataMenuItem != null) {
                for (MenuItemData option : dataMenuItem) {
                    JMenuItem menuItem = new JMenuItem(option.getName());
                    menuItem.setPreferredSize(new Dimension(170, 45));
                    menuItem.setBackground(new Color(204, 204, 255));
                    menuItem.setHorizontalAlignment(SwingConstants.CENTER);
                    menuItem.setContentAreaFilled(false);
                    menuItem.setOpaque(true);

                    menuItem.addActionListener(option.getActionListener());
                    popupMenu.add(new JSeparator());
                    popupMenu.add(menuItem);
                }
            }

            menuButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    popupMenu.show(menuButton, menuButton.getWidth(), 0);
                }
            });
            add(menuButton);
        }
    }
}

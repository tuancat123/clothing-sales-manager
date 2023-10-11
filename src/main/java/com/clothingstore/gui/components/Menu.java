package com.clothingstore.gui.components;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import com.clothingstore.gui.models.MenuData;
import com.clothingstore.gui.models.MenuItemData;


public class Menu extends JPanel {
    private static Menu instance;

    JPopupMenu popupMenu;
    JMenuItem menuItem;

    ArrayList<MenuData> dataMenu = MenuData.getData();

    private boolean status = false;
    private int currentWidth;
    private int currentHeight;
    private Timer resizeTimer;



    public static Menu getInstance() {
        if (instance == null) {
          instance = new Menu();
        }
        return instance;
    }

    public Menu() {

        resizeTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!status){
                    if (currentWidth < 150 ) {
                        currentWidth += 35; 
                        setPreferredSize(new Dimension(currentWidth, currentHeight));
                        revalidate();
                        repaint();
                    } else {
                        status=true;
                        resizeTimer.stop();
                    }
                }
                else{
                    if (currentWidth > 0 ) {
                        currentWidth -= 35; 
                        setPreferredSize(new Dimension(currentWidth, currentHeight));
                        revalidate();
                        repaint();
                    } else {
                        status=false;
                        resizeTimer.stop();
                    }
                }
            }
        });

        initComponents();
    }

    public void reSize() {
        currentWidth = getPreferredSize().width;
        currentHeight = getPreferredSize().height;
        resizeTimer.start();
    }
    

    public void initComponents() {
        setPreferredSize(new Dimension(0, 0));
        setLayout(new GridLayout(10, 1));

        for(MenuData menuData: dataMenu){

            ArrayList<MenuItemData> dataMenuItem = menuData.getItemData();

            JButton menuButton = new JButton(menuData.getName());
            menuButton.setPreferredSize(new Dimension(150, 50));

            JPopupMenu popupMenu = new JPopupMenu();

            if(dataMenuItem != null){
                for(MenuItemData option: dataMenuItem){
                    JMenuItem menuItem = new JMenuItem(option.getName());
                    menuItem.setPreferredSize(new Dimension(150, 50));

                    menuItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ActionListener listener = option.getActionListener();
                            listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
                        }
                    });

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

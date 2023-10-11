package com.clothingstore.gui.customer;

import javax.swing.JPopupMenu;

import com.clothingstore.gui.components.Menu;
import com.clothingstore.gui.components.Products;

public class PopupMenu extends JPopupMenu {
    private static PopupMenu instance;

    public static PopupMenu getInstance() {
        if (instance == null) {
          instance = new PopupMenu();
        }
        return instance;
    }
    public PopupMenu(){
        add(Menu.getInstance());
        show(Products.getInstance(), 0, 0);
        setInvoker(null);
        setVisible(false);
    }
}

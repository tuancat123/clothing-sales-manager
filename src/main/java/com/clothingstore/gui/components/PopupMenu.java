package com.clothingstore.gui.components;

import javax.swing.JPopupMenu;

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

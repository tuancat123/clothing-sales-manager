package com.clothingstore.gui.models;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuItemData {
    private String name;
    private ActionListener actionListener;

    public MenuItemData(String name, ActionListener actionListener) {
        this.name = name;
        this.actionListener = actionListener;
    }

    public String getName() {
        return name;
    }

    public ActionListener getActionListener() {
        return actionListener;
    }

}

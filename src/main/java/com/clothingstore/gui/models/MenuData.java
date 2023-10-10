package com.clothingstore.gui.models;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuData {
    private String name;
    private ActionListener actionListener;
    private ArrayList<MenuItemData> menuItemData; 

    public MenuData(String name, ArrayList<MenuItemData> menuItemData, ActionListener actionListener) {
        this.name = name;
        this.actionListener = actionListener;
        this.menuItemData = menuItemData;
    }

    public String getName() {
        return name;
    }

    public ActionListener getActionListener() {
        return actionListener;
    }

    public ArrayList<MenuItemData> getItemData() { 
        return menuItemData;
    }

    public static ArrayList<MenuData> getData() {
        ArrayList<MenuData> data = new ArrayList<>();

        data.add(new MenuData("Products", null, MenuAction())); 
        data.add(new MenuData(
            "History", 
            new ArrayList<MenuItemData>() {{
                add(new MenuItemData("Option1", MenuAction()));
                add(new MenuItemData("Option2", null));

            }},
            null )); 

        return data;
    }

    private static ActionListener MenuAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Menu clicked");
            }
        };
    }
}

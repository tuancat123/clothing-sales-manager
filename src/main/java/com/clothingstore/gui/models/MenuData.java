package com.clothingstore.gui.models;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.clothingstore.gui.components.Products;
import com.clothingstore.gui.components.invoicesHistory.HistoryList;
import com.clothingstore.gui.components.invoicesHistory.InvoiceHistory;
import com.clothingstore.gui.customer.HomePage;
import com.clothingstore.gui.customer.Invoice;

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

        data.add(new MenuData("Products", null, ProductsAction()));
        data.add(new MenuData("History", null, HistoryAction()));
        data.add(new MenuData(
                "Activity",
                new ArrayList<MenuItemData>() {
                    {
                        add(new MenuItemData("Option1", null));
                        add(new MenuItemData("Option2", null));

                    }
                },
                null));

        return data;
    }

    private static ActionListener ProductsAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomePage.getInstance().Remove();
                HomePage.getInstance().Add(Products.getInstance(), Invoice.getInstance());
            }
        };
    }

    private static ActionListener HistoryAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HistoryList.getInstance().setVisible(true);
                HomePage.getInstance().Remove();
                HomePage.getInstance().Add(InvoiceHistory.getInstance());
            }
        };
    }
}

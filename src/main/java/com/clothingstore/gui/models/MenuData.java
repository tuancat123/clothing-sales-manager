package com.clothingstore.gui.models;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.clothingstore.gui.components.Products;
import com.clothingstore.gui.components.invoicesHistory.HistoryList;
import com.clothingstore.gui.components.invoicesHistory.InvoiceHistory;
import com.clothingstore.gui.employee.HomePage;
import com.clothingstore.gui.employee.Invoice;
import com.clothingstore.models.UserModel;

import services.Authentication;

public class MenuData {
    private String name;
    private ActionListener actionListener;
    private ArrayList<MenuItemData> menuItemData;
    public Authentication authentication;
    static UserModel currentUser = Authentication.getCurrentUser();

    public MenuData() {

    }

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

    public static ArrayList<MenuData> getDataMenu() {
        ArrayList<MenuData> data;
        switch (currentUser.getRoleId()) {
            case 1:
                data = MenuData.getDataAdmin();
                break;
            case 2:
                data = MenuData.getDataManager();
                break;
            case 3:
                data = MenuData.getDataEmployee();
                break;
            default:
                throw new IllegalArgumentException("User role is not supported");
        }
        return data;
    }

    public static ArrayList<MenuData> getDataEmployee() {
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

    public static ArrayList<MenuData> getDataAdmin() {
        ArrayList<MenuData> data = new ArrayList<>();

        data.add(new MenuData("Thống kê", null, null));
        data.add(new MenuData("Products", null, ProductsAction()));
        data.add(new MenuData("History", null, HistoryAction()));
        data.add(new MenuData("Employees", null, null));
        data.add(new MenuData("Customers", null, null));
        data.add(new MenuData("Invoices", null, null));
        data.add(new MenuData("Account", null, null));
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

    public static ArrayList<MenuData> getDataManager() {
        ArrayList<MenuData> data = new ArrayList<>();

        data.add(new MenuData("Products", null, ProductsAction()));
        data.add(new MenuData("History", null, HistoryAction()));
        data.add(new MenuData("Customers", null, null));
        data.add(new MenuData("Invoices", null, null));
        data.add(new MenuData("Account", null, null));

        return data;
    }

    private static ActionListener ProductsAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomePage.getInstance().Remove();
                ;
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

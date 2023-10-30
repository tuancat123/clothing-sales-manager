package com.clothingstore.gui.models;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.clothingstore.gui.components.Products;
import com.clothingstore.gui.components.customerList.Customers;
import com.clothingstore.gui.components.importInvoice.ImportHistory;
import com.clothingstore.gui.components.invoicesHistory.HistoryList;
import com.clothingstore.gui.components.invoicesHistory.InvoiceHistory;
import com.clothingstore.gui.employee.HomePage;
import com.clothingstore.gui.employee.Invoice;
import com.clothingstore.gui.login.Login;
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
        data.add(new MenuData("Invoice history ", null, HistoryAction()));
        data.add(new MenuData(
                "Customers",
                new ArrayList<MenuItemData>() {
                    {
                        add(new MenuItemData("Customers list", null));
                        add(new MenuItemData("Add Customers", null));

                    }
                },
                null));
        data.add(new MenuData("Logout", null, LogoutAction()));

        return data;
    }

    public static ArrayList<MenuData> getDataAdmin() {
        ArrayList<MenuData> data = new ArrayList<>();

        data.add(new MenuData("Products", null, ProductsAction()));
        data.add(new MenuData("Invoice history ", null, HistoryAction()));
        // data.add(new MenuData(
        // "Sales Manager",
        // new ArrayList<MenuItemData>() {
        // {
        // add(new MenuItemData("Add Invoice", null));
        // add(new MenuItemData("Invoice History", HistoryAction()));

        // }
        // },
        // null));
        data.add(new MenuData(
                "Inventory Management",
                new ArrayList<MenuItemData>() {
                    {
                        add(new MenuItemData("Add Invoice", null));
                        add(new MenuItemData("Import Management", ImportAction()));

                    }
                },
                null));
        data.add(new MenuData(
                "Employees Management",
                new ArrayList<MenuItemData>() {
                    {
                        add(new MenuItemData("Employeees List", null));
                        add(new MenuItemData("Add Employee", null));

                    }
                },
                null));
        data.add(new MenuData(
                "Customers Management",
                new ArrayList<MenuItemData>() {
                    {
                        add(new MenuItemData("Customers List", CustomerAction()));
                        add(new MenuItemData("Add Customers", null));

                    }
                },
                null));
        data.add(new MenuData(
                "statistical",
                new ArrayList<MenuItemData>() {
                    {
                        add(new MenuItemData("revenue statistics", null));
                        add(new MenuItemData("operating costs", null));
                        add(new MenuItemData("profit statistics", null));

                    }
                },
                null));
        data.add(new MenuData("Role management", null, null));
        data.add(new MenuData("Logout", null, LogoutAction()));
        return data;
    }

    public static ArrayList<MenuData> getDataManager() {
        ArrayList<MenuData> data = new ArrayList<>();

        data.add(new MenuData("Products", null, ProductsAction()));
        data.add(new MenuData("Invoice history ", null, HistoryAction()));
        data.add(new MenuData(
                "Inventory Management",
                new ArrayList<MenuItemData>() {
                    {
                        add(new MenuItemData("Add Invoice", null));
                        add(new MenuItemData("Import Management", ImportAction()));

                    }
                },
                null));
        data.add(new MenuData(
                "Customers Management",
                new ArrayList<MenuItemData>() {
                    {
                        add(new MenuItemData("Customers List", CustomerAction()));
                        add(new MenuItemData("Add Customers", null));

                    }
                },
                null));
        data.add(new MenuData("Logout", null, LogoutAction()));
        return data;
    }

    private static ActionListener ProductsAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomePage.getInstance().Remove();
                if(currentUser.getRoleId()==3)
                    HomePage.getInstance().Add(Products.getInstance(), Invoice.getInstance());
                else
                    HomePage.getInstance().Add(Products.getInstance());
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

    private static ActionListener ImportAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomePage.getInstance().Remove();
                HomePage.getInstance().Add(ImportHistory.getInstance());
            }
        };
    }
    private static ActionListener CustomerAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomePage.getInstance().Remove();
                HomePage.getInstance().Add(Customers.getInstance());
            }
        };
    }

    private static ActionListener LogoutAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    Authentication.logout();
                    HomePage.getInstance().dispose();
                    HomePage.getInstance().setVisible(false);
                    Login.getInstance().setVisible(true);
                }
            }

        };
    }
}

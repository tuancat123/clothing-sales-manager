package com.clothingstore.gui.models;

import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

import com.clothingstore.gui.components.Menu;
import com.clothingstore.gui.components.Products;

public class NavData {
    private String name;
    private ActionListener actionListener;
    private static boolean isExpanding = true;
    private static int menuWidth;
    private static ArrayList<MenuData> menuData = MenuData.getDataMenu();
    private static Menu menu = Menu.getInstance(menuData);

    public NavData(String name, ActionListener actionListener) {
        this.name = name;
        this.actionListener = actionListener;
    }

    public String getName() {
        return name;
    }

    public ActionListener getActionListener() {
        return actionListener;
    }

    public static ArrayList<NavData> getData() {
        ArrayList<NavData> data = new ArrayList<NavData>() {
            {
                add(new NavData("Menu", MenuAction()));
                add(new NavData("All", null));
                add(new NavData("Shirt", null));
                add(new NavData("Polo", null));
                add(new NavData("Short", null));
                add(new NavData("Jean", null));
                add(new NavData("More", null));

            }
        };
        return data;
    }

    public static ActionListener MenuAction() {
        menuWidth = menu.getWidth();
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Timer timer;
                if (menuWidth > 0 && menuWidth < 200) {
                    return;
                } else {
                    if (isExpanding) {
                        timer = new Timer(10, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (menuWidth <= 200) {
                                    menuWidth += 10;
                                    menu.setPreferredSize(new Dimension(menuWidth, 200));
                                    menu.repaint();
                                    menu.revalidate();
                                } else {
                                    ((Timer) e.getSource()).stop();
                                }
                            }
                        });
                    } else {
                        timer = new Timer(10, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (menuWidth >= 10) {
                                    menuWidth -= 10;
                                    menu.setPreferredSize(new Dimension(menuWidth, 150));
                                    menu.repaint();
                                    menu.revalidate();
                                } else {
                                    ((Timer) e.getSource()).stop();
                                }
                            }
                        });
                    }

                    timer.start();
                    Products.getInstance().MenuOn(isExpanding);
                    isExpanding = !isExpanding;
                }
            }
        };
    }
}

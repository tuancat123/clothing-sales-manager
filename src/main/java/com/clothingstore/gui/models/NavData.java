package com.clothingstore.gui.models;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import org.junit.jupiter.api.Test;

import com.clothingstore.gui.components.Menu;
import com.clothingstore.gui.components.PopupMenu;
import com.clothingstore.gui.components.Products;
import com.clothingstore.gui.customer.HomePage;
import com.clothingstore.gui.customer.Navigation;
import com.itextpdf.awt.geom.Dimension;

public class NavData {
    private String name;
    private ActionListener actionListener;

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
        ArrayList<NavData> data = new ArrayList<NavData>() {{
            add(new NavData("Menu", MenuAction() ));
            add(new NavData("All", null));
            add(new NavData("Shirt", null));
            add(new NavData("Polo", null));
            add(new NavData("Short", null));
            add(new NavData("Jean", null));
            add(new NavData("More", null));

        }};
        return data;
    }
    private static ActionListener MenuAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(PopupMenu.getInstance().isVisible()){
                    HomePage.getInstance().setResizable(true);
                    PopupMenu.getInstance().setVisible(false);
                }
                else{
                    Menu.getInstance().setPreferredSize(new java.awt.Dimension(150, (int) (HomePage.getInstance().getSize().getHeight() - 93)));
                    HomePage.getInstance().setResizable(false);
                    PopupMenu.getInstance().show(Products.getInstance(), 3, 3);
                    PopupMenu.getInstance().setInvoker(null);
                    PopupMenu.getInstance().setVisible(true);
                }
            }
        };
    }
}

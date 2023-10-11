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
import com.clothingstore.gui.components.Products;
import com.clothingstore.gui.customer.HomePage;
import com.clothingstore.gui.customer.Navigation;
import com.clothingstore.gui.customer.PopupMunu;
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
                if(PopupMunu.getInstance().isVisible()){
                    PopupMunu.getInstance().setVisible(false);
                }
                else{
                    PopupMunu.getInstance().setVisible(true);
                }
            }
        };
    }
}

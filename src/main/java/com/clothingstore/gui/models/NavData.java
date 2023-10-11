package com.clothingstore.gui.models;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.clothingstore.gui.components.Menu;

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
                Menu.getInstance().reSize();
            }
        };
    }
}

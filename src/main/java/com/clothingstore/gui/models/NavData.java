package com.clothingstore.gui.models;

import java.awt.event.ActionListener;
import java.util.ArrayList;

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
            add(new NavData("Menu", null));
            add(new NavData("Shirt", null));
            add(new NavData("Polo", null));
            add(new NavData("2", null));
            add(new NavData("3", null));
            add(new NavData("4", null));
            add(new NavData("5", null));

        }};
        return data;
    }
}

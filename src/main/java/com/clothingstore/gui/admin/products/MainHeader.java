package com.clothingstore.gui.admin.products;

import java.awt.*;
import javax.swing.*;

import org.netbeans.lib.awtextra.*;

public class MainHeader extends JPanel {

    public MainHeader() {
        initComponents();
    }

    private void initComponents() {

        Panel = new JPanel();
        Value = new JTextField();
        ButtonSearch = new JButton();
        ButtonDelete = new JButton();
        ButtonAdd = new JButton();

        setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 10));
        setPreferredSize(new Dimension(511, 50));
        setLayout(new BorderLayout());

        Panel.setLayout(new AbsoluteLayout());

        Value.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        Value.setText("text");
        Panel.add(Value, new AbsoluteConstraints(120, 10, 170, 30));

        ButtonSearch.setIcon(new ImageIcon(getClass().getResource("/config/icon/search.png"))); // NOI18N
        Panel.add(ButtonSearch, new AbsoluteConstraints(288, 10, 30, 30));

        ButtonDelete.setText("Delete");
        Panel.add(ButtonDelete, new AbsoluteConstraints(360, 10, 80, 30));

        ButtonAdd.setText("Add");
        Panel.add(ButtonAdd, new AbsoluteConstraints(440, 10, 80, 30));

        add(Panel, BorderLayout.LINE_END);
    }

    private JButton ButtonAdd;
    private JButton ButtonDelete;
    private JButton ButtonSearch;
    private JPanel Panel;
    private JTextField Value;
}

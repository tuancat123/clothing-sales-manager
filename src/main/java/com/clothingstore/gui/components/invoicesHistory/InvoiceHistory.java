package com.clothingstore.gui.components.invoicesHistory;

import java.awt.*;
import javax.swing.*;

public class InvoiceHistory extends JPanel {
    private static InvoiceHistory instance;

    public static InvoiceHistory getInstance() {
        if (instance == null) {
            instance = new InvoiceHistory();
        }
        return instance;
    }

    public InvoiceHistory() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        add(HistoryList.getInstance(), BorderLayout.WEST);
    }

}

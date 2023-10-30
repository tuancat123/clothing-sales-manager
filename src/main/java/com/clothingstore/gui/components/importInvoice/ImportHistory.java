package com.clothingstore.gui.components.importInvoice;

import java.awt.*;
import javax.swing.*;

public class ImportHistory extends JPanel {
    private static ImportHistory instance;

    public static ImportHistory getInstance() {
        if (instance == null) {
            instance = new ImportHistory();
        }
        return instance;
    }

    public ImportHistory() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        add(ImportList.getInstance(), BorderLayout.WEST);
        add(ImportDetail.getInstance(), BorderLayout.CENTER);
    }
    
}

package com.clothingstore.gui.admin.roleManagement;

import java.awt.*;
import javax.swing.*;

public class Invoice extends JPanel{

    Color color = new Color(204, 217, 255);
    
    public Invoice(String idInvoice){
        initComponents(idInvoice);
        IdInvoice.setFont(new Font("Segoe UI", 1, 16));     
    }
    public Invoice(){
        initComponents("Quan ly nhan vien");
    }
    private void initComponents(String idInvoice){
        IdInvoice = new JLabel(idInvoice);
      
        setLayout(new GridLayout(1,3));
        setPreferredSize(new Dimension(40,40));
        setBackground(color);
        add(IdInvoice);
      

    }
    private JLabel IdInvoice;
}

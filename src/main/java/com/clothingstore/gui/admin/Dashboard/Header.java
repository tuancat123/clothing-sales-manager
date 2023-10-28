/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.clothingstore.gui.admin.Dashboard;

import java.awt.*;

/**
 *
 * @author yanti
 */
public class Header extends javax.swing.JPanel {

    /**
     * Creates new form Header
     */
    private static Header instance;

    public static Header getInstance() {
        if (instance == null) {
            instance = new Header();
        }
        return instance;
    }

    public Header() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 20, 1, 1));
        setLayout(new java.awt.BorderLayout());
        setPreferredSize(new Dimension(70,75));

        jLabel1.setText("DashBoard");
        add(jLabel1, java.awt.BorderLayout.LINE_START);

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 1, 20, 1));
        jPanel1.setPreferredSize(new java.awt.Dimension(360, 80));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jPanel2.setLayout(new java.awt.BorderLayout());

        jTextField1.setText("jTextField1");
        jPanel2.add(jTextField1, java.awt.BorderLayout.CENTER);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/config/icon/coin.png"))); // NOI18N
        jButton1.setBorder(null);
        jPanel2.add(jButton1, java.awt.BorderLayout.EAST);

        jPanel1.add(jPanel2);

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 20, 1, 20));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel3.add(jComboBox1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3);

        add(jPanel1, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}

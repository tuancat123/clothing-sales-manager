/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.clothingstore.gui.components;

/**
 *
 * @author yanti
 */
public class ProductDetail extends javax.swing.JFrame {

    /**
     * Creates new form ProductDetail
     */
    public ProductDetail() {
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

        ImagePanel = new javax.swing.JPanel();
        Image = new javax.swing.JLabel();
        Name = new javax.swing.JTextField();
        Rating = new javax.swing.JLabel();
        OriPrice = new javax.swing.JLabel();
        Price = new javax.swing.JLabel();
        Id = new javax.swing.JLabel();
        AmountText = new javax.swing.JLabel();
        CategoryText = new javax.swing.JLabel();
        Gender = new javax.swing.JLabel();
        Category = new javax.swing.JLabel();
        GenderText = new javax.swing.JLabel();
        Scroll = new javax.swing.JScrollPane();
        Describe = new javax.swing.JTextPane();
        DescribeText = new javax.swing.JLabel();
        Control = new javax.swing.JPanel();
        SizePanel = new javax.swing.JPanel();
        ButtonExit = new javax.swing.JButton();
        ButtonAdd = new javax.swing.JButton();
        Remaining = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(685, 390));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ImagePanel.setLayout(new java.awt.GridBagLayout());

        Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/config/image/polo3.png"))); // NOI18N
        ImagePanel.add(Image, new java.awt.GridBagConstraints());

        getContentPane().add(ImagePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 190, 270));

        Name.setEditable(false);
        Name.setBackground(new java.awt.Color(255, 255, 255));
        Name.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Name.setText("Black Polo");
        Name.setAutoscrolls(false);
        Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameActionPerformed(evt);
            }
        });
        getContentPane().add(Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 260, 40));

        Rating.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Rating.setForeground(new java.awt.Color(102, 102, 255));
        Rating.setText("Rating: 4.5");
        getContentPane().add(Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 70, 20));

        OriPrice.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        OriPrice.setForeground(new java.awt.Color(153, 153, 153));
        OriPrice.setText("<html><s>450.000</s></html>");
        getContentPane().add(OriPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 70, 30));

        Price.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        Price.setForeground(new java.awt.Color(255, 51, 51));
        Price.setText("350.000 đ");
        getContentPane().add(Price, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, -1, 50));

        Id.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        Id.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Id.setText("0003");
        getContentPane().add(Id, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 60, 20));

        AmountText.setFont(new java.awt.Font("Segoe UI", 2, 15)); // NOI18N
        AmountText.setForeground(new java.awt.Color(102, 102, 102));
        AmountText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        AmountText.setText("Amount:");
        getContentPane().add(AmountText, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, 60, 20));

        CategoryText.setFont(new java.awt.Font("Segoe UI", 2, 15)); // NOI18N
        CategoryText.setForeground(new java.awt.Color(102, 102, 102));
        CategoryText.setText("Category: ");
        getContentPane().add(CategoryText, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 70, -1));

        Gender.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Gender.setText("Men");
        getContentPane().add(Gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 130, 30));

        Category.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Category.setForeground(new java.awt.Color(0, 51, 51));
        Category.setText("Polo");
        getContentPane().add(Category, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, 130, 20));

        GenderText.setFont(new java.awt.Font("Segoe UI", 2, 15)); // NOI18N
        GenderText.setForeground(new java.awt.Color(102, 102, 102));
        GenderText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        GenderText.setText("Gender:");
        getContentPane().add(GenderText, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, 60, 30));

        Describe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Describe.setText("Trong ví dụ này, chúng ta sử dụng GridBagLayout để quản lý việc đặt JLabel trên JPanel. Bằng cách thiết lập GridBagConstraints.gridwidth thành ");
        Scroll.setViewportView(Describe);

        getContentPane().add(Scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 160, 230, 70));

        DescribeText.setFont(new java.awt.Font("Segoe UI", 2, 15)); // NOI18N
        DescribeText.setForeground(new java.awt.Color(102, 102, 102));
        DescribeText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        DescribeText.setText("Describe:");
        getContentPane().add(DescribeText, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 60, 20));

        Control.setBackground(new java.awt.Color(255, 204, 204));
        Control.setLayout(new java.awt.BorderLayout());
        getContentPane().add(Control, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, 110, 20));

        SizePanel.setBackground(new java.awt.Color(204, 255, 204));
        getContentPane().add(SizePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, 190, 20));

        ButtonExit.setText("Exit");
        ButtonExit.setPreferredSize(new java.awt.Dimension(72, 28));
        ButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonExitActionPerformed(evt);
            }
        });
        getContentPane().add(ButtonExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 320, -1, -1));

        ButtonAdd.setText("Add To Cart");
        ButtonAdd.setPreferredSize(new java.awt.Dimension(94, 28));
        ButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAddActionPerformed(evt);
            }
        });
        getContentPane().add(ButtonAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 320, -1, -1));

        Remaining.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        Remaining.setText("45");
        getContentPane().add(Remaining, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 240, 20, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProductDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductDetail().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AmountText;
    private javax.swing.JButton ButtonAdd;
    private javax.swing.JButton ButtonExit;
    private javax.swing.JLabel Category;
    private javax.swing.JLabel CategoryText;
    private javax.swing.JPanel Control;
    private javax.swing.JTextPane Describe;
    private javax.swing.JLabel DescribeText;
    private javax.swing.JLabel Gender;
    private javax.swing.JLabel GenderText;
    private javax.swing.JLabel Id;
    private javax.swing.JLabel Image;
    private javax.swing.JPanel ImagePanel;
    private javax.swing.JTextField Name;
    private javax.swing.JLabel OriPrice;
    private javax.swing.JLabel Price;
    private javax.swing.JLabel Rating;
    private javax.swing.JLabel Remaining;
    private javax.swing.JScrollPane Scroll;
    private javax.swing.JPanel SizePanel;
    // End of variables declaration//GEN-END:variables
}

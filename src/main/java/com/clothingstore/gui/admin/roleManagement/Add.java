package com.clothingstore.gui.admin.roleManagement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class Add extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField_id;


    public Add(){
        initComponents();
    }

    public void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 647, 577);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLocationRelativeTo(null);
        setResizable(false);

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(500,50));
        panel.setBackground(new Color(0, 38, 77));
        contentPane.add(panel, BorderLayout.NORTH);

        JLabel lblNewLabel = new JLabel("Add Role Permission");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(lblNewLabel);

        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new GridLayout(0, 1, 0, 0));

        JPanel panel_3 = new JPanel();
        panel_3.setBorder(new TitledBorder(null, "Role information",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.add(panel_3);
        GridBagLayout gbl_panel_3 = new GridBagLayout();
        gbl_panel_3.columnWidths = new int[]{78, 180, 0, 75, 166, 0, 0, 0};
        gbl_panel_3.rowHeights = new int[]{0, 43, 37, 77, 36, 0, 0};
        gbl_panel_3.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_panel_3.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        panel_3.setLayout(gbl_panel_3);

        JLabel lbl_id = new JLabel("ID");
        GridBagConstraints gbc_lbl_id = new GridBagConstraints();
        gbc_lbl_id.insets = new Insets(0, 0, 5, 5);
        gbc_lbl_id.gridx = 0;
        gbc_lbl_id.gridy = 1;
        panel_3.add(lbl_id, gbc_lbl_id);

        textField_id = new JTextField();
        textField_id.setEditable(false);
        GridBagConstraints gbc_textField_id = new GridBagConstraints();
        gbc_textField_id.insets = new Insets(0, 0, 5, 5);
        gbc_textField_id.fill = GridBagConstraints.BOTH;
        gbc_textField_id.gridx = 1;
        gbc_textField_id.gridy = 1;
        panel_3.add(textField_id, gbc_textField_id);
        textField_id.setColumns(10);

        JLabel lbl_username = new JLabel("Role");
        GridBagConstraints gbc_lbl_username = new GridBagConstraints();
        gbc_lbl_username.insets = new Insets(0, 0, 5, 5);
        gbc_lbl_username.gridx = 0;
        gbc_lbl_username.gridy = 2;
        panel_3.add(lbl_username, gbc_lbl_username);
        
        JComboBox comboBox_role = new JComboBox();
        GridBagConstraints gbc_comboBox_role = new GridBagConstraints();
        gbc_comboBox_role.insets = new Insets(0, 0, 5, 5);
        gbc_comboBox_role.fill = GridBagConstraints.BOTH;
        gbc_comboBox_role.gridx = 1;
        gbc_comboBox_role.gridy = 2;
        panel_3.add(comboBox_role, gbc_comboBox_role);

        JLabel lbl_password = new JLabel("Permission");
        GridBagConstraints gbc_lbl_password = new GridBagConstraints();
        gbc_lbl_password.insets = new Insets(0, 0, 5, 5);
        gbc_lbl_password.gridx = 0;
        gbc_lbl_password.gridy = 3;
        panel_3.add(lbl_password, gbc_lbl_password);
        
        JTextArea textArea_permission = new JTextArea();
        GridBagConstraints gbc_textArea_permission = new GridBagConstraints();
        gbc_textArea_permission.insets = new Insets(0, 0, 5, 5);
        gbc_textArea_permission.fill = GridBagConstraints.BOTH;
        gbc_textArea_permission.gridx = 1;
        gbc_textArea_permission.gridy = 3;
        panel_3.add(textArea_permission, gbc_textArea_permission);

        JPanel panel_Model = new JPanel();
        panel_Model.setPreferredSize(new Dimension(500,100));
        panel_Model.setBorder(new TitledBorder(null, "Button List",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPane.add(panel_Model, BorderLayout.SOUTH);

        JButton btnAdd = new JButton("Add");
        btnAdd.setPreferredSize(new Dimension(100,30));
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        panel_Model.add(btnAdd);

        JButton btnReset = new JButton("Reset");
        btnReset.setPreferredSize(new Dimension(100,30));
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        panel_Model.add(btnReset);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setPreferredSize(new Dimension(100,30));
        panel_Model.add(btnCancel);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Window window = SwingUtilities.getWindowAncestor((Component)e.getSource());
                if (window != null) {
                    window.dispose();
                }
            }
        });

        JPanel panel_4 = new JPanel();
        panel_Model.add(panel_4);
        panel_4.setLayout(new GridLayout(1, 0, 0, 0));
    }

}
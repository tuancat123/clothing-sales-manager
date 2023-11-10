package com.clothingstore.gui.admin.roleManagement;



import com.clothingstore.gui.components.Menu;
import com.clothingstore.gui.models.MenuData;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class RoleManagement extends JPanel {
    private static RoleManagement instance;
    private JTextField textField;
    private JPanel employeePanel;
    private JPanel topPanel;
    private JLabel lbl_title;
    private JLabel titleLabel;
    private JPanel panel;
    private JPanel centerPanel;
    private JLabel lblNewLabel_1;
    private JPanel panel_Search;
    private JPanel panel_Search_1;
    private JPanel bottomPanel;
    private JPanel panel_table;
    private JPanel panel_Model;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnEdit;
    private JButton btnSearch;
    private JPanel panel_Role;

    public static RoleManagement getInstance() {
        if (instance == null) {
            instance = new RoleManagement();
        }
        return instance;
    }

    public RoleManagement(){
        initComponents();
    }

    public void initComponents() {

        employeePanel = new JPanel();

        setLayout(new BorderLayout());
        setSize(new Dimension(1130, 628));
        employeePanel.setBackground(new Color(0, 38, 77));
        employeePanel.setBorder(BorderFactory.createEmptyBorder(1, 20, 1, 5));
        setLayout(new BorderLayout());

        employeePanel.setLayout(new BorderLayout());
        add(employeePanel, BorderLayout.CENTER);

        topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(1000, 120));
        topPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 255, 255)));
        topPanel.setBackground(new Color(0, 38, 77));
        employeePanel.add(topPanel, BorderLayout.NORTH);
        topPanel.setLayout(new GridLayout(0, 1, 0, 0));

        lbl_title = new JLabel("      Home/Role Management");
        lbl_title.setForeground(new Color(255, 255, 255));
        lbl_title.setFont(new Font("Arial Black", Font.BOLD, 17));
        lbl_title.setPreferredSize(new Dimension(20,30));
        topPanel.add(lbl_title);

        titleLabel = new JLabel("   Role Management");
        titleLabel.setForeground(new Color(255, 255, 255));
        titleLabel.setFont(new Font("Arial Black", Font.PLAIN, 30));
        topPanel.add(titleLabel);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(20,20));
        panel.setBackground(new Color(0, 38, 77));
        topPanel.add(panel);

        centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(60, 50));
        centerPanel.setBackground(new Color(0, 38, 77));
        employeePanel.add(centerPanel, BorderLayout.CENTER);
        GridBagLayout gbl_centerPanel = new GridBagLayout();
        gbl_centerPanel.columnWidths = new int[]{86, 224, 0, 172, 144, 0, 0};
        gbl_centerPanel.rowHeights = new int[]{0, 36, 0, 0};
        gbl_centerPanel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_centerPanel.rowWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
        centerPanel.setLayout(gbl_centerPanel);

        lblNewLabel_1 = new JLabel("Search");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 0;
        gbc_lblNewLabel_1.gridy = 1;
        centerPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);

        textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.insets = new Insets(0, 0, 5, 5);
        gbc_textField.fill = GridBagConstraints.BOTH;
        gbc_textField.gridx = 1;
        gbc_textField.gridy = 1;
        centerPanel.add(textField, gbc_textField);
        textField.setColumns(10);

        btnSearch = new JButton("Search");
        btnSearch.setPreferredSize(new Dimension(100, 30));
        GridBagConstraints gbc_btnSearch = new GridBagConstraints();
        gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
        gbc_btnSearch.gridx = 2;
        gbc_btnSearch.gridy = 1;
        centerPanel.add(btnSearch, gbc_btnSearch);

        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(0, 38, 77));
        employeePanel.add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.setLayout(new BorderLayout());

        panel_table = new JPanel();
        panel_table.setForeground(new Color(0, 38, 77));
        panel_table.setBackground(new Color(0, 38, 77));
        panel_table.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "List of roles permision", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
        panel_table.setPreferredSize(new Dimension(600,400));
        panel_table.setLayout(new BorderLayout());
        bottomPanel.add(panel_table, BorderLayout.CENTER);
        
        panel_Role = new JPanel();
        panel_Role.setBackground(new Color(0, 38, 77));
        panel_Role.setLayout(new GridLayout(1,0));
        panel_Role.add(Content.getInstance());
        panel_table.add(panel_Role, BorderLayout.CENTER);

        panel_Model = new JPanel();
        panel_Model.setBackground(new Color(0, 38, 77));
        panel_Model.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Button list", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
        panel_Model.setPreferredSize(new Dimension(200,80));
        panel_table.add(panel_Model, BorderLayout.SOUTH);
        bottomPanel.add(panel_table, BorderLayout.CENTER);

        btnAdd = new JButton("Add Role");
        btnAdd.setPreferredSize(new Dimension(200,40));
        panel_Model.add(btnAdd);
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Add addEmployee = new Add();
                addEmployee.setVisible(true);

            }
        });



        btnDelete = new JButton("Delete Role");
        btnDelete.setPreferredSize(new Dimension(200,40));
        panel_Model.add(btnDelete);
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(null,"Do you want to delete this Role?","Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                } else {}
            }
        });

        btnEdit = new JButton("Edit Role");
        btnEdit.setPreferredSize(new Dimension(200,40));
        panel_Model.add(btnEdit);
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 Edit editEmployee = new Edit();
                 editEmployee.setVisible(true);
            }
        });
   


    }
}

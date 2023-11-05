package com.clothingstore.gui.admin.Employees;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Edit extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField_id;
    private JTextField textField_phone;
    private JTextField textField_username;
    private JTextField textField_password;
    private JTextField textField_email;
    private JTextField textField_name;
    private JTextField textField_address;

    public Edit(){
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

        JLabel lblNewLabel = new JLabel("Edit Employee");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(lblNewLabel);

        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new GridLayout(1, 0, 0, 0));

        JPanel panel_3 = new JPanel();
        panel_3.setForeground(new Color(255, 255, 255));
        panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Employee information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
        panel_1.add(panel_3);
        GridBagLayout gbl_panel_3 = new GridBagLayout();
        gbl_panel_3.columnWidths = new int[]{78, 180, 0, 75, 166, 0, 0, 0};
        gbl_panel_3.rowHeights = new int[]{0, 43, 37, 39, 36, 41, 51, 0, 0};
        gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_panel_3.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        panel_3.setLayout(gbl_panel_3);

        JLabel lbl_ID = new JLabel("ID");
        lbl_ID.setForeground(new Color(0, 0, 0));
        GridBagConstraints gbc_lbl_ID = new GridBagConstraints();
        gbc_lbl_ID.insets = new Insets(0, 0, 5, 5);
        gbc_lbl_ID.gridx = 0;
        gbc_lbl_ID.gridy = 1;
        panel_3.add(lbl_ID, gbc_lbl_ID);

        textField_id = new JTextField();
        textField_id.setEditable(false);
        GridBagConstraints gbc_textField_id = new GridBagConstraints();
        gbc_textField_id.insets = new Insets(0, 0, 5, 5);
        gbc_textField_id.fill = GridBagConstraints.BOTH;
        gbc_textField_id.gridx = 1;
        gbc_textField_id.gridy = 1;
        panel_3.add(textField_id, gbc_textField_id);
        textField_id.setColumns(10);

        JLabel lbl_Phone = new JLabel("Phone");
        lbl_Phone.setForeground(new Color(0, 0, 0));
        GridBagConstraints gbc_lbl_Phone = new GridBagConstraints();
        gbc_lbl_Phone.insets = new Insets(0, 0, 5, 5);
        gbc_lbl_Phone.gridx = 3;
        gbc_lbl_Phone.gridy = 1;
        panel_3.add(lbl_Phone, gbc_lbl_Phone);

        textField_phone = new JTextField();
        textField_phone.setColumns(10);
        GridBagConstraints gbc_textField_phone = new GridBagConstraints();
        gbc_textField_phone.insets = new Insets(0, 0, 5, 5);
        gbc_textField_phone.fill = GridBagConstraints.BOTH;
        gbc_textField_phone.gridx = 4;
        gbc_textField_phone.gridy = 1;
        panel_3.add(textField_phone, gbc_textField_phone);

        JLabel lbl_Username = new JLabel("Username");
        lbl_Username.setForeground(new Color(0, 0, 0));
        GridBagConstraints gbc_lbl_Username = new GridBagConstraints();
        gbc_lbl_Username.insets = new Insets(0, 0, 5, 5);
        gbc_lbl_Username.gridx = 0;
        gbc_lbl_Username.gridy = 2;
        panel_3.add(lbl_Username, gbc_lbl_Username);

        textField_username = new JTextField();
        textField_username.setColumns(10);
        GridBagConstraints gbc_textField_username = new GridBagConstraints();
        gbc_textField_username.insets = new Insets(0, 0, 5, 5);
        gbc_textField_username.fill = GridBagConstraints.BOTH;
        gbc_textField_username.gridx = 1;
        gbc_textField_username.gridy = 2;
        panel_3.add(textField_username, gbc_textField_username);

        JLabel lbl_Gender = new JLabel("Gender");
        lbl_Gender.setForeground(new Color(0, 0, 0));
        GridBagConstraints gbc_lbl_Gender = new GridBagConstraints();
        gbc_lbl_Gender.insets = new Insets(0, 0, 5, 5);
        gbc_lbl_Gender.gridx = 3;
        gbc_lbl_Gender.gridy = 2;
        panel_3.add(lbl_Gender, gbc_lbl_Gender);

        JComboBox<String> comboBox_role = new JComboBox<>();
        comboBox_role.setModel(new DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        GridBagConstraints gbc_comboBox_role = new GridBagConstraints();
        gbc_comboBox_role.insets = new Insets(0, 0, 5, 5);
        gbc_comboBox_role.fill = GridBagConstraints.BOTH;
        gbc_comboBox_role.gridx = 4;
        gbc_comboBox_role.gridy = 2;
        panel_3.add(comboBox_role, gbc_comboBox_role);

        JLabel lbl_Password = new JLabel("Password");
        lbl_Password.setForeground(new Color(0, 0, 0));
        GridBagConstraints gbc_lbl_Password = new GridBagConstraints();
        gbc_lbl_Password.insets = new Insets(0, 0, 5, 5);
        gbc_lbl_Password.gridx = 0;
        gbc_lbl_Password.gridy = 3;
        panel_3.add(lbl_Password, gbc_lbl_Password);

        textField_password = new JTextField();
        textField_password.setColumns(10);
        GridBagConstraints gbc_textField_password = new GridBagConstraints();
        gbc_textField_password.insets = new Insets(0, 0, 5, 5);
        gbc_textField_password.fill = GridBagConstraints.BOTH;
        gbc_textField_password.gridx = 1;
        gbc_textField_password.gridy = 3;
        panel_3.add(textField_password, gbc_textField_password);

        JLabel lbl_Role = new JLabel("Role");
        lbl_Role.setForeground(new Color(0, 0, 0));
        GridBagConstraints gbc_lbl_Role = new GridBagConstraints();
        gbc_lbl_Role.insets = new Insets(0, 0, 5, 5);
        gbc_lbl_Role.gridx = 3;
        gbc_lbl_Role.gridy = 3;
        panel_3.add(lbl_Role, gbc_lbl_Role);

        JComboBox<String> comboBox_1 = new JComboBox<>();
        GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
        gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
        gbc_comboBox_1.fill = GridBagConstraints.BOTH;
        gbc_comboBox_1.gridx = 4;
        gbc_comboBox_1.gridy = 3;
        panel_3.add(comboBox_1, gbc_comboBox_1);

        JLabel lbl_Email = new JLabel("Email");
        lbl_Email.setForeground(new Color(0, 0, 0));
        GridBagConstraints gbc_lbl_Email = new GridBagConstraints();
        gbc_lbl_Email.insets = new Insets(0, 0, 5, 5);
        gbc_lbl_Email.gridx = 0;
        gbc_lbl_Email.gridy = 4;
        panel_3.add(lbl_Email, gbc_lbl_Email);

        textField_email = new JTextField();
        textField_email.setColumns(10);
        GridBagConstraints gbc_textField_email = new GridBagConstraints();
        gbc_textField_email.insets = new Insets(0, 0, 5, 5);
        gbc_textField_email.fill = GridBagConstraints.BOTH;
        gbc_textField_email.gridx = 1;
        gbc_textField_email.gridy = 4;
        panel_3.add(textField_email, gbc_textField_email);

        JLabel lbl_Address = new JLabel("Address");
        lbl_Address.setForeground(new Color(0, 0, 0));
        GridBagConstraints gbc_lbl_Address = new GridBagConstraints();
        gbc_lbl_Address.insets = new Insets(0, 0, 5, 5);
        gbc_lbl_Address.gridx = 3;
        gbc_lbl_Address.gridy = 4;
        panel_3.add(lbl_Address, gbc_lbl_Address);

        textField_address = new JTextField();
        GridBagConstraints gbc_textField_address = new GridBagConstraints();
        gbc_textField_address.insets = new Insets(0, 0, 5, 5);
        gbc_textField_address.fill = GridBagConstraints.BOTH;
        gbc_textField_address.gridx = 4;
        gbc_textField_address.gridy = 4;
        panel_3.add(textField_address, gbc_textField_address);
        textField_address.setColumns(10);

        JLabel lbl_Name = new JLabel("Name");
        lbl_Name.setForeground(new Color(0, 0, 0));
        GridBagConstraints gbc_lbl_Name = new GridBagConstraints();
        gbc_lbl_Name.insets = new Insets(0, 0, 5, 5);
        gbc_lbl_Name.gridx = 0;
        gbc_lbl_Name.gridy = 5;
        panel_3.add(lbl_Name, gbc_lbl_Name);

        textField_name = new JTextField();
        textField_name.setColumns(10);
        GridBagConstraints gbc_textField_name = new GridBagConstraints();
        gbc_textField_name.insets = new Insets(0, 0, 5, 5);
        gbc_textField_name.fill = GridBagConstraints.BOTH;
        gbc_textField_name.gridx = 1;
        gbc_textField_name.gridy = 5;
        panel_3.add(textField_name, gbc_textField_name);

        JLabel lbl_Image = new JLabel("Image");
        lbl_Image.setForeground(new Color(0, 0, 0));
        GridBagConstraints gbc_lbl_Image = new GridBagConstraints();
        gbc_lbl_Image.insets = new Insets(0, 0, 5, 5);
        gbc_lbl_Image.gridx = 3;
        gbc_lbl_Image.gridy = 5;
        panel_3.add(lbl_Image, gbc_lbl_Image);

        JPanel panel_image = new JPanel();
        panel_image.setBorder(new LineBorder(new Color(0, 0, 0)));
        GridBagConstraints gbc_panel_image = new GridBagConstraints();
        gbc_panel_image.gridheight = 2;
        gbc_panel_image.insets = new Insets(0, 0, 5, 5);
        gbc_panel_image.fill = GridBagConstraints.BOTH;
        gbc_panel_image.gridx = 4;
        gbc_panel_image.gridy = 5;
        panel_3.add(panel_image, gbc_panel_image);
        panel_image.setLayout(new GridLayout(0, 1, 0, 0));

        JLabel lbl_img = new JLabel("");
        lbl_img.setHorizontalAlignment(SwingConstants.CENTER);
        panel_image.add(lbl_img);

        JButton btn_upload = new JButton("Upload Image");
        GridBagConstraints gbc_btn_upload = new GridBagConstraints();
        gbc_btn_upload.fill = GridBagConstraints.BOTH;
        gbc_btn_upload.insets = new Insets(0, 0, 5, 5);
        gbc_btn_upload.gridx = 5;
        gbc_btn_upload.gridy = 5;
        panel_3.add(btn_upload, gbc_btn_upload);
        btn_upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser file = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "JPG & GIF Images", "jpg", "gif", "png");
                file.setFileFilter(filter);
                int returnVal = file.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    String filePath = file.getSelectedFile().getAbsolutePath();
                    ImageIcon imageIcon = new ImageIcon(filePath);
                    System.out.println(""+filePath);
                    lbl_img.setIcon(imageIcon);
                }
            }
        });

        JPanel panel_Model = new JPanel();
        panel_Model.setPreferredSize(new Dimension(500,100));
        panel_Model.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Button List", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        contentPane.add(panel_Model, BorderLayout.SOUTH);

        JButton btnAdd = new JButton("Edit");
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

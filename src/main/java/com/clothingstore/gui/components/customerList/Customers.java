package com.clothingstore.gui.components.customerList;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.clothingstore.bus.CustomerBUS;
import com.clothingstore.gui.components.customerList.Add;
import com.clothingstore.gui.components.customerList.Edit;
import com.clothingstore.gui.components.customerList.CustomerImageRender;
import com.clothingstore.models.CustomerModel;
import java.awt.event.KeyEvent;

public class Customers extends JPanel {
  private static Customers instance;
  private JTextField textField;
  private JPanel customerPanel;
  private JPanel topPanel;
  private JLabel lbl_title;
  private JLabel titleLabel;
  private JPanel panel;
  private JPanel centerPanel;
  private JLabel lblNewLabel_1;
  private JPanel bottomPanel;
  private JPanel panel_table;
  private JTable table;
  private JPanel panel_Model;
  private JButton btnAdd;
  private JButton btnSearch;

  private JScrollPane scrollPane;
  private JButton btnDelete;
  private JButton btnEdit;

  List<CustomerModel> customerList = new ArrayList<>(CustomerBUS.getInstance().getAllModels());

  public static Customers getInstance() {
    if (instance == null) {
      instance = new Customers();
    }
    return instance;
  }

  public Customers() {
    initComponents();
  }

  private void initComponents() {
    customerPanel = new JPanel();

    setLayout(new BorderLayout());
    setSize(new Dimension(1130, 628));
    customerPanel.setBackground(new Color(0, 38, 77));
    customerPanel.setBorder(BorderFactory.createEmptyBorder(1, 20, 1, 5));
    setLayout(new BorderLayout());

    customerPanel.setLayout(new BorderLayout());
    add(customerPanel, BorderLayout.CENTER);

    topPanel = new JPanel();
    topPanel.setPreferredSize(new Dimension(1000, 120));
    topPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 255, 255)));
    topPanel.setBackground(new Color(0, 38, 77));
    customerPanel.add(topPanel, BorderLayout.NORTH);
    topPanel.setLayout(new GridLayout(0, 1, 0, 0));

    lbl_title = new JLabel("      Home/Customers");
    lbl_title.setForeground(new Color(255, 255, 255));
    lbl_title.setFont(new Font("Arial Black", Font.BOLD, 17));
    lbl_title.setPreferredSize(new Dimension(20, 30));
    topPanel.add(lbl_title);

    titleLabel = new JLabel("  Manage Customers");
    titleLabel.setForeground(new Color(255, 255, 255));
    titleLabel.setFont(new Font("Arial Black", Font.PLAIN, 30));
    topPanel.add(titleLabel);

    panel = new JPanel();
    panel.setPreferredSize(new Dimension(20, 20));
    panel.setBackground(new Color(0, 38, 77));
    topPanel.add(panel);

    centerPanel = new JPanel();
    centerPanel.setPreferredSize(new Dimension(60, 50));
    centerPanel.setBackground(new Color(0, 38, 77));
    customerPanel.add(centerPanel, BorderLayout.CENTER);
    GridBagLayout gbl_centerPanel = new GridBagLayout();
    gbl_centerPanel.columnWidths = new int[] { 86, 224, 0, 172, 144, 0, 0 };
    gbl_centerPanel.rowHeights = new int[] { 0, 36, 0, 0 };
    gbl_centerPanel.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
    gbl_centerPanel.rowWeights = new double[] { 1.0, 0.0, 1.0, Double.MIN_VALUE };
    centerPanel.setLayout(gbl_centerPanel);

    setLayout(new BorderLayout());
    add(CustomerList.getInstance(), BorderLayout.WEST);
    add(new CustomerDetail(customerList.get(customerList.size() - 1)), BorderLayout.CENTER);
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
    btnSearch.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String searchValue = textField.getText();
        String[] columnNames = { "id", "name", "phone", "email" };
        List<CustomerModel> searchResults = CustomerBUS.getInstance().searchModel(searchValue, columnNames);
        showSearchResult(searchResults);
      }
    });

    textField.addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          String searchValue = textField.getText();
          String[] columnNames = { "id", "name", "phone", "email" };
          List<CustomerModel> searchResults = CustomerBUS.getInstance().searchModel(searchValue, columnNames);
          showSearchResult(searchResults);
        }
      }
    });
    bottomPanel = new JPanel();
    bottomPanel.setBackground(new Color(0, 38, 77));
    customerPanel.add(bottomPanel, BorderLayout.SOUTH);
    bottomPanel.setLayout(new BorderLayout());

    panel_table = new JPanel();
    panel_table.setForeground(new Color(0, 38, 77));
    panel_table.setBackground(new Color(0, 38, 77));
    panel_table.setBorder(
        new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
            "List of Customer", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
    panel_table.setPreferredSize(new Dimension(500, 400));
    panel_table.setLayout(new BorderLayout(0, 0));

    table = new JTable();
    table.setModel(new DefaultTableModel(
        new Object[][] {
        },
        new String[] {
            "id", "name", "phone", "email"
        }));
    table.setRowHeight(50);
    scrollPane = new JScrollPane(table);

    panel_table.add(scrollPane);
    bottomPanel.add(panel_table, BorderLayout.NORTH);

    panel_Model = new JPanel();
    panel_Model.setBackground(new Color(0, 38, 77));
    panel_Model.setBorder(
        new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
            "Button list", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
    panel_Model.setPreferredSize(new Dimension(200, 80));
    panel_table.add(panel_Model, BorderLayout.SOUTH);

    btnAdd = new JButton("Add Customer");
    btnAdd.setPreferredSize(new Dimension(200, 40));
    panel_Model.add(btnAdd);
    btnAdd.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        com.clothingstore.gui.components.customerList.Add addCustomer = new Add();
        addCustomer.setVisible(true);

      }
    });

    btnDelete = new JButton("Delete Customer");
    btnDelete.setPreferredSize(new Dimension(200, 40));
    panel_Model.add(btnDelete);
    btnDelete.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog(null, "Do you want to delete this Customer?", "Confirm Deletion",
            JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
          int selectedRow = table.getSelectedRow();
          int customerID = (int) table.getModel().getValueAt(selectedRow, 0);

          deleteCustomer(customerID);
          updateDTFromList();
        }
      }
    });

    btnEdit = new JButton("Edit Customer");
    btnEdit.setPreferredSize(new Dimension(200, 40));
    panel_Model.add(btnEdit);
    btnEdit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int index = table.getSelectedRow();
        if (index == -1) {
          JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng muốn sửa", "Thông báo",
              JOptionPane.INFORMATION_MESSAGE);
          return;
        } else {
          int modelIndex = table.convertRowIndexToModel(index);
          int id = (int) table.getModel().getValueAt(modelIndex, 0);
          System.out.println("id: " + id);
          CustomerBUS.getInstance().refreshData();
          for (CustomerModel user : customerList) {
            if (id == user.getId()) {
              showUpdateInfo(id);
            }
          }
        }

      }
    });

    JButton btnRefresh = new JButton("Refresh");
    btnRefresh.setPreferredSize(new Dimension(200, 40));
    panel_Model.add(btnRefresh);
    btnRefresh.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        updateDTFromList();
        textField.setText("");
      }
    });
    updateDTFromList();

  }

  public void updateDTFromList() {
    CustomerBUS.getInstance().refreshData();
    DefaultTableModel model_table = (DefaultTableModel) table.getModel();
    model_table.setRowCount(0);

    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    renderer.setHorizontalAlignment(SwingConstants.CENTER);

    for (CustomerModel Customer : customerList) {
      model_table.addRow(new Object[] { Customer.getId() + "" + Customer.getCustomerName(), Customer.getEmail() + "",
          Customer.getPhone() });
    }

    table.getColumnModel().getColumn(4).setCellRenderer(renderer);
    table.getColumnModel().getColumn(4).setCellRenderer(new CustomerImageRender());
  }

  public void deleteCustomer(int customerId) {
    try {
      int deletedRow = CustomerBUS.getInstance().deleteModel(customerId);
      if (deletedRow > 0) {
        JOptionPane.showMessageDialog(null, "Xóa khách hàng thành công");
      }
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Xóa khách hàng thất bại");
    }
  }

  public void showUpdateInfo(int customerID) {
    com.clothingstore.gui.components.customerList.Edit editCustomer = new Edit();
    CustomerModel customerModel = CustomerBUS.getInstance().getModelById(customerID);
    CustomerBUS.getInstance().refreshData();

    editCustomer.textField_email.setText(customerModel.getEmail());
    editCustomer.textField_name.setText(customerModel.getCustomerName());
    editCustomer.textField_phone.setText(customerModel.getPhone());

    editCustomer.setVisible(true);
  }

  public void showSearchResult(List<CustomerModel> search) {
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.setRowCount(0);

    for (CustomerModel user : search) {
      model.addRow(new Object[] {
          user.getId(),
          user.getEmail(),
          user.getCustomerName(),
          user.getPhone(),
      });
    }
  }
}

/*
 * public void Remove(){
 * Container contentPane = Customers.getInstance();
 * Component centerComponent = ((BorderLayout)
 * contentPane.getLayout()).getLayoutComponent(BorderLayout.CENTER);
 * contentPane.remove(centerComponent);
 * contentPane.revalidate();
 * contentPane.repaint();
 * }
 */

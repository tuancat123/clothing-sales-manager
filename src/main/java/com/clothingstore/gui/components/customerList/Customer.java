package com.clothingstore.gui.components.customerList;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import com.clothingstore.models.CustomerModel;

public class Customer extends JPanel {

  public Customer(CustomerModel customerModel) {
    initComponents(customerModel);
    this.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
          CustomerDetail customerDetail = new CustomerDetail(customerModel);
          Customers.getInstance().Remove();
          Customers.getInstance().add(customerDetail, BorderLayout.CENTER);
          Customers.getInstance().revalidate();
          Customers.getInstance().repaint(); 
      }
  });
  }

  private void initComponents(CustomerModel customerModel) {

    Icon = new JLabel();
    Detail = new JPanel();
    Panel = new JPanel();
    Name = new JLabel();
    Id = new JLabel();
    PhonePanel = new JPanel();
    Phone = new JLabel();

    Color color = new Color(204, 224, 255);

    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(40, 60));
    setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
    setBackground(color);

    Icon.setIcon(new ImageIcon(getClass().getResource("/resources/icons/user.png")));
    Icon.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 0));
    add(Icon, BorderLayout.LINE_START);

    Detail.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5));
    Detail.setBackground(color);
    Detail.setLayout(new BorderLayout());

    Panel.setLayout(new GridLayout(2, 0));
    Panel.setBackground(color);

    Name.setHorizontalAlignment(SwingConstants.CENTER);
    Name.setText(customerModel.getCustomerName());
    Name.setFont(new Font("Segoe UI", 0, 14));
    Name.setForeground(Color.RED);
    Panel.add(Name);

    Id.setHorizontalAlignment(SwingConstants.CENTER);
    Id.setText(""+customerModel.getId());
    Id.setForeground(new Color(0, 128, 0));
    Panel.add(Id);

    Detail.add(Panel, BorderLayout.LINE_START);

    add(Detail, BorderLayout.CENTER);

    PhonePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    PhonePanel.setLayout(new GridLayout(2, 1));
    PhonePanel.setBackground(color);

    Phone.setText(""+customerModel.getPhone());
    Phone.setFont(new Font("Segoe UI", 0, 13));
    Phone.setForeground(new Color(102, 0, 51));
    PhonePanel.add(Phone);

    add(PhonePanel, BorderLayout.EAST);
  }

  private JLabel Phone;
  private JPanel Detail;
  private JLabel Icon;
  private JLabel Id;
  private JPanel Panel;
  private JLabel Name;
  private JPanel PhonePanel;
}

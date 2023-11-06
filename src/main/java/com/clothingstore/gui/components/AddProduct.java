package com.clothingstore.gui.components;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AddProduct extends JFrame {

  String[] name = { "Name", "Category", "Gender", "Price", "Origin Price", "Description" };
  String[] size = { "S", "M", "L", "XL", "XXL" };
  static int[] sizeQuantity = { -1, -1, -1, -1, -1 };

  public AddProduct() {
    initComponents();
    setVisible(true);
    setLocationRelativeTo(null);
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  }

  public void initComponents() {
    Scroll = new JScrollPane();
    Panel = new JPanel();
    Content = new JPanel();
    NameFrame = new JLabel("New Product");
    ImagePanel = new JPanel();
    Image = new JLabel();
    Buttons = new JPanel();
    ButtonBack = new JButton("Back");
    ButtonSave = new JButton("Save");
    ChooseImage = new JPanel();
    LinkImage = new JTextField();
    Choose = new JButton();
    SizePanel = new JPanel();
    Size = new JComboBox<>();
    QuantityPanel = new JPanel();

    setSize(800, 500);
    setPreferredSize(new Dimension(800, 500));
    setLayout(new BorderLayout());

    add(NameFrame, BorderLayout.NORTH);

    Panel.setLayout(new BorderLayout());

    Content.setLayout(new GridLayout(0, 1));
    for (int i = 0; i < 6; i++) {
      JPanel Panel = new JPanel();
      Panel.setLayout(new BorderLayout());
      Panel.setPreferredSize(new Dimension(40, 80));
      Panel.setBorder(BorderFactory.createEmptyBorder(5, 40, 15, 45));

      JLabel Name = new JLabel(name[i]);
      Name.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 45));
      Panel.add(Name, BorderLayout.NORTH);

      JTextField Value = new JTextField();
      Value.setPreferredSize(new Dimension(150, 35));
      Panel.add(Value, BorderLayout.CENTER);

      JLabel Invalid = new JLabel("No");
      Invalid.setForeground(Color.RED);
      Panel.add(Invalid, BorderLayout.SOUTH);

      Content.add(Panel);
    }
    Panel.add(Content, BorderLayout.CENTER);

    SizePanel.setLayout(new GridBagLayout());

    Size.setModel(new DefaultComboBoxModel<>(size));
    Size.addActionListener(ChooseSizeAction);
    SizePanel.add(Size);
    Panel.add(SizePanel, BorderLayout.SOUTH);

    QuantityPanel.setLayout(new GridLayout(0, 1));
    SizePanel.add(QuantityPanel);

    Scroll.setViewportView(Panel);
    add(Scroll, BorderLayout.CENTER);

    ImagePanel.setLayout(new BorderLayout());
    ImagePanel.setPreferredSize(new Dimension(250, 250));
    ImagePanel.setBorder(BorderFactory.createEmptyBorder(25, 15, 5, 15));

    Image.setIcon(new ImageIcon(getClass().getResource("/config/image/Ao/Nam/Hoodie/AoHoodieA.png")));
    Image.setBackground(Color.RED);
    Image.setPreferredSize(new Dimension(180, 300));
    ImagePanel.add(Image, BorderLayout.NORTH);

    ChooseImage.setLayout(new BorderLayout());
    ChooseImage.add(LinkImage, BorderLayout.CENTER);
    ChooseImage.add(Choose, BorderLayout.EAST);
    ImagePanel.add(ChooseImage, BorderLayout.SOUTH);

    add(ImagePanel, BorderLayout.WEST);

    ButtonSave.addActionListener(SaveAction);

    Buttons.setPreferredSize(new Dimension(60, 60));
    Buttons.setLayout(new GridBagLayout());
    Buttons.add(ButtonBack);
    Buttons.add(ButtonSave);
    add(Buttons, BorderLayout.SOUTH);

  }

  private JScrollPane Scroll;
  private JPanel Panel;
  private JPanel SizePanel;
  private JPanel Content;
  private JLabel NameFrame;
  private JPanel ImagePanel;
  private JLabel Image;
  private JPanel Buttons;
  private JButton ButtonBack;
  private JButton ButtonSave;
  private JPanel ChooseImage;
  private JTextField LinkImage;
  private JButton Choose;
  private JComboBox<String> Size;
  private JPanel QuantityPanel;

  public ActionListener ChooseSizeAction = new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {
      JPanel Panel = new JPanel();
      Panel.setLayout(new GridBagLayout());
      Panel.setPreferredSize(new Dimension(150, 35));

      GridBagConstraints gbc = new GridBagConstraints();
      gbc.ipadx = 20;
      gbc.fill = GridBagConstraints.LINE_START;

      JLabel SizeName = new JLabel(Size.getSelectedItem().toString() + ": amount is");
      SizeName.setFont(new Font("Segoe UI", 0, 14));
      Panel.add(SizeName, gbc);

      JTextField SizeQuantity = new JTextField();
      SizeQuantity.setPreferredSize(new Dimension(35, 25));
      SizeQuantity.setText("0");
      Panel.add(SizeQuantity);

      switch (Size.getSelectedItem().toString()) {
        case "S":
          if (sizeQuantity[0] == -1) {
            QuantityPanel.add(Panel, gbc);
            sizeQuantity[0] = 0;
            repaint();
            revalidate();
            SizeQuantity.getDocument().addDocumentListener(SaveValueAction(0, SizeQuantity));
          }
          break;
        case "M":
          if (sizeQuantity[1] == -1) {
            sizeQuantity[1] = 0;
            QuantityPanel.add(Panel, gbc);
            repaint();
            revalidate();
            SizeQuantity.getDocument().addDocumentListener(SaveValueAction(1, SizeQuantity));
          }
          break;
        case "L":
          if (sizeQuantity[2] == -1) {
            sizeQuantity[2] = 0;
            QuantityPanel.add(Panel, gbc);
            repaint();
            revalidate();
            SizeQuantity.getDocument().addDocumentListener(SaveValueAction(2, SizeQuantity));
          }
          break;
        case "XL":
          if (sizeQuantity[3] == -1) {
            sizeQuantity[3] = 0;
            QuantityPanel.add(Panel, gbc);
            repaint();
            revalidate();
            SizeQuantity.getDocument().addDocumentListener(SaveValueAction(3, SizeQuantity));
          }
          break;
        case "XXL":
          if (sizeQuantity[4] == -1) {
            sizeQuantity[4] = 0;
            QuantityPanel.add(Panel, gbc);
            repaint();
            revalidate();
            SizeQuantity.getDocument().addDocumentListener(SaveValueAction(4, SizeQuantity));
          }
          break;
        default:
          break;
      }

    }

  };

  private ActionListener SaveAction = new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {
      for (int i = 0; i < sizeQuantity.length; i++) {
        System.out.println(sizeQuantity[i]);
      }
    }

  };

  private DocumentListener SaveValueAction(final int value, final JTextField text) {
    return new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        sizeQuantity[value] = Integer.parseInt(text.getText().toString());
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
      }
    };
  }
}

package com.clothingstore.gui.components;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.event.*;

public class AddProduct extends JFrame {

  String[] name = { "Name", "Category", "Gender", "Price", "Origin Price", "Description" };
  String[] size = {"S", "M", "L", "XL", "XXL"};
  ArrayList<String> sizeName = new ArrayList<>(Arrays.asList( "S", "M", "L", "XL", "XXL" ));
  ArrayList<Integer> sizeQuantity = new ArrayList<>(Arrays.asList(-1, -1, -1, -1, -1));

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
    NameLabel = new JLabel("New Product");
    ImagePanel = new JPanel();
    Image = new JLabel();
    Buttons = new JPanel();
    ButtonBack = new JButton("Back");
    ButtonSave = new JButton("Save");
    ChooseImage = new JPanel();
    LinkImage = new JTextField();
    ButtonImage = new JButton();
    SizePanel = new JPanel();
    SizeChoose = new JComboBox<>();
    QuantityPanel = new JPanel();
    ButtonNewSize = new JButton("New");

    setSize(800, 500);
    setPreferredSize(new Dimension(800, 500));
    setLayout(new BorderLayout());

    add(NameLabel, BorderLayout.NORTH);

    Panel.setLayout(new BorderLayout());

    Content.setLayout(new GridLayout(0, 1));
    for (int i = 0; i < 6; i++) {
      JPanel IndexPanel = new JPanel();
      IndexPanel.setLayout(new BorderLayout());
      IndexPanel.setPreferredSize(new Dimension(40, 80));
      IndexPanel.setBorder(BorderFactory.createEmptyBorder(5, 40, 15, 45));

      JLabel Name = new JLabel(name[i]);
      JLabel Invalid = new JLabel("No");
      JTextField Value = new JTextField();

      Name.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 45));
      IndexPanel.add(Name, BorderLayout.NORTH);

      Value.setPreferredSize(new Dimension(150, 35));
      IndexPanel.add(Value, BorderLayout.CENTER);
      Value.addFocusListener(CheckInvalid(i,Invalid));

      Invalid.setForeground(Color.RED);
      IndexPanel.add(Invalid, BorderLayout.SOUTH);

      Content.add(IndexPanel);
    }
    Panel.add(Content, BorderLayout.CENTER);

    SizePanel.setLayout(new GridBagLayout());

    SizeChoose.setModel(new DefaultComboBoxModel<>(size));
    SizeChoose.addActionListener(ChooseSizeAction);
    SizePanel.add(SizeChoose);

    ButtonNewSize.addActionListener(ChooseNewSize);
    SizePanel.add(ButtonNewSize);
    
    QuantityPanel.setLayout(new GridLayout(0, 1));
    SizePanel.add(QuantityPanel);
    
    Panel.add(SizePanel, BorderLayout.SOUTH);

    Scroll.setViewportView(Panel);
    add(Scroll, BorderLayout.CENTER);

    ImagePanel.setLayout(new BorderLayout());
    ImagePanel.setPreferredSize(new Dimension(250, 480));
    ImagePanel.setBorder(BorderFactory.createEmptyBorder(25, 15, 5, 15));

    ImageIcon originalIcon = new ImageIcon(getClass().getResource("/config/image/Ao/Nam/Hoodie/AoHoodieB.png"));
    Image originalImage = originalIcon.getImage();
    Image scaledImage = originalImage.getScaledInstance(220, 290, java.awt.Image.SCALE_SMOOTH);
    ImageIcon scaledIcon = new ImageIcon(scaledImage);
    Image.setIcon(scaledIcon);
    ImagePanel.add(Image, BorderLayout.NORTH);

    ButtonImage.addActionListener(ChooseImageAction);

    ChooseImage.setLayout(new BorderLayout());
    ChooseImage.add(LinkImage, BorderLayout.CENTER);
    ChooseImage.add(ButtonImage, BorderLayout.EAST);
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
  private JLabel NameLabel;
  private JPanel ImagePanel;
  private JLabel Image;
  private JPanel Buttons;
  private JButton ButtonBack;
  private JButton ButtonSave;
  private JPanel ChooseImage;
  private JTextField LinkImage;
  private JButton ButtonImage;
  private JComboBox<String> SizeChoose;
  private JPanel QuantityPanel;
  private JButton ButtonNewSize;


  // choose size
  public ActionListener ChooseSizeAction = new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {
      JPanel Panel = new JPanel();
      Panel.setLayout(new GridBagLayout());
      Panel.setPreferredSize(new Dimension(170, 35));

      GridBagConstraints gbc = new GridBagConstraints();
      gbc.ipadx = 20;
      gbc.fill = GridBagConstraints.LINE_START;

      JLabel SizeName = new JLabel(SizeChoose.getSelectedItem().toString() + ": amount is");
      SizeName.setFont(new Font("Segoe UI", 0, 14));
      Panel.add(SizeName, gbc);

      JTextField SizeQuantity = new JTextField();
      SizeQuantity.setPreferredSize(new Dimension(35, 25));
      SizeQuantity.setText("0");
      Panel.add(SizeQuantity);

      switch (SizeChoose.getSelectedItem().toString()) {
        case "S":
          if (sizeQuantity.get(0) == -1) {
            QuantityPanel.add(Panel, gbc);
            sizeQuantity.set(0, 0);
            repaint();
            revalidate();
            SizeQuantity.getDocument().addDocumentListener(SizeValueAction(0, SizeQuantity));
          }
          break;
        case "M":
          if (sizeQuantity.get(1) == -1) {
            sizeQuantity.set(1, 0);
            QuantityPanel.add(Panel, gbc);
            repaint();
            revalidate();
            SizeQuantity.getDocument().addDocumentListener(SizeValueAction(1, SizeQuantity));
          }
          break;
        case "L":
          if (sizeQuantity.get(2) == -1) {
            sizeQuantity.set(2, 0);
            QuantityPanel.add(Panel, gbc);
            repaint();
            revalidate();
            SizeQuantity.getDocument().addDocumentListener(SizeValueAction(2, SizeQuantity));
          }
          break;
        case "XL":
          if (sizeQuantity.get(3)== -1) {
            sizeQuantity.set(3, 0);
            QuantityPanel.add(Panel, gbc);
            repaint();
            revalidate();
            SizeQuantity.getDocument().addDocumentListener(SizeValueAction(3, SizeQuantity));
          }
          break;
        case "XXL":
          if (sizeQuantity.get(4) == -1) {
            sizeQuantity.set(4, 0);
            QuantityPanel.add(Panel, gbc);
            repaint();
            revalidate();
            SizeQuantity.getDocument().addDocumentListener(SizeValueAction(4, SizeQuantity));
          }
          break;
        default:
          break;
      }

    }

  };

  // choose new size
  private ActionListener ChooseNewSize = new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {
      JFrame frame = new JFrame("New Size");
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.setLocationRelativeTo(null);
      frame.setSize(350, 120);
      frame.setLayout(new GridBagLayout());
      JTextField Name = new JTextField();
      Name.setPreferredSize(new Dimension(50,20));

      JButton Button = new JButton("Save");
      Button.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
          JPanel Panel = new JPanel();
          Panel.setLayout(new GridBagLayout());
          Panel.setPreferredSize(new Dimension(170, 35));

          GridBagConstraints gbc = new GridBagConstraints();
          gbc.ipadx = 20;
          gbc.fill = GridBagConstraints.LINE_START;

          JLabel SizeName = new JLabel( Name.getText()+ ": amount is");
          SizeName.setFont(new Font("Segoe UI", 0, 14));
          Panel.add(SizeName, gbc);

          JTextField SizeQuantity = new JTextField();
          SizeQuantity.setPreferredSize(new Dimension(35, 25));
          SizeQuantity.setText("0");
          Panel.add(SizeQuantity);
          QuantityPanel.add(Panel, gbc);
          sizeQuantity.add(0);
          sizeName.add(Name.getText());
          revalidate();
          repaint();
          frame.dispose();
        }
        
      });
      frame.add(Name);
      frame.add(Button);

      frame.setVisible(true);
    }
    
  };
  // choose image 
  private ActionListener ChooseImageAction = new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {
      JFrame frame = new JFrame("File Explorer Example");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(400, 200);
      JFileChooser fileChooser = new JFileChooser();
      int result = fileChooser.showOpenDialog(frame);
                
      if (result == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fileChooser.getSelectedFile();
          // ex: set image in panel
          ImageIcon originalIcon = new ImageIcon(getClass().getResource("/config/image/Ao/Nam/Hoodie/AoHoodieA.png"));
          Image originalImage = originalIcon.getImage();
          Image scaledImage = originalImage.getScaledInstance(220, 290, java.awt.Image.SCALE_SMOOTH);
          ImageIcon scaledIcon = new ImageIcon(scaledImage);
          Image.setIcon(scaledIcon);
        }
        revalidate();
        repaint();
    }
    
  };

  // save
  private ActionListener SaveAction = new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {
      for (int i = 0; i < sizeQuantity.size(); i++) {
        System.out.println(sizeName.get(i));
        System.out.println(sizeQuantity.get(i));
      }
    }

  };

  // save size quantity
  private DocumentListener SizeValueAction(final int value, final JTextField text) {
    return new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        sizeQuantity.set(value, Integer.parseInt(text.getText().toString()));
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
      }
    };
  }

  // check invalid 
  private FocusListener CheckInvalid(int i, JLabel invLabel){
    return new FocusListener() {

      @Override
      public void focusGained(FocusEvent e) {
      }

      @Override
      public void focusLost(FocusEvent e) {
        switch (i) {
          case 0:
            // check name and set text for Invalid
            invLabel.setText("ok");
            revalidate();
            repaint();
            break;
          case 1:
            //check category
            break;
          case 2:
            //check gender
            break;
          case 3:
            //check price
            break;
          case 4:
            //check origin price
            break;
          case 5:
            // check description
            break;
          default:
            break;
        }
      }
    };
  }
}

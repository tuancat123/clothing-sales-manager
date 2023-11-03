package com.clothingstore.gui.components;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import javax.swing.*;

public class AddProduct extends JFrame {

    String[] name = {"Name", "Category", "Gender", "Price", "Origin Price", "Description"};
    String[] size = {"S", "M","L", "XL", "XXL"};
    int[] sizeQuantity = {1,1,1,1,1};    
    public AddProduct(){
        initComponents();
        setVisible(true);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    public void initComponents(){
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
        SizePanel= new JPanel();
        Size= new JComboBox<>();
        QuantityPanel = new JPanel();

        setSize(800,500);
        setPreferredSize(new Dimension(800,500));
        setLayout(new BorderLayout());
        
        add(NameFrame, BorderLayout.NORTH);

        Panel.setLayout(new BorderLayout());

        Content.setLayout(new GridLayout(0,1));
        for(int i = 0; i< 6; i++){
            JPanel Panel = new JPanel();
            Panel.setLayout(new BorderLayout());
            Panel.setPreferredSize(new Dimension(40,80));
            Panel.setBorder(BorderFactory.createEmptyBorder(5, 40, 15, 45));

            JLabel Name = new JLabel(name[i]);
            Name.setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 45));
            Panel.add(Name, BorderLayout.NORTH);

            JTextField Value = new JTextField();
            Value.setPreferredSize(new Dimension(150,35));
            Panel.add(Value, BorderLayout.CENTER);

            JLabel Invalid = new JLabel("No");
            Invalid.setForeground(Color.RED);
            Panel.add(Invalid, BorderLayout.SOUTH);
            
            Content.add(Panel);
        }
        Panel.add(Content, BorderLayout.CENTER);
        
        SizePanel.setLayout(new BorderLayout());
        
        Size.setModel(new DefaultComboBoxModel<>(new String[] { "S", "M", "L", "XL" }));
        Size.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel Panel = new JPanel();
                Panel.setLayout(new GridBagLayout());

                JLabel SizeName = new JLabel(Size.getSelectedItem().toString());
                Panel.add(SizeName);

                JTextField SizeQuantity = new JTextField();
                Panel.add(SizeQuantity);
                QuantityPanel.add(Panel);
                repaint();
                revalidate();
            }
            
        });
        SizePanel.add(Size, BorderLayout.NORTH);
        Panel.add(SizePanel, BorderLayout.SOUTH);
                
        QuantityPanel.setLayout(new GridLayout(0,1));
        SizePanel.add(QuantityPanel, BorderLayout.CENTER);

        Scroll.setViewportView(Panel);
        add(Scroll, BorderLayout.CENTER);
        
        ImagePanel.setLayout(new BorderLayout());
        ImagePanel.setPreferredSize(new Dimension(250,250));
        ImagePanel.setBorder(BorderFactory.createEmptyBorder(25,15,5,15));

        Image.setIcon(new ImageIcon(getClass().getResource("/config/image/Ao/Nam/Hoodie/AoHoodieA.png")));
        Image.setBackground(Color.RED);
        Image.setPreferredSize(new Dimension(180, 300));
        ImagePanel.add(Image, BorderLayout.NORTH);

        ChooseImage.setLayout(new BorderLayout());
        ChooseImage.add(LinkImage, BorderLayout.CENTER);
        ChooseImage.add(Choose, BorderLayout.EAST);
        ImagePanel.add(ChooseImage, BorderLayout.SOUTH);

        add(ImagePanel, BorderLayout.WEST);

        Buttons.setPreferredSize(new Dimension(60,60));
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

    public static void main(String[] args) {
        new AddProduct();
    }
}

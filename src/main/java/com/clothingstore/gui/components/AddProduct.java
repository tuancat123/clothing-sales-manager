package com.clothingstore.gui.components;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class AddProduct extends JFrame {

    ArrayList<String> name = new ArrayList<String>(){{
        add("Name");
        add("Value");
    }};

    ArrayList<String> invalid = new ArrayList<String>(){{
        add("Not null");
        add("False");
    }};
    
    public AddProduct(){
        initComponents();
        setVisible(true);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    public void initComponents(){
        Scroll = new JScrollPane();
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

        setSize(800,500);
        setPreferredSize(new Dimension(800,500));
        setLayout(new BorderLayout());
        
        add(NameFrame, BorderLayout.NORTH);

        Content.setLayout(new GridLayout(0,1));
        for(int i = 0; i< 5; i++){
            JPanel Panel = new JPanel();
            Panel.setLayout(new BorderLayout());
            Panel.setPreferredSize(new Dimension(40,60));
            Panel.setBorder(BorderFactory.createEmptyBorder(5, 40, 15, 45));

            JLabel Name = new JLabel("Name");
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
        Scroll.setViewportView(Content);
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

    public static void main(String[] args) {
        new AddProduct();
    }
}

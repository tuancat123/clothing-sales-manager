package com.clothingstore.gui.components;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.print.DocFlavor.READER;
import javax.swing.*;

public class Control extends JPanel {


	public Control() {
		initComponents();
	}

	private void initComponents() {

		IncrButton = new JLabel();
		ReduceButton = new JLabel();
		Value = new JLabel();

		setBorder(BorderFactory.createEtchedBorder());
		setLayout(new BorderLayout());

		IncrButton.setText("+");
		IncrButton.setPreferredSize(new Dimension(16, 16));
		IncrButton.setFont(new Font("Segoe UI", 1, 16));
		IncrButton.setHorizontalAlignment(SwingConstants.CENTER);
        IncrButton.setVerticalAlignment(SwingConstants.CENTER);
		IncrButton.addMouseListener(increaseBtn);
		add(IncrButton, BorderLayout.EAST);

		ReduceButton.setText(" -");
		ReduceButton.setPreferredSize(new Dimension(16, 16));
		ReduceButton.setFont(new Font("Segoe UI", 1, 16));
		ReduceButton.setHorizontalAlignment(SwingConstants.CENTER);
        ReduceButton.setVerticalAlignment(SwingConstants.CENTER);
		ReduceButton.addMouseListener(decreaseBtn);
		add(ReduceButton, BorderLayout.WEST);

		Value.setHorizontalAlignment(SwingConstants.CENTER);
		Value.setText("1");
		Value.setFont(new Font("Segoe UI", 0, 14));

		add(Value, BorderLayout.CENTER);



	}

	private JLabel IncrButton;
	private JLabel ReduceButton;
	private JLabel Value;

	public MouseListener increaseBtn = new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseClicked(MouseEvent e) {
			int quantity = Integer.parseInt(Value.getText()+"");
			quantity++;
			Value.setText(quantity+"");
		}
	};

	public MouseListener decreaseBtn = new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseClicked(MouseEvent e) {
			int quantity = Integer.parseInt(Value.getText()+"");
			if(quantity == 0) {
				quantity = 0;
			}else {
				quantity--;
			}
			Value.setText(quantity+"");
		}
	};
}
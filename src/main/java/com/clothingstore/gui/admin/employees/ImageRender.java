package com.clothingstore.gui.admin.employees;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ImageRender extends DefaultTableCellRenderer{

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (value instanceof String && ((String) value).endsWith(".png") || value instanceof String && ((String) value).endsWith(".jpg")) {
            // tao hinh anh tu chuoi string
            ImageIcon icon = new ImageIcon((String) value);
            // dat bieu tuong
            setIcon(icon);
            setText(null);
        } else {
            // neu ko phai bieu tuong thi de mac dinh
           return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
        return this;
	}

}
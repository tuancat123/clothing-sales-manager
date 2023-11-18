package com.clothingstore.gui.components.customerList;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomerImageRender extends DefaultTableCellRenderer {

  @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
      int row, int column) {
    if (value instanceof String && ((String) value).endsWith(".png")
        || value instanceof String && ((String) value).endsWith(".jpg")) {
      ImageIcon icon = new ImageIcon((String) value);
      setIcon(icon);
      setText(null);
    } else {
      return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
    return this;
  }

}
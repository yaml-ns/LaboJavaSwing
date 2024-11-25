package com.compagnie.aerienne.interface_graphique.composants.model;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ActionButtonRendrer extends JPanel implements TableCellRenderer {

    private final JButton btnEdit = new JButton();
    private final JButton btnDelete = new JButton();

    public ActionButtonRendrer() {

        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        btnEdit.setPreferredSize(new Dimension(25,25));
        btnDelete.setPreferredSize(new Dimension(25,25));
        btnEdit.setIcon(new FlatSVGIcon("icons/edit.svg"));
        btnDelete.setIcon(new FlatSVGIcon("icons/trash.svg"));

//        btnEdit.setBackground(new Color(0, 94, 183));
        btnEdit.setBackground(new Color(29, 29, 70));
        btnDelete.setBackground(new Color(100, 1, 1));

        btnEdit.setFocusable(false);
        btnDelete.setFocusable(false);

        add(btnEdit);
        add(btnDelete);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        if (isSelected) {
            setBackground(table.getSelectionBackground());
        } else {
            setBackground(table.getBackground());
        }
        return this;
    }

}

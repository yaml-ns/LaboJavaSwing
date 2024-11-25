package com.compagnie.aerienne.interface_graphique.composants.model;


import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionButtonEditor extends AbstractCellEditor implements TableCellEditor{

    private final JPanel panel = new JPanel();
    private ActionListener onUpdateListener;
    private ActionListener onDeleteListener;

    public ActionButtonEditor(){
        ToolTipManager.sharedInstance().setEnabled(true);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,2,2));
        Dimension btnDimension = new Dimension(25,25);
        JButton btnUpdate = new JButton();
        btnUpdate.setToolTipText("Modifier ce vol !");
        btnUpdate.setPreferredSize(btnDimension);
        JButton btnDelete = new JButton();
        btnDelete.setToolTipText("Supprimer ce vol !");
        btnDelete.setPreferredSize(btnDimension);
        btnUpdate.setIcon(new FlatSVGIcon("icons/edit.svg"));
        btnDelete.setIcon(new FlatSVGIcon("icons/trash.svg"));

        btnUpdate.setBackground(new Color(29, 29, 70));
        btnDelete.setBackground(new Color(100, 1, 1));

        btnUpdate.setFocusable(false);
        btnDelete.setFocusable(false);


        btnUpdate.addActionListener(e -> {
            fireEditingStopped();
            if (onUpdateListener != null) {
                onUpdateListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "update"));
            }
        });


        btnDelete.addActionListener(e -> {
            fireEditingStopped();
            if (onDeleteListener != null) {
                onDeleteListener.actionPerformed(new ActionEvent(
                        this,
                        ActionEvent.ACTION_PERFORMED,
                        "delete"));
            }
        });

        panel.add(btnUpdate);
        panel.add(btnDelete);

    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (isSelected){
             panel.setBackground(table.getSelectionBackground());
        }else{
            panel.setBackground(table.getBackground());
        }

        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }


    public void setOnUpdateListener(ActionListener onUpdateListener) {
        this.onUpdateListener = onUpdateListener;
    }

    public void setOnDeleteListener(ActionListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }


}

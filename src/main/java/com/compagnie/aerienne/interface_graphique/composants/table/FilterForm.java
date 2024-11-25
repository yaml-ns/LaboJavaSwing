package com.compagnie.aerienne.interface_graphique.composants.table;

import com.compagnie.aerienne.interface_graphique.AppColors;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class FilterForm extends JPanel {

    JTextField idVolField = new JTextField(10);
    JTextField destinationField = new JTextField(10);
    JTextField datedField = new JTextField(10);
    JTextField resevField = new JTextField(10);

    JButton resetButton;
    public FilterForm(TableRowSorter<TableModel> sorter){

        initForm();

        DocumentListener listener = new FilterListener(
                sorter,
                idVolField,
                destinationField,
                datedField,
                resevField
        );

        idVolField.getDocument().addDocumentListener(listener);
        destinationField.getDocument().addDocumentListener(listener);
        datedField.getDocument().addDocumentListener(listener);
        resevField.getDocument().addDocumentListener(listener);

        resetButton.addActionListener(e -> {
            idVolField.setText("");
            destinationField.setText("");
            datedField.setText("");
            resevField.setText("");
        });

    }

    private void initForm(){

        setBackground(AppColors.BG_DARK);
        setBorder(new CompoundBorder(
                new LineBorder(AppColors.BG_LIGHT),
                new EmptyBorder(10,10,10,10)
        ));
        resetButton = new JButton("Réinitialiser");
        resetButton.setBackground(AppColors.BG_LIGHT);
        idVolField.setBackground(AppColors.BG_LIGHT);
        destinationField.setBackground(AppColors.BG_LIGHT);
        datedField.setBackground(AppColors.BG_LIGHT);
        resevField.setBackground(AppColors.BG_LIGHT);

        setLayout(new GridLayout(3, 4, 5, 5));
        add(new JLabel("ID Vol :"));
        add(idVolField);
        add(new JLabel("Destination :"));
        add(destinationField);
        add(new JLabel("Date :"));
        add(datedField);
        add(new JLabel("Réservations :"));
        add(resevField);
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        add(resetButton);
    }
}

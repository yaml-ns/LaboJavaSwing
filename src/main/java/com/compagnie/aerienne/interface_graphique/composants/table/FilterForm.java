package com.compagnie.aerienne.interface_graphique.composants.table;

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
    public FilterForm(TableModel tableModel){

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableModel);
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

        setBackground(new Color(17, 17, 33));
        setBorder(new CompoundBorder(
                new LineBorder(new Color(28, 28, 51)),
                new EmptyBorder(10,10,10,10)
        ));
        resetButton = new JButton("Réinitialiser");

        idVolField.setBackground(new Color(28, 28, 51));
        destinationField.setBackground(new Color(28, 28, 51));
        datedField.setBackground(new Color(28, 28, 51));
        resevField.setBackground(new Color(28, 28, 51));

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

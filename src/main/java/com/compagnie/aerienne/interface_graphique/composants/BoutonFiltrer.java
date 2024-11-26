package com.compagnie.aerienne.interface_graphique.composants;

import com.compagnie.aerienne.interface_graphique.AppColors;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoutonFiltrer extends JToggleButton {

    ActionListener onToggleListener;
    public BoutonFiltrer() {
        setText("Filtrer les vols");
        FlatSVGIcon FilterIcon = new FlatSVGIcon(getClass().getResource("/icons/filter.svg"));
        setIcon(FilterIcon);
        setPreferredSize(new Dimension(150, 35));
        setBackground(AppColors.BG_DARK);
        setIconTextGap(10);
        setSelected(true);
        addActionListener(e->{
            if (this.onToggleListener != null){
                this.onToggleListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "toggle"));
            }
        });
    }

    public void setOnToggleListener(ActionListener onToggleListener) {
        this.onToggleListener = onToggleListener;
    }
}

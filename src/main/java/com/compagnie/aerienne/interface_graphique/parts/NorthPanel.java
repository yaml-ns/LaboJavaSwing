package com.compagnie.aerienne.interface_graphique.parts;

import com.compagnie.aerienne.interface_graphique.composants.BoutonAjouter;
import com.compagnie.aerienne.interface_graphique.composants.FormulaireVol;
import com.compagnie.aerienne.interface_graphique.composants.InfoPanel;

import javax.swing.*;
import java.awt.*;

public class NorthPanel extends JPanel {


    public NorthPanel(FormulaireVol formulaireAjout){
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 10));
        setBackground(new Color(28, 28, 51));

        BoutonAjouter btnAjouter = new BoutonAjouter();

        btnAjouter.addActionListener(e -> {
            formulaireAjout.setVisible(true);
        });

        InfoPanel infosPanel = InfoPanel.getInstance();
        add(infosPanel,BorderLayout.CENTER);
        add(btnAjouter,BorderLayout.EAST);
    }
}

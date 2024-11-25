package com.compagnie.aerienne.interface_graphique.composants.table;

import com.compagnie.aerienne.interface_graphique.InterfacePrincipale;
import com.compagnie.aerienne.interface_graphique.composants.FormulaireVol;
import com.compagnie.aerienne.interface_graphique.composants.InfoPanel;
import com.compagnie.aerienne.interface_graphique.composants.model.ActionButtonEditor;
import com.compagnie.aerienne.interface_graphique.composants.model.ActionButtonRendrer;
import com.compagnie.aerienne.interface_graphique.parts.SouthPanel;
import com.compagnie.aerienne.modele.Vol;
import com.compagnie.aerienne.interface_graphique.composants.model.VolTableModel;
import com.compagnie.aerienne.service.GestionVolService;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class VolTable{
    private final JTable table;
    private final GestionVolService volManager;
    private final VolTableModel tableModel;
    private final FilterForm filters;
    TableRowSorter<TableModel> sorter;
    public VolTable(List<Vol> liste) throws IOException {

        this.volManager = new GestionVolService();
        this.tableModel = new VolTableModel(liste);
        this.table = new JTable(this.tableModel);
        this.filters = new FilterForm(this.tableModel);
        this.sorter = new TableRowSorter<>(tableModel);

        initTable();

        ActionButtonEditor tableCellEditor = new ActionButtonEditor();
        ActionButtonRendrer actionButtonRendrer = new ActionButtonRendrer();

        this.table.getColumnModel().getColumn(4).setCellEditor(tableCellEditor);
        this.table.getColumnModel().getColumn(4).setCellRenderer(actionButtonRendrer);

        tableCellEditor.setOnUpdateListener(e->{
            int row = table.getSelectedRow();
            Vol vol = tableModel.getVolAt(row);
            Window w = SwingUtilities.getWindowAncestor(table);

            if (w instanceof JFrame){
                InterfacePrincipale ip = (InterfacePrincipale) w;
                FormulaireVol formulaire = new FormulaireVol(ip,vol);
                formulaire.setVisible(true);
            }


        });

        tableCellEditor.setOnDeleteListener(e -> {
            int row = table.getSelectedRow();
            Vol vol = tableModel.getVolAt(row);
            int confirmation = JOptionPane.showConfirmDialog(
                    null,
                    """
                    Voulez vous vraiment supprimer le vol %d
                    à destination de %s?""".formatted(
                                    vol.getIdVol(),
                                    vol.getDestination()
                    )
            );

            if (confirmation == 0){
                try {
                    volManager.deleteVol(vol.getIdVol());
                    List<Vol> nouvelleListe = volManager.getAll();
                    this.updateData(nouvelleListe);
                    InfoPanel.getInstance().setOperationResult("Vol supprimé avec succès", InfoPanel.messageType.SUCCESS);
                    SouthPanel.getInstance().setTotalVols(nouvelleListe.size());
                } catch (IOException ex) {
                    InfoPanel.getInstance().setOperationResult(
                            "Une erreur s'est produite pendant l'opération!",
                            InfoPanel.messageType.ERROR);

                }

            }

        });


    }

    public JPanel getTable(){

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(new Color(28, 28, 51));
        JScrollPane sp = new JScrollPane(table,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        sp.setBackground(new Color(28, 28, 51));
        sp.setBorder(BorderFactory.createLineBorder(new Color(24, 24, 51)));
        sp.setBorder(new EmptyBorder(10,0,0,0));
        tablePanel.add(filters,BorderLayout.NORTH);
        tablePanel.add(sp,BorderLayout.CENTER);
        return tablePanel;
    }

    private void initTable(){

        this.table.setRowSorter(sorter);
        this.table.getColumnModel().getColumn(4).setMaxWidth(70);
        this.table.setRowHeight(30);
        this.table.setShowGrid(false);
        this.table.setShowVerticalLines(false);
        this.table.setFocusable(false);
        this.table.setBackground(new Color(17, 17, 33));
        this.table.setSelectionBackground(new Color(28, 28, 51));

        JTableHeader header = this.table.getTableHeader();
        header.setBackground(new Color(17, 17, 33));
    }

    public void updateData(List<Vol> newVols) {
        tableModel.updateData(newVols);
    }

}

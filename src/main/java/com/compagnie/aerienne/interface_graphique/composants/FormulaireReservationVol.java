package com.compagnie.aerienne.interface_graphique.composants;

import com.compagnie.aerienne.Application;
import com.compagnie.aerienne.interface_graphique.AppColors;
import com.compagnie.aerienne.interface_graphique.InterfacePrincipale;
import com.compagnie.aerienne.modele.Vol;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Objects;

public class FormulaireReservationVol extends JDialog {

    private final Vol vol;
    private final JLabel spinner;
    private JTextField nbrReservations;
    private final InterfacePrincipale ip;
    JLabel errorMessage;
    public FormulaireReservationVol(InterfacePrincipale ip, Vol vol){

        super(ip, ModalityType.APPLICATION_MODAL);
        this.ip = ip;
        this.vol = vol;

        spinner = new Chargement("Chargement ...");

        setTitle("Réservation de vol");
        setIconImage(new ImageIcon(Objects.requireNonNull(Application.class
                .getResource("/icons/air_relax.png")))
                .getImage()
        );
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.errorMessage = new JLabel();
        errorMessage.setForeground(AppColors.FG_DANGER);
        topPanel.add(errorMessage);
        topPanel.add(spinner);

        add(topPanel, BorderLayout.NORTH);
        JPanel form = new JPanel(new GridBagLayout());

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setFields(form);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton btnEnregistrer = new BoutonEnregistrer();
        JButton btnReset = new JButton("Effacer");

        btnReset.setPreferredSize(new Dimension(100,25));
        btnReset.setBorder(new LineBorder(new Color(0,0,0,0),1));


        btnEnregistrer.addActionListener(e -> {
            spinner.setVisible(true);
            errorMessage.setText("");
            process(vol);
        });

        if (vol.getReserv() == 400 ){
            this.nbrReservations.setEnabled(false);
            this.errorMessage.setText("Désolé ! Ce vol est complet. Veuillez choisir un autre.");
            spinner.setVisible(false);
            btnEnregistrer.setEnabled(false);
            btnReset.setEnabled(false);
        }
        btnReset.addActionListener(e -> this.resetForm());

        bottomPanel.add(btnEnregistrer);
        bottomPanel.add(btnReset);

        add(bottomPanel, BorderLayout.SOUTH);

        setMinimumSize(new Dimension(400,250));
        pack();
        setLocationRelativeTo(ip);

    }


    private void setFields(JPanel form) {

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        String label = """
                       <html>
                       <tr><td>ID:</td><td> %d</td></tr>
                       <tr><td>Destination :</td><td>    %s</td></tr>
                       <tr><td>Date départ :</td><td>    %s</td></tr>
                       <tr><td>Res. Actuelles: </td><td> %d</td></tr>
                       <tr><td>Place dispos: </td><td> %d </td></tr>
                       <br>
                      
                       Nombre de place à réserver :
                       </html>
                       """
                .formatted(
                vol.getIdVol(),
                vol.getDestination(),
                vol.getDated().toString(),
                vol.getReserv(),
                400 - vol.getReserv()
                );

        JLabel nbrReservationLabel = new JLabel(label);
        form.add(nbrReservationLabel, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;

        this.nbrReservations= new JTextField(20);
        form.add(this.nbrReservations, gbc);
        add(form,BorderLayout.CENTER);

    }

    private void resetForm(){
        this.nbrReservations.setText("");
        this.errorMessage.setText("");
    }


    private void process(Vol vol){



        if (Integer.parseInt(nbrReservations.getText())> (400 - vol.getReserv())){
            this.errorMessage.setText("Places disponibles insuffisantes pour ce nombre de réservation");
            spinner.setVisible(false);
        }else{
            spinner.setVisible(false);
            this.errorMessage.setText("");
//            try {
//                GestionVolService volManager = new GestionVolService();
//                String infoMessage;
//                if (validatedVol.getIdVol() == 0){
//                    validatedVol.setIdVol(volManager.getNextId());
//                    volManager.addVol(validatedVol);
//                    infoMessage = """
//                                       Le Vol %d à destination de %s a été ajouté avec succès !"""
//                            .formatted(
//                            validatedVol.getIdVol(),
//                            validatedVol.getDestination()
//                    );
//
//                    SouthPanel.getInstance().setTotalVols(volManager.getAll().size());
//
//                }else{
//                    volManager.updateVol(validatedVol);
//                    infoMessage = """
//                                       Le Vol %d à destination de %s a été modifié avec succès !"""
//                            .formatted(
//                            validatedVol.getIdVol(),
//                            validatedVol.getDestination()
//                    );
//                }
//                ip.rafraichirTable(volManager.getAll());
//                this.resetForm();
//                dispose();
//                InfoPanel.getInstance().setOperationResult(infoMessage, InfoPanel.messageType.SUCCESS);
//            }catch (Exception exception){
//                errorMessage.setText("Une erreur s'est produite pendant l'ajout.");
//            }

        }
    }




}

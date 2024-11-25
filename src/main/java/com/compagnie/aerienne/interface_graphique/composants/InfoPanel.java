package com.compagnie.aerienne.interface_graphique.composants;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class InfoPanel extends JPanel {

    public enum messageType{
        ERROR, SUCCESS
    }
    private static InfoPanel instance;
    JLabel operationResult = new JLabel();
    public InfoPanel(){
        setBackground(new Color(28, 28, 51));
        add(this.operationResult,BorderLayout.CENTER);

    }

    public static InfoPanel getInstance() {
        if (instance == null) {
            instance = new InfoPanel();
        }
        return instance;
    }

    public void setOperationResult(String message, messageType type) {

        operationResult.setText(message);
        if (type == messageType.SUCCESS){
            operationResult.setBorder(new CompoundBorder(
                    new MatteBorder(0,0,1,0,new Color(131,183,0)),
                    new EmptyBorder(3,0,3,0)
            ));

            operationResult.setIcon(new FlatSVGIcon(getClass().getResource("/icons/check-square.svg")));
            operationResult.setIconTextGap(5);
        }else {
            operationResult.setBorder(new CompoundBorder(
                    new MatteBorder(0,0,1,0,new Color(105, 0,0)),
                    new EmptyBorder(3,0,3,0)
            ));

            operationResult.setIcon(new FlatSVGIcon(getClass().getResource("/icons/x-square.svg")));
            operationResult.setIconTextGap(5);
        }
        operationResult.setVisible(true);
        Timer timer = new Timer(3500,e -> {
            operationResult.setVisible(false);
        });
        timer.setRepeats(false);
        timer.start();

    }
}

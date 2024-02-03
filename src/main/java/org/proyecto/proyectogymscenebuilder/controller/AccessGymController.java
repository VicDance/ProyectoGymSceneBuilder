package org.proyecto.proyectogymscenebuilder.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AccessGymController {
    @FXML
    Label labelAccessGym;

    @FXML
    Label labelMessageAccessGym;

    @FXML
    public void accessGym() {
        labelMessageAccessGym.setText("ACCESO CONCEDIDO");
        System.out.println("ACCESO CONCEDIDO");
        try {
            Thread.sleep(2000);
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package org.proyecto.proyectogymscenebuilder.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import static org.proyecto.proyectogymscenebuilder.controller.UserController.logged;
import static org.proyecto.proyectogymscenebuilder.controller.UserController.username;

public class CarritoController implements Initializable {
    @FXML
    Label carritoUsername;

    @FXML
    Button añadirCestaId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (logged) {
            carritoUsername.setText(carritoUsername.getText() + " " + username);
        }
    }
}

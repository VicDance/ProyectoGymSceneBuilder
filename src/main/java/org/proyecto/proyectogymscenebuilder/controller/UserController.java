package org.proyecto.proyectogymscenebuilder.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserController {
    //TODO: hacer que cuando se inicie sesion aparezca el nombre en la pantalla principal

    @FXML
    TextField usernameTextField;

    @FXML
    PasswordField passwordTextField;

    @FXML
    protected void login() {
        if (usernameTextField.getText().equals("VicDance") && passwordTextField.getText().equals("admin")) {
            Stage stage = (Stage) this.usernameTextField.getScene().getWindow();
            stage.close();
        } else {

        }
    }
}

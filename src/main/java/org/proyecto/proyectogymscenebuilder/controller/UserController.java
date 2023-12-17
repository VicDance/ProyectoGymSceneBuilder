package org.proyecto.proyectogymscenebuilder.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.proyecto.proyectogymscenebuilder.MainApplication;

import java.io.IOException;

public class UserController {
    //TODO: hacer que cuando se inicie sesion aparezca el nombre en la pantalla principal

    @FXML
    TextField usernameTextField;

    @FXML
    PasswordField passwordTextField;

    @FXML
    TextField usernameRegisterTextField;

    @FXML
    PasswordField passwordRegisterTextField;

    @FXML
    PasswordField repeatPasswordTextField;

    @FXML
    Label labelSuccessRegister;

    @FXML
    protected void login() {
        if (usernameTextField.getText().equals("VicDance") && passwordTextField.getText().equals("admin")) {
            Stage stage = (Stage) this.usernameTextField.getScene().getWindow();
            stage.close();
        } else {

        }
    }

    @FXML
    protected void toRegisterView() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("register-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void register() {
        if (!usernameRegisterTextField.getText().isEmpty() && !passwordRegisterTextField.getText().isEmpty() && !repeatPasswordTextField.getText().isEmpty()) {
            labelSuccessRegister.setText("Registro exitoso");
        }
    }
}

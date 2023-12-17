package org.proyecto.proyectogymscenebuilder.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.proyecto.proyectogymscenebuilder.MainApplication;
import org.proyecto.proyectogymscenebuilder.model.User;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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

    URL url = new URL("http://localhost:8080");
    HttpURLConnection con = null;

    public UserController() throws MalformedURLException {
    }

    @FXML
    protected void login() {
        if (usernameTextField.getText().equals("VicDance") && passwordTextField.getText().equals("admin")) {
            Stage stage = (Stage) this.usernameTextField.getScene().getWindow();
            stage.close();
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
    protected void register() throws IOException {
        if (!usernameRegisterTextField.getText().isEmpty() && !passwordRegisterTextField.getText().isEmpty() && !repeatPasswordTextField.getText().isEmpty()) {
            User user = new User();
            user.setUsername(usernameRegisterTextField.getText());
            user.setPassword(passwordRegisterTextField.getText());
            //User.builder().username(usernameRegisterTextField.getText()).password(passwordRegisterTextField.getText()).build();
            //createUser(user);
            labelSuccessRegister.setText("Registro exitoso");
        }
    }

    private void createUser(User user) throws IOException {
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
    }
}

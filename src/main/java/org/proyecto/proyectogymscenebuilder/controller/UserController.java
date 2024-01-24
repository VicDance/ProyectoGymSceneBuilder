package org.proyecto.proyectogymscenebuilder.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.proyecto.proyectogymscenebuilder.MainApplication;
import org.proyecto.proyectogymscenebuilder.connection.DatabaseConnection;
import org.proyecto.proyectogymscenebuilder.model.User;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;

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

    DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection connection = databaseConnection.getConnection();

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
    protected void register() {
        if (!usernameRegisterTextField.getText().isEmpty() && !passwordRegisterTextField.getText().isEmpty() && !repeatPasswordTextField.getText().isEmpty()) {
            User user = new User();
            user.setUsername(usernameRegisterTextField.getText());
            user.setPassword(passwordRegisterTextField.getText());
            createUser(user);
            labelSuccessRegister.setText("Registro exitoso");
        }
    }

    private User createUser(User user) {
        String query = "insert into user (username, password) values (?, ?)";
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, user.getUsername());
            preparedStmt.setString (2, user.getPassword());

            preparedStmt.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}

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
import org.proyecto.proyectogymscenebuilder.repository.UserRepository;
import java.io.IOException;
import java.sql.*;
import java.util.Optional;

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

    private final UserRepository userRepository = new UserRepository();

    public static boolean logged = false;
    public static String username = "";

    @FXML
    protected void login() throws SQLException {
        if (!usernameTextField.getText().isEmpty() && !passwordTextField.getText().isEmpty()) {
            Optional<User> user = userRepository.getUserByEmailAndPassword(usernameTextField.getText(), passwordTextField.getText());

            if (user.isPresent()) {
                logged = true;
                username = user.get().getUsername();
                System.out.println("logged");
            }
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
            try {
                userRepository.createUser(user);
            } catch (SQLException e) {
                System.out.println("Hubo un problema creando el usuario");
                e.printStackTrace();
            }
            labelSuccessRegister.setText("Registro exitoso");
        }
    }
}

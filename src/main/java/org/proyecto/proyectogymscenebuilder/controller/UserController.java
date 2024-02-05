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
import org.proyecto.proyectogymscenebuilder.repository.UserRepository;
import java.io.IOException;
import java.sql.*;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
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
        String enteredUsername = usernameTextField.getText();
        String enteredPassword = passwordTextField.getText();

        if (!enteredUsername.isEmpty() && !enteredPassword.isEmpty()) {
            Optional<User> user = userRepository.getUserByEmailAndPassword(enteredUsername, enteredPassword);

            if (user.isPresent()) {
                // Usuario encontrado, proceder con el inicio de sesión
                logged = true;
                UserController.setLoggedInUser(user.get()); // Actualiza el usuario logueado
                username = user.get().getUsername();
                System.out.println("logged");

                // Obtener la referencia al Stage actual y cerrarlo
                Stage currentStage = (Stage) usernameTextField.getScene().getWindow();
                currentStage.close();

                // Crear un cuadro de diálogo de información (Information)
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("¡Inicio de sesión exitoso!");
                alert.setHeaderText(null);
                alert.setContentText("Welcome! " + username);

                // Configurar el cuadro de diálogo para que se cierre automáticamente después de 3 segundos
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> alert.hide()));
                timeline.setCycleCount(1);
                timeline.play();

                // Mostrar el cuadro de diálogo
                alert.show();
            } else {
                // Usuario no encontrado, mostrar mensaje de datos incorrectos
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error de inicio de sesión");
                alert.setHeaderText(null);
                alert.setContentText("Usuario y/o contraseña incorrectos. Inténtelo de nuevo.");

                // Mostrar el cuadro de diálogo de error
                alert.showAndWait();
            }
        }else{
            // Campo usuario y/o contraseña vacios.
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error de inicio de sesión");
            alert.setHeaderText(null);
            alert.setContentText("Campo usuario y/o contraseña vacios.");

            // Mostrar el cuadro de diálogo de error
            alert.showAndWait();

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

    private static User loggedInUser;

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    // Método para actualizar el usuario logueado
    public static void setLoggedInUser(User user) {
        loggedInUser = user;
    }




    @FXML
    protected void register() {
        String username = usernameRegisterTextField.getText();
        String password = passwordRegisterTextField.getText();
        String repeatPassword = repeatPasswordTextField.getText();

        // Verificar que los campos no estén vacíos
        if (username.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
            showAlert("Por favor, complete todos los campos.");
            return;
        }

        // Verificar que las contraseñas coincidan
        if (!password.equals(repeatPassword)) {
            showAlert("Las contraseñas no coinciden. Vuelva a intentarlo.");
            return;
        }

        try {
            // Verificar si el nombre de usuario ya existe
            if (userRepository.isUsernameTaken(username)) {
                showAlert("Nombre de usuario ya tomado. Por favor, elija otro.");
                return;
            }

            // Crear el usuario si todas las validaciones pasan
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            try (Connection connection = DatabaseConnection.getConnection()) {
                // Desactivar autocommit al inicio del bloque
                connection.setAutoCommit(false);

                userRepository.createUser(user);
                System.out.println("Usuario creado correctamente");

                // Realizar commit manual
                connection.commit();

                // Cierra la ventana después de crear el usuario
                closeWindow();
            }

        } catch (SQLException e) {
            System.out.println("Hubo un problema creando el usuario");
            e.printStackTrace();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }





    private void closeWindow() {
        Stage stage = (Stage) usernameRegisterTextField.getScene().getWindow();
        stage.close();
    }





}

package org.proyecto.proyectogymscenebuilder.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.util.Duration;
import lombok.SneakyThrows;
import org.proyecto.proyectogymscenebuilder.model.User;
import org.proyecto.proyectogymscenebuilder.repository.UserRepository;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    // Agrega esta línea para tener una instancia de UserRepository
    private final UserRepository userRepository = new UserRepository();

    @FXML
    Label labelProfileUsername;

    @FXML
    Label labelProfileCreate;

    @FXML
    Label labelProfileLastAccess;

    @FXML
    Label labelProfileNumAccess;

    @FXML
    Button btnUnsubscribe;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadProfile();
    }

    private void loadProfile() throws SQLException {
        User user = UserController.getLoggedInUser();
        System.out.println(user.getUsername());

        labelProfileUsername.setText(labelProfileUsername.getText() + " " + user.getUsername());

        // Usa userRepository para obtener detalles del usuario
        Optional<User> userDetails = userRepository.getUserByUsername(user.getUsername());

        userDetails.ifPresent(userInfo -> {
            labelProfileCreate.setText(labelProfileCreate.getText() + " " + userInfo.getCreation_date());
            labelProfileLastAccess.setText(labelProfileLastAccess.getText() + " " + userInfo.getLast_access());
            labelProfileNumAccess.setText(labelProfileNumAccess.getText() + " " + userInfo.getNum_access());
        });
    }

    // Método para manejar la acción del botón "Darse de baja"
    @FXML
    private void unsubscribe() throws SQLException {
        User user = UserController.getLoggedInUser();

        // Mostrar el diálogo de confirmación
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmar baja");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("¿Está seguro de que desea darse de baja?");

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Lógica para eliminar el usuario
            userRepository.deleteUserByUsername(user.getUsername());

            // Mostrar la alerta de "Baja realizada"
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Baja realizada");
            alert.setHeaderText(null);
            alert.setContentText("¡Hasta pronto! Siempre serás bienvenido de vuelta. ¡Gracias por haber formado parte de nuestra comunidad!");

            // Configurar el cuadro de diálogo para que se cierre automáticamente después de 2 segundos
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> alert.hide()));
            timeline.setCycleCount(1);
            timeline.play();

            // Mostrar la alerta
            alert.show();

            // Cerrar la aplicación después de 0,5 segundos
            timeline.setOnFinished(event -> {
                try {
                    Thread.sleep(500);
                    System.exit(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}

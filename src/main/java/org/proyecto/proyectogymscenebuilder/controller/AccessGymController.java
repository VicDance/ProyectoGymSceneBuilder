package org.proyecto.proyectogymscenebuilder.controller;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.proyecto.proyectogymscenebuilder.MainApplication;
import org.proyecto.proyectogymscenebuilder.model.User;
import org.proyecto.proyectogymscenebuilder.repository.UserRepository;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.sql.Timestamp;




public class AccessGymController {

    private final UserRepository repository = new UserRepository();

    @FXML
    Label labelAccessGym;

    @FXML
    Label labelMessageAccessGym;

    @FXML
    Label labelProfile;

    @FXML
    Label labelCart;


    @FXML
    public void accessGym() throws SQLException {
        User user = UserController.getLoggedInUser();

        if (user.getUsername() != null) {
            // Obtener la fecha y hora actuales
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

            // Actualizar last_access con la fecha y hora actuales
            repository.updateLastAccessByUsername(user.getUsername(), currentTimestamp);

            // Incrementar el valor de num_access
            repository.incrementNumAccessByUsername(user.getUsername());

            // Obtener el valor actualizado de num_access
            Optional<Integer> numAccessOptional = repository.getNumAccessByUsername(user.getUsername());

            if (numAccessOptional.isPresent()) {
                int numAccess = numAccessOptional.get();

                // Mostrar la alerta de "ACCESO CONCEDIDO" con el número actual de entrenamientos
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Acceso Concedido");
                alert.setHeaderText(null);
                if(numAccess == 10)
                {
                    alert.setContentText("¡¡¡Lo has conseguido!!!! \n Ya llevas: " + numAccess + " entrenamientos. ¡Increible "+ user.getUsername() +  "!");

                }else
                {
                    alert.setContentText("¡Bienvenido! Ya llevas: " + numAccess + " entrenamientos. ¡Sigue así "+ user.getUsername() +  "!");
                }

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
            } else {
                // Manejar el caso en el que no se pueda obtener el número de entrenamientos
                System.out.println("ERROR: No se pudo obtener el número de entrenamientos.");
            }
        } else {
            // Mostrar un mensaje de error si no se encuentra el usuario
            labelMessageAccessGym.setText("ERROR: Usuario no encontrado");
            System.out.println("ERROR: Usuario no encontrado");
        }
    }

    @FXML
    private void accessCart() throws IOException {

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("cart-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void accessProfile() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("profile-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}

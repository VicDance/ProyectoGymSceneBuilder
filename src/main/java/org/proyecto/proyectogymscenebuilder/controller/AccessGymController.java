package org.proyecto.proyectogymscenebuilder.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.proyecto.proyectogymscenebuilder.MainApplication;
import org.proyecto.proyectogymscenebuilder.model.User;
import org.proyecto.proyectogymscenebuilder.repository.UserRepository;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;

public class AccessGymController {

    private final UserRepository repository = new UserRepository();

    @FXML
    Label labelAccessGym;

    @FXML
    Label labelMessageAccessGym;

    @FXML
    Label labelProfile;

    @FXML
    public void accessGym() throws SQLException {
        User user = repository.getUserById(1);
        repository.updateLastDate(user.getUserId(), new Date(java.util.Date.from(Instant.now()).getTime()));
        labelMessageAccessGym.setText("ACCESO CONCEDIDO");
        System.out.println("ACCESO CONCEDIDO");
        try {
            Thread.sleep(2000);
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void accessProfile() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("profile-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void accessCart() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("cart-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}

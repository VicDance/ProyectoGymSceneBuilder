package org.proyecto.proyectogymscenebuilder.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.proyecto.proyectogymscenebuilder.model.User;
import org.proyecto.proyectogymscenebuilder.repository.UserRepository;

import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;

public class AccessGymController {

    private UserRepository repository = new UserRepository();

    @FXML
    Label labelAccessGym;

    @FXML
    Label labelMessageAccessGym;

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
}

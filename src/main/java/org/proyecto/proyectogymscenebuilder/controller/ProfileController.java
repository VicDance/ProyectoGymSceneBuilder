package org.proyecto.proyectogymscenebuilder.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lombok.SneakyThrows;
import org.proyecto.proyectogymscenebuilder.model.User;
import org.proyecto.proyectogymscenebuilder.repository.UserRepository;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    private final UserRepository repository = new UserRepository();

    @FXML
    Label labelProfileUsername;

    @FXML
    Label labelProfileLastAccess;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadProfile();
    }

    private void loadProfile() throws SQLException {
        User user = repository.getUserById(1);
        labelProfileUsername.setText(labelProfileUsername.getText() + " " + user.getUsername());
        labelProfileLastAccess.setText(labelProfileLastAccess.getText() + " " + user.getLast_access() + "");
    }
}

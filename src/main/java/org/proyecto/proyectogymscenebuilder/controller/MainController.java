package org.proyecto.proyectogymscenebuilder.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.proyecto.proyectogymscenebuilder.MainApplication;

import java.io.IOException;

public class MainController {
    @FXML
    private ImageView imageView;


    public void setImage(Image image) {
        imageView.setImage(image);
    }

    @FXML
    protected void changeToProductsView() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("products-view.fxml"));
        Parent root = fxmlLoader.load();

        MainController controller = fxmlLoader.getController();

        Image image = new Image("file:src/main/resources/images/banco-gym.jpg");
        controller.setImage(image);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void changeToLoginView() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void changeToProductDescription() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("product-detail-view-2.fxml"));
        Parent root = fxmlLoader.load();

        MainController controller = fxmlLoader.getController();

        Image image = new Image("file:src/main/resources/images/banco-gym.jpg");
        controller.setImage(image);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
package org.proyecto.proyectogymscenebuilder;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private Label labelCesta;
    @FXML
    private Button añadirCestaId;

    @FXML
    private ImageView imageView;

    public void setImage(Image image) {
        imageView.setImage(image);
    }
    @FXML
    protected void changeScene() throws IOException {
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

    @FXML
    protected void addToCart() {
        labelCesta.setText("Añadido al carrito");
    }
}
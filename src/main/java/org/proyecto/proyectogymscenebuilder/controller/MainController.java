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
    @FXML
    private ImageView imageViewInstalacionPrincipal;
    @FXML
    private ImageView imageViewInstalacionCardio;
    @FXML
    private ImageView imageViewInstalacionPiscina;
    @FXML
    private ImageView imageViewInstalacionVestuario;


    public void setImage(Image image) {
        imageView.setImage(image);
    }
    public void setImageImageViewInstalacionPrincipal(Image image) {
        imageViewInstalacionPrincipal.setImage(image);
    }
    public void setImageViewInstalacionCardio(Image image) {
        imageViewInstalacionCardio.setImage(image);
    }
    public void setImageImageViewInstalacionPiscina(Image image) {
        imageViewInstalacionPiscina.setImage(image);
    }
    public void setImageImageViewInstalacionVestuario(Image image) {
        imageViewInstalacionVestuario.setImage(image);
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
    protected void changeToInstalacionesView() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("instalaciones-view.fxml"));
        Parent root = fxmlLoader.load();

        MainController controller = fxmlLoader.getController();

        Image imagePrincipal = new Image("file:src/main/resources/images/instalacionPrincipal.png");
        controller.setImageImageViewInstalacionPrincipal(imagePrincipal);
        Image imageCardio = new Image("file:src/main/resources/images/cardio.png");
        controller.setImageViewInstalacionCardio(imageCardio);
        Image imagePiscina = new Image("file:src/main/resources/images/piscina.png");
        controller.setImageImageViewInstalacionPiscina(imagePiscina);
        Image imageVestuario = new Image("file:src/main/resources/images/vestuarios.png");
        controller.setImageImageViewInstalacionVestuario(imageVestuario);

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
    @FXML
    protected void changeToSalaPrincipal() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("salaPrincipal.fxml"));
        Parent root = fxmlLoader.load();

        MainController controller = fxmlLoader.getController();

        Image imagePrincipal = new Image("file:src/main/resources/images/instalacionPrincipal.png");
        controller.setImageImageViewInstalacionPrincipal(imagePrincipal);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    protected void changeToSalaCardio() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("cardio.fxml"));
        Parent root = fxmlLoader.load();

        MainController controller = fxmlLoader.getController();

        Image imageCardio = new Image("file:src/main/resources/images/cardio.png");
        controller.setImageViewInstalacionCardio(imageCardio);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    protected void changeToSalaPiscina() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("piscina.fxml"));
        Parent root = fxmlLoader.load();

        MainController controller = fxmlLoader.getController();

        Image imagePiscina = new Image("file:src/main/resources/images/piscina.png");
        controller.setImageImageViewInstalacionPiscina(imagePiscina);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    protected void changeToVestuario() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("vestuarios.fxml"));
        Parent root = fxmlLoader.load();

        MainController controller = fxmlLoader.getController();

        Image imageVstuarios = new Image("file:src/main/resources/images/vestuarios.png");
        controller.setImageImageViewInstalacionVestuario(imageVstuarios);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
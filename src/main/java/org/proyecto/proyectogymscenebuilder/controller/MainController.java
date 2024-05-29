package org.proyecto.proyectogymscenebuilder.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.proyecto.proyectogymscenebuilder.MainApplication;
import org.proyecto.proyectogymscenebuilder.qr.QRReader;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static org.proyecto.proyectogymscenebuilder.controller.UserController.logged;

public class MainController implements Initializable {
    @FXML
    private ImageView imageView;
    @FXML
    private ImageView imageMancuernas;
    @FXML
    private ImageView imageBici;
    @FXML
    private ImageView imageMultiestacion;
    @FXML
    private ImageView imageTarifa1;
    @FXML
    private ImageView imageTarifa3;
    @FXML
    private ImageView imageTarifa6;
    @FXML
    private ImageView imageTarifa12;
    @FXML
    private ImageView imageViewInstalacionPrincipal;
    @FXML
    private ImageView imageViewInstalacionCardio;
    @FXML
    private ImageView imageViewInstalacionPiscina;
    @FXML
    private ImageView imageViewInstalacionVestuario;
    @FXML
    Label labelCesta;
    @FXML
    Button añadirCestaId;
    @FXML
    Button buttonUser;

    private QRReader qrReader;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*if (!logged) {
            añadirCestaId.setDisable(true);
        }*/
    }


    public void setImage(Image image) {
        imageView.setImage(image);
    }
    public void setImageMancuernas(Image image) {
        imageMancuernas.setImage(image);
    }
    public void setImageBici(Image image) {
        imageBici.setImage(image);
    }
    public void setImageTarifa1(Image image) {
        imageTarifa1.setImage(image);
    }
    public void setImageTarifa3(Image image) {
        imageTarifa3.setImage(image);
    }
    public void setImageTarifa6(Image image) {
        imageTarifa6.setImage(image);
    }
    public void setImageTarifa12(Image image) {
        imageTarifa12.setImage(image);
    }
    public void setImageMultiestacion(Image image) {
        imageMultiestacion.setImage(image);
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
        Image imageMancuernas = new Image("file:src/main/resources/images/mancuernas.png");
        controller.setImageMancuernas(imageMancuernas);
        Image imageBici = new Image("file:src/main/resources/images/bici.png");
        controller.setImageBici(imageBici);
        Image imageMultiestacion = new Image("file:src/main/resources/images/multiestacion.png");
        controller.setImageMultiestacion(imageMultiestacion);

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
    protected void changeToTarifasView() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("tarifas-view.fxml"));
        Parent root = fxmlLoader.load();

        MainController controller = fxmlLoader.getController();

        Image imageTarifa1 = new Image("file:src/main/resources/images/mensual.png");
        controller.setImageTarifa1(imageTarifa1);
        Image imageTarifa3 = new Image("file:src/main/resources/images/trimestral.png");
        controller.setImageTarifa3(imageTarifa3);
        Image imageTarifa6 = new Image("file:src/main/resources/images/semestral.png");
        controller.setImageTarifa6(imageTarifa6);
        Image imageTarifa12 = new Image("file:src/main/resources/images/anual.png");
        controller.setImageTarifa12(imageTarifa12);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void changeToLoginView() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader;
        if (logged){
            buttonUser.setText("Menu");
            fxmlLoader = new FXMLLoader(MainApplication.class.getResource("after-login-view.fxml"));
        } else {
            fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        }

        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void setTextOnButton(String text) {
        buttonUser.setText(text);
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

    @FXML
    protected void changeToCartView() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("cart-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void addToCart() {
        labelCesta.setText("Añadido al carrito");
    }

    @FXML
    public void activeCam() {
        qrReader = new QRReader();

        Stage stage = new Stage();
        stage.setTitle("Coloque el código frente a la camara");
        stage.setScene(qrReader.makeUI());
        stage.setHeight(500);
        stage.setWidth(600);
        stage.show();

        qrReader.startCameraInput();
    }
}
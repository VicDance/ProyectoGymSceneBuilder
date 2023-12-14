module org.proyecto.proyectogymscenebuilder {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;

    opens org.proyecto.proyectogymscenebuilder to javafx.fxml;
    exports org.proyecto.proyectogymscenebuilder;
}
module org.proyecto.proyectogymscenebuilder {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires mysql.connector.j;

    opens org.proyecto.proyectogymscenebuilder to javafx.fxml;
    exports org.proyecto.proyectogymscenebuilder;
    exports org.proyecto.proyectogymscenebuilder.controller;
    opens org.proyecto.proyectogymscenebuilder.controller to javafx.fxml;
}
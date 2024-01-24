package org.proyecto.proyectogymscenebuilder.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public Connection connection;

    public Connection getConnection() {
        String databaseName = "proyecto_gym";
        String databaseUser = "root";
        String databasePassword = "ItsMeVicky";
        String url = "jdbc:mysql://localhost:3306/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}

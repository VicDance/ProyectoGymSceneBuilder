package org.proyecto.proyectogymscenebuilder.repository;

import org.proyecto.proyectogymscenebuilder.connection.DatabaseConnection;
import org.proyecto.proyectogymscenebuilder.model.User;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UserRepository {
    //DatabaseConnection databaseConnection = new DatabaseConnection();

    //En proceso:
    public List<User> getAllUsersWithAdditionalInfo() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        List<User> users = new ArrayList<>();

        try {
            String query = "select id, username, password, last_access, creation_date, additional_field from user";

            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setLast_access(resultSet.getDate(4));
                user.setCreation_date(resultSet.getDate(5));

                // Agregar campo adicional (cambiar "additional_field" por el nombre real de la columna)
                user.setAdditionalField(resultSet.getString("additional_field"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != statement) {
                statement.close();
            }

            if (null != connection) {
                connection.close();
            }
        }
        return users;
    }


    public Optional<User> getUserByUsername(String username) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            String query = "SELECT username, last_access, creation_date, num_access FROM user WHERE username = ?";
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setLast_access(resultSet.getDate("last_access"));
                user.setCreation_date(resultSet.getDate("creation_date"));
                user.setNum_access(resultSet.getInt("num_access"));

                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, statement);
        }

        return Optional.empty();
    }


    public void deleteUserByUsername(String username) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false);  // Desactivar autocommit

            // Eliminar usuario por nombre de usuario
            String deleteQuery = "DELETE FROM user WHERE username = ?";
            statement = connection.prepareStatement(deleteQuery);
            statement.setString(1, username);

            statement.executeUpdate();

            connection.commit();  // Commit manual
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();  // Rollback en caso de excepción
        } finally {
            // Cerrar PreparedStatement y Connection
            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }


    public Optional<Integer> getNumAccessByUsername(String username) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            String query = "SELECT num_access FROM user WHERE username = ?";
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int numAccess = resultSet.getInt("num_access");
                return Optional.of(numAccess);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, statement);
        }

        return Optional.empty();
    }
    private void closeResources(Connection connection, PreparedStatement statement) throws SQLException {
        if (statement != null) {
            statement.close();
        }

        if (connection != null) {
            connection.close();
        }
    }



    public Optional<User> getUserByEmailAndPassword(String username, String password) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        User user = null;
        try {
            connection = DatabaseConnection.getConnection();

            String query = "SELECT * FROM user WHERE username = ? AND password = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Se encontró un usuario con las credenciales proporcionadas
                user = User.builder()
                        .userId(resultSet.getInt("id"))
                        .username(resultSet.getString("username"))
                        .password(resultSet.getString("password"))
                        .last_access(resultSet.getTimestamp("last_access"))
                        .creation_date(resultSet.getTimestamp("creation_date"))
                        .build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != statement) {
                statement.close();
            }

            if (null != connection) {
                connection.close();
            }
        }

        return Optional.ofNullable(user);
    }


    public boolean isUsernameTaken(String username) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM user WHERE username = ?")) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        }
        return false;
    }



    public User createUser(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.getConnection();

            // Modifica la consulta SQL para incluir la columna num_access
            String query = "INSERT INTO user (username, password, last_access, creation_date, num_access) VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());

            // Obtén la fecha y hora actual del sistema
            LocalDateTime currentDateTime = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(currentDateTime);

            // Configura los valores de last_access, creation_date y num_access con la fecha y hora actual
            statement.setTimestamp(3, timestamp);
            statement.setTimestamp(4, timestamp);
            statement.setInt(5, 0); // Num_access inicializado en 0

            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setUserId(resultSet.getInt(1)); // Establecer el ID generado para el usuario
            }

            // No se realiza un commit aquí, se realizará en el bloque try-with-resources

        } catch (SQLException e) {
            e.printStackTrace();
            // Solo realiza rollback si se produce una excepción
            if (connection != null) {
                connection.rollback();
            }
        } finally {
            // Cerrar ResultSet antes de PreparedStatement
            if (resultSet != null) {
                resultSet.close();
            }

            // Cerrar PreparedStatement
            if (statement != null) {
                statement.close();
            }

            // Cerrar Connection
            if (connection != null) {
                // Cerrar la conexión y realizar el commit o rollback según sea necesario
                if (connection.getAutoCommit()) {
                    // Si el autocommit está habilitado, cerrar la conexión
                    connection.close();
                } else {
                    // Si el autocommit está deshabilitado, realizar el commit o rollback
                    if (user.getUserId() > 0) {
                        // Realizar commit solo si se ha generado un ID válido
                        connection.commit();
                    } else {
                        // Realizar rollback si no se ha generado un ID válido
                        connection.rollback();
                    }
                    // Cerrar la conexión después de commit o rollback
                    connection.close();
                }
            }
        }

        return user;
    }



    public void incrementNumAccessByUsername(String username) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            String query = "UPDATE user SET num_access = num_access + 1 WHERE username = ?";
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, username);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(connection, statement);
        }
    }





    public void updateLastAccessByUsername(String username, Timestamp last_access) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnection.getConnection();

            String query = "UPDATE user SET last_access = ? WHERE username = ?";
            statement = connection.prepareStatement(query);
            statement.setTimestamp(1, last_access);
            statement.setString(2, username);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            if (null != statement) {
                statement.close();
            }
            if (null != connection) {
                connection.close();
            }
        }
    }



}

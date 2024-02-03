package org.proyecto.proyectogymscenebuilder.repository;

import org.proyecto.proyectogymscenebuilder.connection.DatabaseConnection;
import org.proyecto.proyectogymscenebuilder.model.User;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    //DatabaseConnection databaseConnection = new DatabaseConnection();

    public List<User> getAllUsers() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        List<User> users = new ArrayList<>();

        try {
            String query = "select * from user";

            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setLast_access(resultSet.getDate(4));
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

    public User getUserById(int id) throws SQLException {
        return getAllUsers().stream().filter(user -> user.getUserId() == id).findFirst().orElse(new User());
    }

    public Optional<User> getUserByEmailAndPassword(String username, String password) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        User user = User.builder().build();
        try {
            connection = DatabaseConnection.getConnection();

            String query = "select username from user where username = ? and password = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                String name = resultSet.getString("username");
                user.setUsername(name);
            }
            //return Optional.of(user);

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
        return Optional.of(user);
    }

    public User createUser(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.getConnection();

            String query = "insert into user (username, password) values (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString (1, user.getUsername());
            statement.setString (2, user.getPassword());

            statement.executeUpdate();
            connection.commit();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            if (null != resultSet) {
                resultSet.close();
            }

            if (null != statement) {
                statement.close();
            }

            if (null != connection) {
                connection.close();
            }
        }
        return null;
    }

    public void updateLastDate(int id, Date last_access) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseConnection.getConnection();

            String query = "update user set last_access = ?  where id = ?";
            statement = connection.prepareStatement(query);
            statement.setDate (1, last_access);
            statement.setInt (2, id);

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

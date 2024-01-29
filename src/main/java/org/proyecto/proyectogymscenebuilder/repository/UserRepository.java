package org.proyecto.proyectogymscenebuilder.repository;

import org.proyecto.proyectogymscenebuilder.connection.DatabaseConnection;
import org.proyecto.proyectogymscenebuilder.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public User getUserByEmailAndPassword(String username, String password) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        User user = null;
        try {
            connection = DatabaseConnection.getConnection();

            String query = "select * from user where username = ? and password = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            user = User.builder().build();
            user.toBuilder().username(resultSet.getString(1));
            user.toBuilder().password(resultSet.getString(2));

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
        return user;
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
}

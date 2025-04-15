package org.unicesumar.dao.impl;

import org.unicesumar.dao.UserDao;
import org.unicesumar.entity.User;
import org.unicesumar.exceptions.ECommerceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private String query;

    private Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User getUserByEmail(String email) {
        query = "SELECT * FROM users WHERE email = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3));
                }
            }
        } catch (SQLException e) {
            throw new ECommerceException("Error fetching user by email", e.toString());
        }

        return null;
    }

    @Override
    public void addNewUser(User user) {
        query = "INSERT INTO users (name, email) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User successfully inserted!");
            }

        } catch (SQLException e) {
            throw new ECommerceException("Error inserting user", e.getMessage());
        }
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email")
                ));
            }

        } catch (SQLException e) {
            throw new ECommerceException("Error fetching users", e.toString());
        }

        return users;
    }
}

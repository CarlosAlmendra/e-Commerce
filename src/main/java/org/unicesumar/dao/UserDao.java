package org.unicesumar.dao;

import org.unicesumar.entity.User;

import java.util.List;

public interface UserDao {
    User getUserByEmail(String email);

    void addNewUser(User user);

    List<User> getUsers();
}

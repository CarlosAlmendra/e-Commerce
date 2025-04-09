package org.unicesumar.dao;

import org.unicesumar.entity.User;

import java.util.List;

public interface UserDao {
    User getUser(int id);

    List<User> getUsers();
}

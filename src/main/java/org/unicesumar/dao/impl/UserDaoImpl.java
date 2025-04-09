package org.unicesumar.dao.impl;

import org.unicesumar.dao.UserDao;
import org.unicesumar.entity.User;

import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public User getUser(int id) {
        String sql = "select * from user where id = ?";
        return null;
    }

    @Override
    public List<User> getUsers() {
        return List.of();
    }
}

package com.web.ryzhakov.repositories;

import com.web.ryzhakov.models.User;

import java.util.List;

public interface UserRepository  {
    void addUser(User user);

    void removeUser(int id);

    void editUser(User user);

    User getUserById(int id);

    List<User> getAllUsers();

    User getUserByUsername(String username);
}

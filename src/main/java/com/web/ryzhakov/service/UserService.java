package com.web.ryzhakov.service;

import com.web.ryzhakov.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    void addUser(User user);

    void removeUser(int id);

    void updateUser(User user);

    User getUserById(int id);

    List<User> getAllUsers();

    User findByUsername(String username);
}

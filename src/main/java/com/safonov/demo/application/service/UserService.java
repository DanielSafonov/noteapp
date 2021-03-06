package com.safonov.demo.application.service;

import com.safonov.demo.application.model.entity.User;

import java.util.Set;

public interface UserService {
    User createUser(User currentUser, User user);
    void deleteUser(User currentUser, User user);
    User updateUser(User currentUser, User user);
    User getUserByID(User currentUser, Long id);
    User getUserByUsername(User currentUser, String username);
    Set<User> getAllUsers(User currentUser);
}

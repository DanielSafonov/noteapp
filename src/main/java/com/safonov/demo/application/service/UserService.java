package com.safonov.demo.application.service;

import com.safonov.demo.application.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UserService {
    User createUser(User currentUser, User user);
    void deleteUser(User currentUser, User user);
    User updateUser(User currentUser, User user);
    User getUserByID(User currentUser, Long id);
    Set<User> getAllUsers(User currentUser);
}

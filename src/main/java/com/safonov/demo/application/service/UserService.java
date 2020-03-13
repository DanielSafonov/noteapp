package com.safonov.demo.application.service;

import com.safonov.demo.application.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UserService {
    User createUser(User user);
    User deleteUser(User user);
    User updateUser(User user);
    User getUserByID(Long id);
    Set<User> getAllUsers();
}

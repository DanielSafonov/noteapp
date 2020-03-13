package com.safonov.demo.application.service.impl;

import com.safonov.demo.application.common.exception.UserException;
import com.safonov.demo.application.model.entity.User;
import com.safonov.demo.application.model.repository.UserRepository;
import com.safonov.demo.application.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    //TODO: Проверка полномочий для работы с пользователями - Role Based Access Control

    @Override
    public User createUser(User currentUser, User user) {
        try{
            return userRepository.save(user);
        } catch (Exception e){
            throw new UserException(e.getMessage(), currentUser.getUsername());
        }
    }

    @Override
    public void deleteUser(User currentUser, User user) {
        try{
            userRepository.delete(user);
        } catch (Exception e){
            throw new UserException(e.getMessage(), currentUser.getUsername());
        }
    }

    @Override
    public User updateUser(User currentUser, User user) {
        try{
            return userRepository.save(user);
        } catch (Exception e){
            throw new UserException(e.getMessage(), currentUser.getUsername());
        }
    }

    @Override
    public User getUserByID(User currentUser, Long id) {
        try{
            return userRepository.findById(id).get();
        } catch (Exception e){
            throw new UserException(e.getMessage(), currentUser.getUsername());
        }
    }

    @Override
    public User getUserByUsername(User currentUser, String username) {
        try{
            return userRepository.findByUsername(username);
        } catch (Exception e){
            throw new UserException(e.getMessage(), currentUser.getUsername());
        }
    }

    @Override
    public Set<User> getAllUsers(User currentUser) {
        try{
            return new HashSet<>(userRepository.findAll());
        } catch (Exception e){
            throw new UserException(e.getMessage(), currentUser.getUsername());
        }
    }
}

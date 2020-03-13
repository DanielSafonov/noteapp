package com.safonov.demo.application.web.facade.impl;

import com.safonov.demo.application.web.dto.ResponseDAO;
import com.safonov.demo.application.web.dto.UserDTO;
import com.safonov.demo.application.web.facade.UserControllerFacade;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public class UserControllerFacadeImpl implements UserControllerFacade {
    /**
     * Создать пользователя
     *
     * @param userDTO
     * @return ResponseDAO<UserDTO>
     */
    @Override
    public ResponseDAO<UserDTO> createUser(UserDTO userDTO) {
        return null;
    }

    /**
     * Удалить пользователя
     *
     * @param userDTO
     * @return ResponseDAO
     */
    @Override
    public ResponseDAO deleteUser(UserDTO userDTO) {
        return null;
    }

    /**
     * Получить пользователя по идентификатору
     *
     * @param userID
     * @return ResponseDAO<UserDTO>
     */
    @Override
    public ResponseDAO<UserDTO> getUserByID(String userID) {
        return null;
    }

    /**
     * Получить список всех пользователей
     *
     * @return ResponseDAO<Set<UserDTO>>
     */
    @Override
    public ResponseDAO<Set<UserDTO>> getAllUsers() {
        return null;
    }
}

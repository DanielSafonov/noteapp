package com.safonov.demo.application.web.facade;

import com.safonov.demo.application.web.dto.ResponseDAO;
import com.safonov.demo.application.web.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Set;

/**
 * Бизнес-логика для UserController
 */
public interface UserControllerFacade {
    /**
     * Создать пользователя
     * @param principal
     * @param userDTO
     * @return UserDTO
     */
    ResponseDAO<UserDTO> createUser(Principal principal, UserDTO userDTO);

    /**
     * Удалить пользователя
     * @param principal
     * @param userDTO
     */
    ResponseDAO deleteUser(Principal principal, UserDTO userDTO);

    /**
     * Получить пользователя по идентификатору
     * @param principal
     * @param userID
     * @return UserDTO
     */
    ResponseDAO<UserDTO> getUserByID(Principal principal, String userID);

    /**
     * Получить список всех пользователей
     * @param principal
     * @return Set<UserDTO>
     */
    ResponseDAO<Set<UserDTO>> getAllUsers(Principal principal);
}

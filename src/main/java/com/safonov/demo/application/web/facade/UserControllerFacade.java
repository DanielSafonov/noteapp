package com.safonov.demo.application.web.facade;

import com.safonov.demo.application.web.dto.ResponseDAO;
import com.safonov.demo.application.web.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Бизнес-логика для UserController
 */
@Component
public interface UserControllerFacade {
    /**
     * Создать пользователя
     * @param userDTO
     * @return UserDTO
     */
    ResponseDAO<UserDTO> createUser(UserDTO userDTO);

    /**
     * Удалить пользователя
     * @param userDTO
     */
    ResponseDAO deleteUser(UserDTO userDTO);

    /**
     * Получить пользователя по идентификатору
     * @param userID
     * @return UserDTO
     */
    ResponseDAO<UserDTO> getUserByID(String userID);

    /**
     * Получить список всех пользователей
     * @return Set<UserDTO>
     */
    ResponseDAO<Set<UserDTO>> getAllUsers();
}

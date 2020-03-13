package com.safonov.demo.application.web.facade.impl;

import com.safonov.demo.application.model.entity.User;
import com.safonov.demo.application.service.UserService;
import com.safonov.demo.application.web.converter.UserConverter;
import com.safonov.demo.application.web.dto.ResponseDAO;
import com.safonov.demo.application.web.dto.UserDTO;
import com.safonov.demo.application.web.facade.UserControllerFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.util.Set;

@Slf4j
public class UserControllerFacadeImpl implements UserControllerFacade {
    @Autowired
    private UserService userService;

    /**
     * Создать пользователя
     *
     * @param userDTO
     * @return ResponseDAO<UserDTO>
     */
    @Override
    public ResponseDAO<UserDTO> createUser(Principal principal, UserDTO userDTO) {
        try{
            userService.createUser(
                    new User().setUsername(principal.getName()),
                    UserConverter.toEntity(userDTO)
            );
            return ResponseDAO.buildSuccessResponse(userDTO);
        } catch (Exception e){
            //TODO: Exception Handling
            return ResponseDAO.buildErrorResponse(userDTO, e.getMessage(), 500);
        }
    }

    /**
     * Удалить пользователя
     *
     * @param userDTO
     * @return ResponseDAO
     */
    @Override
    public ResponseDAO deleteUser(Principal principal, UserDTO userDTO) {
        try{
            userService.deleteUser(
                    new User().setUsername(principal.getName()),
                    UserConverter.toEntity(userDTO)
            );
            return ResponseDAO.buildSuccessResponse(userDTO);
        } catch (Exception e){
            //TODO: Exception Handling
            return ResponseDAO.buildErrorResponse(userDTO, e.getMessage(), 500);
        }
    }

    /**
     * Получить пользователя по идентификатору
     *
     * @param userID
     * @return ResponseDAO<UserDTO>
     */
    @Override
    public ResponseDAO<UserDTO> getUserByID(Principal principal, String userID) {
        try{
            User user = userService.getUserByID(
                    new User().setUsername(principal.getName()),
                    Long.parseLong(userID)
            );
            return ResponseDAO.buildSuccessResponse(UserConverter.toDTO(user));
        } catch (Exception e){
            //TODO: Exception Handling
            return ResponseDAO.buildErrorResponse(e.getMessage(), 500);
        }
    }

    /**
     * Получить список всех пользователей
     *
     * @return ResponseDAO<Set<UserDTO>>
     */
    @Override
    public ResponseDAO<Set<UserDTO>> getAllUsers(Principal principal) {
        try{
            Set<User> users = userService.getAllUsers(new User().setUsername(principal.getName()));
            return ResponseDAO.buildSuccessResponse(UserConverter.toDTO(users));
        } catch (Exception e){
            //TODO: Exception Handling
            return ResponseDAO.buildErrorResponse(e.getMessage(), 500);
        }
    }
}

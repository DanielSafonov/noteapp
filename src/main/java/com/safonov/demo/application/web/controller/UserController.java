package com.safonov.demo.application.web.controller;

import com.safonov.demo.application.common.aspects.logger.Loggable;
import com.safonov.demo.application.web.dto.ResponseDAO;
import com.safonov.demo.application.web.dto.UserDTO;
import com.safonov.demo.application.web.facade.UserControllerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Set;

/**
 * Контроллер для работы с пользователями
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserControllerFacade userControllerFacade;

    @Loggable
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDAO<UserDTO> createUser(
            Principal principal,
            @RequestBody UserDTO userDTO
    ) {
        return userControllerFacade.createUser(principal, userDTO);
    }

    @Loggable
    @DeleteMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE,
                   produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDAO deleteUser(
            Principal principal,
            @RequestBody UserDTO userDTO
    ) {
        return userControllerFacade.deleteUser(principal, userDTO);
    }

    @Loggable
    @GetMapping(value = "/get/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDAO<UserDTO> getUserByID(
            Principal principal,
            @PathVariable(name = "id") String id
    ) {
        return userControllerFacade.getUserByID(principal, id);
    }

    @Loggable
    @GetMapping(value = "/get/all", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDAO<Set<UserDTO>> getAllUsers(Principal principal) {
        return userControllerFacade.getAllUsers(principal);
    }
}

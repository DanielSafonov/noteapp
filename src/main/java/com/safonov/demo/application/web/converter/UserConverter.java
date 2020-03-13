package com.safonov.demo.application.web.converter;

import com.safonov.demo.application.model.entity.User;
import com.safonov.demo.application.web.dto.UserDTO;

import java.util.Set;
import java.util.stream.Collectors;

public class UserConverter {
    public static User toEntity(UserDTO userDTO){
        return new User()
                .setId(userDTO.getId())
                .setUsername(userDTO.getUsername())
                .setPassword(userDTO.getPassword());
    }

    public static UserDTO toDTO(User user){
        return new UserDTO()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setPassword(user.getPassword());
    }

    public static Set<UserDTO> toDTO(Set<User> users){
        return users
                .stream()
                .map(UserConverter::toDTO)
                .collect(Collectors.toSet());
    }
}

package com.safonov.demo.application.service;

import com.safonov.demo.application.model.entity.User;
import com.safonov.demo.application.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * UserDetailsService с получением пользователей из БД
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserServiceImpl userService;

    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(new User().setUsername("auth system"), username);

        if(user == null)
            throw new UsernameNotFoundException("User with name " + username + " not found!");

        List<GrantedAuthority> authorities = new ArrayList<>(1);
        authorities.add(new SimpleGrantedAuthority("USER"));

        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
    }
}
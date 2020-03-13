package com.safonov.demo.config.security;

import com.safonov.demo.application.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthFilter authFilter;
    @Autowired
    private BasicAuthEntryPoint basicAuthEntryPoint;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    //Конфигурация доступа
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .authorizeRequests()
                .antMatchers("/ping").permitAll()
                .anyRequest().authenticated()

                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().httpBasic().authenticationEntryPoint(basicAuthEntryPoint);

        http
                .addFilterAfter(authFilter, BasicAuthenticationFilter.class);
    }

    //Выбрать userDetailsService
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder()); //TODO
        auth.userDetailsService(customUserDetailsService);
    }

    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

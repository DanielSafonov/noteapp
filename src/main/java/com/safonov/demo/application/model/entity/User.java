package com.safonov.demo.application.model.entity;

import com.safonov.demo.application.common.Constants;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER", schema = Constants.DB_SCHEMA)
@Accessors(chain = true)
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME", unique = true, nullable = false, length = 64)
    private String username;

    @Column(name = "PASSWORD", nullable = false, length = 64)
    private String password; //TODO: BCrypt
}

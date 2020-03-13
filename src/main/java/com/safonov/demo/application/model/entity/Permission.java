package com.safonov.demo.application.model.entity;

import com.safonov.demo.application.common.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER", schema = Constants.DB_SCHEMA)
@Accessors(chain = true)
@Data
@EqualsAndHashCode(exclude = {"user", "note"})
@ToString(exclude = {"user", "note"})
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "NOTE_ID")
    private Note note;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "PERMIT_ALL", nullable = false)
    private Boolean permitAll = false;

    @Column(name = "READ", nullable = false)
    private Boolean readPermission = false;

    @Column(name = "WRITE", nullable = false)
    private Boolean writePermission = false;
}

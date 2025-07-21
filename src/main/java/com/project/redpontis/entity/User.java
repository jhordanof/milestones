package com.project.redpontis.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TBL_USERS", schema = "public")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Identificador del usuario. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_ID_USER", nullable = false)
    private Long id;

    /** Nombre de usuario (único). */
    @Column(name = "USERNAME", length = 100, nullable = false, unique = true)
    private String username;

    /** Contraseña encriptada del usuario. */
    @Column(name = "PASSWORD", length = 255, nullable = false)
    private String password;

    /** Rol del usuario (USER o ADMIN). */
    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", length = 20, nullable = false)
    private Role role;
}
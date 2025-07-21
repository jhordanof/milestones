package com.project.redpontis.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TBL_TASKS", schema = "public")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Identificador de la tarea. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_ID_TASK", nullable = false)
    private Long id;

    /** Título de la tarea. */
    @Column(name = "TITLE", length = 150, nullable = false)
    private String title;

    /** Descripción de la tarea. */
    @Column(name = "DESCRIPTION_TASK", length = 500)
    private String description;

    /** Estado de la tarea (completada o no). */
    @Column(name = "COMPLETED", nullable = false)
    private boolean completed;

    /** Usuario relacionado. */
    @ManyToOne
    @JoinColumn(name = "C_ID_USER", nullable = false)
    private User user;
}
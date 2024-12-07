package com.todoTask.crud.repaso.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "specialities")
public class SpecialtyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String descriptions;
}

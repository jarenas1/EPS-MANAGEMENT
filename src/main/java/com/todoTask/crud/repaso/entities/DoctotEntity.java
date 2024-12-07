package com.todoTask.crud.repaso.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "doctors")
public class DoctotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastname;

}

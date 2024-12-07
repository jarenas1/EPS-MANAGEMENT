package com.todoTask.crud.repaso.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "patients")
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastname;

    @Column(unique = true)
    private String document;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "patient")
    private List<DateEntity> dates;

}

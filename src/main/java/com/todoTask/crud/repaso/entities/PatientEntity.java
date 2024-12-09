package com.todoTask.crud.repaso.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
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


    public PatientEntity(String document, String lastname, String name, UserEntity user) {
        this.document = document;
        this.lastname = lastname;
        this.name = name;
        this.user = user;
    }
}

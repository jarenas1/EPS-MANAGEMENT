package com.todoTask.crud.repaso.entities;

import com.todoTask.crud.repaso.tools.enums.RoleUser;
import jakarta.persistence.*;

import javax.print.Doc;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private RoleUser role;

    private Boolean active;

    @OneToOne(mappedBy = "user")
    private PatientEntity patient;

    @OneToOne(mappedBy = "user")
    private DoctorEntity doctor;

}

package com.todoTask.crud.repaso.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.todoTask.crud.repaso.tools.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String email;

    private Boolean active;

    @OneToOne(mappedBy = "user")
    private PatientEntity patient;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private DoctorEntity doctor;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Set<RoleEnum> roles = new HashSet<>();


}

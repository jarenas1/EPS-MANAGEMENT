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
@Table(name = "doctors")
public class DoctorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastname;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "speciality_id")
    private SpecialtyEntity specialty;

    @OneToMany(mappedBy = "doctor")
    private List<DateEntity> dates;

    @OneToMany(mappedBy = "doctor")
    private List<ShiftEntity> shifts;
}

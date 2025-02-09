package com.todoTask.crud.repaso.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctors")
public class DoctorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private SpecialtyEntity specialty;

    @OneToMany(mappedBy = "doctor")
    private List<DateEntity> dates;

    @OneToMany(mappedBy = "doctor")
    private List<ShiftEntity> shifts;

}

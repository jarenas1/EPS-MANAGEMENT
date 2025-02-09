package com.todoTask.crud.repaso.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.todoTask.crud.repaso.tools.enums.Day;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shifts")
public class ShiftEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Day day;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Boolean active = true;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctor;

}

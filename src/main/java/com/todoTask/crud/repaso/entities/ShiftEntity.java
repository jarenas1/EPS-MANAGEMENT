package com.todoTask.crud.repaso.entities;

import com.todoTask.crud.repaso.tools.enums.Day;
import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "shifts")
public class ShiftEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Day day;

    private LocalTime startTime;

    private LocalTime endTime;

    private Boolean active = true;
}
